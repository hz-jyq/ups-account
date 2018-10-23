package com.pgy.ups.account.business.factory.proofread;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.pgy.ups.account.business.configuration.properties.BaoFuProofreadProperties;
import com.pgy.ups.account.business.dao.mapper.BaofuBorrowDataDao;
import com.pgy.ups.account.business.dao.mapper.BaofuReturnDataDao;
import com.pgy.ups.account.business.dao.mapper.BusinessDataDao;
import com.pgy.ups.account.business.dao.mapper.ProofreadResultDao;
import com.pgy.ups.account.business.entity.BaofuProofreadSum;
import com.pgy.ups.account.business.entity.ProofreadSum;
import com.pgy.ups.account.business.handler.proofread.DocumentParserHandler;
import com.pgy.ups.account.business.handler.proofread.ProofreadHandler;
import com.pgy.ups.account.commom.utils.DateUtils;
import com.pgy.ups.account.commom.utils.HttpClientUtils;
import com.pgy.ups.account.commom.utils.SecurityUtil;
import com.pgy.ups.account.facade.constant.ProofreadAccountType;
import com.pgy.ups.account.facade.model.proofread.BaoFuModel;
import com.pgy.ups.account.facade.model.proofread.BaoFuModelBorrow;
import com.pgy.ups.account.facade.model.proofread.BaoFuModelReturn;
import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

/**
 * 宝付对账处理器工厂
 * 
 * @author 墨凉
 *
 */
@Component
public class BaofuProofreadHandlerFactory implements ProofreadHandlerFactory {
	/**
	 * 宝付对账处理器
	 */
	@Resource(type = BaoFuProofreadHandler.class)
	private ProofreadHandler<String, List<? extends BaoFuModel>> baofuProofreadHandler;

	public ProofreadHandler<String, List<? extends BaoFuModel>> getProofreadHandler(String fromSystem,
			String proofreadAccountType, Date date) {
		// 设置下载文本解析器
		BaoFuDocumentParserHandler baoFuDocumentParserHandler = new BaoFuDocumentParserHandler();
		// 设置借款或者还款解析方式
		baoFuDocumentParserHandler.setProofreadAccountType(proofreadAccountType);
		// 设置来源系统
		baoFuDocumentParserHandler.setSystemFrom(fromSystem);
		// 设置对账日期
		baoFuDocumentParserHandler.setProofreadDate(DateUtils.dateToString(date));

		// 处理器设置下载文件解析器
		baofuProofreadHandler.setDocumentParserHandler(baoFuDocumentParserHandler);
		// 处理器设置要对账的系统
		baofuProofreadHandler.setFromSystem(fromSystem);
		// 处理器设置借款或者还款
		baofuProofreadHandler.setProofreadAccountType(proofreadAccountType);
		// 处理器设置对账日期
		baofuProofreadHandler.setDate(date);

		return baofuProofreadHandler;
	}

}

/**
 * 宝付对账处理器 (多例模式)
 * 
 * @author 墨凉
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class BaoFuProofreadHandler implements ProofreadHandler<String, List<? extends BaoFuModel>> {

	private Logger logger = LoggerFactory.getLogger(BaoFuProofreadHandler.class);

	@Resource
	private BaoFuProofreadProperties baoFuProofreadProperties;

	@Resource
	private ProofreadResultDao proofreadResultDao;

	@Resource
	private BaofuBorrowDataDao baofuBorrowDataDao;

	@Resource
	private BaofuReturnDataDao baofuReturnDataDao;

	@Resource
	private BusinessDataDao businessDataDao;

	private DocumentParserHandler<String, List<? extends BaoFuModel>> documentParserHandler;

	private String fromSystem;

	private String proofreadAccountType;

	private Date date;

	public BaoFuProofreadHandler() {
	}

	/**
	 * 处理流程
	 */
	@Override
	public ProofreadResult handler(List<BusinessProofreadModel> list) {
		List<BusinessProofreadModel> businessList = new ArrayList<>(list);
		// 初始化返回结果
		ProofreadResult proofreadResult = initProofreadResult();
		// baofuList用于存储解析文件后的数据
		List<? extends BaoFuModel> baofuList = null;
		// 若之前已经下载成功，则无需再次下载，从数据库中根据日期、来源系统、对账类型取出数据即可
		if (proofreadResult.getDownloadSuccess()) {
			try {
				baofuList = queryDownloadDate(proofreadResult.getProofreadDate(), proofreadResult.getFromSystem(),
						proofreadResult.getProofreadType());
			} catch (Exception e) {
				logger.error("从数据库查询宝付下载数据失败{}", ExceptionUtils.getStackTrace(e));
				return recordProofreadFail(proofreadResult, "从数据库查询宝付下载数据失败");
			}
		} else {
			// 下载文件
			Map<String, Object> param = generateDownLoadParam(proofreadResult.getProofreadDate());
			String responseStr = HttpClientUtils.sentRequest(baoFuProofreadProperties.getRequestUrl(), param);
			// 对下载结果进行判断
			if (StringUtils.isEmpty(responseStr)) {
				logger.error("对账任务执行失败，文件下载失败:");
				return recordProofreadFail(proofreadResult, "文件下载失败");
			}
			// 返回结果不为空时，对responseStr进行解析
			try {
				baofuList = documentParserHandler.handler(responseStr);
			} catch (Exception e) {
				logger.error("对账任务执行失败，下载文件解析失败:{}", ExceptionUtils.getStackTrace(e));
				return recordProofreadFail(proofreadResult, "文件解析失败");
			}

			// 保存baofuList至数据库中
			try {
				this.saveDownloadDate(baofuList, proofreadResult);
			} catch (Exception e) {
				logger.error("保存宝付下载数据失败！{}", ExceptionUtils.getStackTrace(e));
				return recordProofreadFail(proofreadResult, "保存宝付下载数据失败");
			}
		}
		// 解析文件获取为空集合时
		if (CollectionUtils.isEmpty(baofuList)) {
			logger.warn("未能从数据库或文件中获取到账单数据！baofuList:{}", baofuList);
		}
		// 接受原系统的数据list为null时
		if (CollectionUtils.isEmpty(businessList)) {
			logger.warn("没有接受到系统来源数据:{}", businessList);
		}
		// 开始对账
		return proofreadResult;
	}

	/**
	 * 开始对账
	 * 
	 * @param proofreadResult
	 * @param systemList
	 * @param baofuList
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
	public ProofreadResult proofread(ProofreadResult proofreadResult, List<BusinessProofreadModel> businessList,
			List<? extends BaoFuModel> baofuList) throws Exception{
		//添加信息
		businessList = businessList.stream().map((e) -> {
			e.setSystemFrom(proofreadResult.getFromSystem());
			e.setProofreadType(proofreadResult.getProofreadType());
			e.setProofreadDate(proofreadResult.getProofreadDate());
			return e;
		}).collect(Collectors.toList());
		// 先保存businessList
		businessDataDao.batchInsert(businessList);
		 //构建宝付对账汇总数据对象		
		BaofuProofreadSum proofreadSum=new BaofuProofreadSum(proofreadResult,businessList,baofuList,baoFuProofreadProperties);
		//设置商户号
		if (Objects.equals(proofreadResult.getProofreadType(), ProofreadAccountType.BORROW)) {
			proofreadSum.setBusinessNum(baoFuProofreadProperties.getBusinessBorrowNum());			
		} else if (Objects.equals(proofreadResult.getProofreadType(), ProofreadAccountType.RETURN)) {
			proofreadSum.setBusinessNum(baoFuProofreadProperties.getBusinessReturnNum());
		}		
		// 对账成功总金额从0开始计算
		BigDecimal successSum = new BigDecimal("0");
		// 对账成功笔数从0开始计算
		int successTotal = 0;
  
		// 先校对错账
		Iterator<? extends BaoFuModel> baofuListIterator = baofuList.iterator();
		Iterator<BusinessProofreadModel> businessListIterator = businessList.iterator();
		while (baofuListIterator.hasNext()) {
			BaoFuModel baofuModel = baofuListIterator.next();
			while (businessListIterator.hasNext()) {
				BusinessProofreadModel businessModel = businessListIterator.next();
				String businessOrderNum = businessModel.getBusinessOrderNum();
				String baofuOrderNum = baofuModel.getBusinessOrderNum();
				// 根据商户订单号进行对比
				if (Objects.equals(baofuOrderNum, businessOrderNum)) {
					// 获取双方交易金额
					BigDecimal baofuExchangeAmountBigDecimal = new BigDecimal(baofuModel.getExchangeAmount());
					BigDecimal businessExchangeAmountBigDecimal = new BigDecimal(businessModel.getExchangeAmount());
					if (baofuExchangeAmountBigDecimal.compareTo(businessExchangeAmountBigDecimal) == 0) {
						// 相等 则成功金额和成功笔数累加
						successTotal++;
						successSum.add(baofuExchangeAmountBigDecimal);
					} else if (baofuExchangeAmountBigDecimal.compareTo(businessExchangeAmountBigDecimal) < 0) {
						// 异常情况baofu小于business
					} else {
						// 异常情况baofu大于business
					}
				}
				// 校对后移除
				businessListIterator.remove();
				break;
			}
			// 校对后移除
			baofuListIterator.remove();
		}
       
		
		return proofreadResult;
	}
	

	/**
	 * 记录对账任务失败原因，并发挥对账结果
	 * 
	 * @param proofreadResult
	 * @param failReson
	 * @return
	 */
	private ProofreadResult recordProofreadFail(ProofreadResult proofreadResult, String failReson) {
		proofreadResult.appendFailReason(failReson);
		proofreadResult.increaseFailCount();
		proofreadResultDao.updateProofreadResult(proofreadResult);
		return proofreadResult;
	}

	// 保存宝付下载数据并更新对账结果状态，记录文件下载成功
	@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
	public void saveDownloadDate(List<? extends BaoFuModel> baofuList, ProofreadResult proofreadResult)
			throws Exception {
		if (Objects.equals(this.proofreadAccountType, ProofreadAccountType.BORROW)) {
			baofuBorrowDataDao.batchInsert(baofuList);
		} else if (Objects.equals(this.proofreadAccountType, ProofreadAccountType.RETURN)) {
			baofuReturnDataDao.batchInsert(baofuList);
		} else {
			throw new Exception("proofreadAccountType类型不正确,proofreadAccountType=" + proofreadAccountType);
		}
		// 更新下载成功状态
		proofreadResult.setDownloadSuccess(true);
		proofreadResultDao.updateProofreadResult(proofreadResult);
	}

	// 从数据库查询宝付下载数据
	private List<? extends BaoFuModel> queryDownloadDate(String proofreadDate, String fromSystem, String proofreadType)
			throws Exception {
		Map<String, Object> queryParam = Maps.newHashMap();
		queryParam.put("proofreadDate", proofreadDate);
		queryParam.put("systemFrom", fromSystem);
		queryParam.put("proofreadType", proofreadType);
		if (Objects.equals(proofreadType, ProofreadAccountType.BORROW)) {
			List<BaoFuModelBorrow> borrowList = baofuBorrowDataDao.queryBaoFuBorrowDataList(queryParam);
			return borrowList;
		} else if (Objects.equals(proofreadType, ProofreadAccountType.RETURN)) {
			List<BaoFuModelReturn> returnList = baofuReturnDataDao.queryBaoFuReturnDataList(queryParam);
			return returnList;
		} else {
			throw new Exception("proofreadType类型不正确,proofreadType=" + proofreadType);
		}
	}

	/**
	 * 初始化返回结果
	 * 
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
	public ProofreadResult initProofreadResult() {
		Map<String, Object> queryMap = Maps.newHashMap();
		queryMap.put("fromSystem", this.fromSystem);
		queryMap.put("proofreadType", this.proofreadAccountType);
		queryMap.put("proofreadDate", DateUtils.dateToString(this.date));
		ProofreadResult proofreadResult = proofreadResultDao.queryProofreadResult(queryMap);
		if (Objects.isNull(proofreadResult)) {
			proofreadResult = new ProofreadResult();
			proofreadResult.setFromSystem(this.fromSystem);
			proofreadResult.setProofreadType(this.proofreadAccountType);
			proofreadResult.setProofreadDate(DateUtils.dateToString(this.date));
			proofreadResult.setDownloadSuccess(false);
			proofreadResult.setFailCount(0);
			proofreadResult.setSuccess(false);
			proofreadResultDao.createProofreadResult(proofreadResult);
		}
		// 清空失败原因重新记录
		proofreadResult.setFailReason(StringUtils.EMPTY);
		return proofreadResult;
	}

	/**
	 * 创建文件下载参数
	 * 
	 * @param date
	 * @return
	 */
	private Map<String, Object> generateDownLoadParam(String proofreadDate) {
		Map<String, Object> param = new HashMap<>();
		param.put("version", baoFuProofreadProperties.getVersion());
		if (Objects.equals(proofreadAccountType, ProofreadAccountType.BORROW)) {
			param.put("member_id", baoFuProofreadProperties.getBusinessBorrowNum());// 商户号
			param.put("file_type", baoFuProofreadProperties.getBorrows());// 收款：fi 出款：fo
			param.put("client_ip", baoFuProofreadProperties.getLocalAddress());
		} else if (Objects.equals(proofreadAccountType, ProofreadAccountType.RETURN)) {
			param.put("member_id", baoFuProofreadProperties.getBusinessReturnNum());// 商户号
			param.put("file_type", baoFuProofreadProperties.getReturns());// 收款：fi 出款：fo
			param.put("client_ip", baoFuProofreadProperties.getLocalAddress());
		}
		param.put("settle_date", proofreadDate);// 指定日期的对帐文件（除当天）
		return param;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}

	public void setProofreadAccountType(String proofreadAccountType) {
		this.proofreadAccountType = proofreadAccountType;
	}

	@Override
	public void setDocumentParserHandler(
			DocumentParserHandler<String, List<? extends BaoFuModel>> documentParserHandler) {
		this.documentParserHandler = documentParserHandler;

	}
}

/**
 * 宝付对账文件解析器
 * 
 * @author 墨凉
 *
 */
class BaoFuDocumentParserHandler implements DocumentParserHandler<String, List<? extends BaoFuModel>> {

	private Logger logger = LoggerFactory.getLogger(BaoFuDocumentParserHandler.class);

	private String fileName;

	private String systemFrom;

	private String proofreadDate;

	private String proofreadAccountType;

	public void setProofreadAccountType(String proofreadAccountType) {
		this.proofreadAccountType = proofreadAccountType;
	}

	public void setProofreadDate(String proofreadDate) {
		this.proofreadDate = proofreadDate;
	}

	public void setSystemFrom(String systemFrom) {
		this.systemFrom = systemFrom;
	}

	@Override
	public List<? extends BaoFuModel> handler(String responseStr) throws Exception {
		// resp_code=0000说明返回成功
		int StrOf = responseStr.indexOf("resp_code=0000");
		if (StrOf < 0) {
			logger.error("下载失败！返回结果为：" + responseStr);
			throw new Exception("下载失败!下载返回报文不正确");
		}
		String[] responeseContent = responseStr.split("=");
		// base64解密
		byte[] bytes = SecurityUtil.Base64Decode(responeseContent[3]);
		// 获取压缩文件流
		ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(bytes));
		// 必须先调用getNextEntry方法，否则无法解读文件
		String fileName = zipInputStream.getNextEntry().getName();
		// 获取文件名
		this.fileName = fileName;
		// 调用getNextEntry方法后，字节流转字符流
		BufferedReader br = new BufferedReader(new InputStreamReader(zipInputStream));
		// 第一行为汇总表头，无需解析，直接跳过
		br.readLine();
		String line = "";
		while ((line = br.readLine()) != null) {
			if (line.startsWith("商户号")) {
				// 第二次读到表头，则开始正式解析
				break;
			}
		}
		try {
			if (Objects.equals(proofreadAccountType, ProofreadAccountType.RETURN)) {
				return parseReturn(br);
			}
			if (Objects.equals(proofreadAccountType, ProofreadAccountType.BORROW)) {
				return parseBorrow(br);
			}
		} catch (Exception e) {
			logger.error("解析文本失败！异常为：{}", e);
			throw e;
		}
		throw new Exception("proofreadAccountType参数非法，无法确定对账类型：" + proofreadAccountType);
	}

	/**
	 * 解析宝付还款文件
	 * 
	 * @param br
	 * @return
	 * @throws Exception
	 */
	private List<? extends BaoFuModel> parseReturn(BufferedReader br) throws Exception {
		List<BaoFuModelReturn> list = new ArrayList<>();
		String line = "";
		while ((line = br.readLine()) != null) {
			BaoFuModelReturn model = new BaoFuModelReturn();
			String field[] = line.split("\\|", -1);
			model.setSystemFrom(this.systemFrom);
			model.setDownLoadTime(DateUtils.getCurrentDateTime());
			model.setProofreadDate(this.proofreadDate);
			model.setBusinessNum(field[0]);
			model.setTerminalNum(field[1]);
			model.setExchangeType(field[2]);
			model.setSubExchangeType(field[3]);
			model.setBaofuOrderNum(field[4]);
			model.setBusinessOrderNum(field[5]);
			model.setCaculateTime(Objects.equals(StringUtils.EMPTY, field[6]) ? null : field[6]);
			model.setOrderStatus(field[7]);
			model.setExchangeAmount(field[8]);
			model.setExchangeTip(field[9]);
			model.setBaofuExchangeNum(field[10]);
			model.setOrderCreateTime(Objects.equals(StringUtils.EMPTY, field[11]) ? null : field[11]);
			model.setBusinessRefundOrderNum(field[12]);
			model.setRefundOrderCreateTime(Objects.equals(StringUtils.EMPTY, field[13]) ? null : field[13]);
			list.add(model);
		}
		return list;
	}

	/**
	 * 解析宝付借款文件
	 * 
	 * @param br
	 * @return
	 * @throws Exception
	 */
	private List<? extends BaoFuModel> parseBorrow(BufferedReader br) throws Exception {
		List<BaoFuModelBorrow> list = new ArrayList<>();
		String line = "";
		while ((line = br.readLine()) != null) {
			BaoFuModelBorrow model = new BaoFuModelBorrow();
			String field[] = line.split("\\|", -1);
			model.setSystemFrom(this.systemFrom);
			model.setDownLoadTime(DateUtils.getCurrentDateTime());
			model.setProofreadDate(this.proofreadDate);
			model.setBusinessNum(field[0]);
			model.setTerminalNum(field[1]);
			model.setExchangeType(field[2]);
			model.setSubExchangeType(field[3]);
			model.setBaofuOrderNum(field[4]);
			model.setBusinessOrderNum(field[5]);
			model.setBatchNum(field[6]);
			model.setCaculateTime(Objects.equals(StringUtils.EMPTY, field[7]) ? null : field[7]);
			model.setOrderStatus(field[8]);
			model.setExchangeAmount(field[9]);
			model.setExchangeTip(field[10]);
			model.setRecievePersonNum(field[11]);
			model.setRecievePersonName(field[12]);
			model.setBaofuExchangeNum(field[13]);
			model.setProxyOrderCreateTime(Objects.equals(StringUtils.EMPTY, field[14]) ? null : field[14]);
			model.setRefundOrderCreateTime(Objects.equals(StringUtils.EMPTY, field[15]) ? null : field[15]);
			list.add(model);
		}
		return list;
	}

	@Override
	public String getDocumentName() {
		return fileName;
	}

}
