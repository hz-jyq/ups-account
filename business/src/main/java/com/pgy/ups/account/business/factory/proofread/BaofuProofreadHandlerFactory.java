package com.pgy.ups.account.business.factory.proofread;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.aop.framework.AopContext;
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
import com.pgy.ups.account.business.dao.mapper.ProofreadErrorDao;
import com.pgy.ups.account.business.dao.mapper.ProofreadResultDao;
import com.pgy.ups.account.business.dao.mapper.ProofreadSumDao;
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
import com.pgy.ups.account.facade.model.proofread.ProofreadError;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;
import com.pgy.ups.account.facade.model.proofread.ProofreadSum;

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
	private ProofreadSumDao proofreadSumDao;

	@Resource
	private BusinessDataDao businessDataDao;
	
	@Resource
	private ProofreadErrorDao proofreadErrorDao;

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
		// 如果对账成功 直接返回
		if (proofreadResult.getSuccess()) {
			logger.error("该日期的对账已经完成！{}", proofreadResult);
			proofreadResult.setFailReason("该日期的对账已经完成");
			return proofreadResult;
		}
		// baofuList用于存储解析文件后的数据
		List<? extends BaoFuModel> baofuList = null;
		// 宝付对账汇总记录
		ProofreadSum baofuProofreadSum = null;
		// 若之前已经下载成功，则无需再次下载，从数据库中根据日期、来源系统、对账类型取出数据即可
		if (proofreadResult.getDownloadSuccess()) {
			try {
				baofuList = queryDownloadDate(proofreadResult.getProofreadDate(), proofreadResult.getFromSystem(),
						proofreadResult.getProofreadType());
			} catch (Exception e) {
				logger.error("从数据库查询宝付下载数据失败！{}", ExceptionUtils.getStackTrace(e));
				return recordProofreadFail(proofreadResult, "从数据库查询宝付下载数据失败");
			}
			try {
				baofuProofreadSum = queryProofreadSum(proofreadResult.getProofreadDate(),
						proofreadResult.getFromSystem(), proofreadResult.getProofreadType());
			} catch (Exception e) {
				logger.error("从数据库查询对账汇总数据失败！{}", ExceptionUtils.getStackTrace(e));
				return recordProofreadFail(proofreadResult, "从数据库查询对账汇总数据失败");
			}

		} else {
			// 下载文件
			Map<String, Object> param = generateDownLoadParam(proofreadResult.getProofreadDate());
			String responseStr = HttpClientUtils.sentRequest(baoFuProofreadProperties.getRequestUrl(), param);
			// 对下载结果进行判断
			if (StringUtils.isEmpty(responseStr)) {
				logger.error("对账任务执行失败，文件下载失败！");
				return recordProofreadFail(proofreadResult, "文件下载失败");
			}
			// 返回结果不为空时，对responseStr进行解析
			try {
				baofuList = documentParserHandler.handler(responseStr);
			} catch (Exception e) {
				logger.error("对账任务执行失败，下载文件解析失败:{}", ExceptionUtils.getStackTrace(e));
				return recordProofreadFail(proofreadResult, "文件解析失败");
			}

			try {
				// 保存baofuList至数据库中并执行中间记录
				baofuProofreadSum = ((BaoFuProofreadHandler) AopContext.currentProxy()).saveProcessStatus(baofuList,
						proofreadResult);
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
		try {
			// 开始对账这样才能开启事务!fuck!!!
			return ((BaoFuProofreadHandler) AopContext.currentProxy()).proofread(proofreadResult, baofuProofreadSum,
					businessList, baofuList);
		} catch (Exception e) {
			logger.error("对账操作失败！失败异常:{}", ExceptionUtils.getStackTrace(e));
			return recordProofreadFail(proofreadResult, "对账操作失败");
		}
	}

	/**
	 * 查询对账汇总结果记录
	 * 
	 * @param proofreadDate
	 * @param fromSystem2
	 * @param proofreadType
	 * @return
	 */
	private ProofreadSum queryProofreadSum(String proofreadDate, String fromSystem, String proofreadType) {
		Map<String, Object> queryParam = Maps.newHashMap();
		queryParam.put("proofreadDate", proofreadDate);
		queryParam.put("fromSystem", fromSystem);
		queryParam.put("proofreadType", proofreadType);
		return proofreadSumDao.queryproofreadSum(queryParam);
	}

	/**
	 * 开始对账
	 * 
	 * @param proofreadResult
	 * @param systemList
	 * @param baofuList
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public ProofreadResult proofread(ProofreadResult proofreadResult, ProofreadSum baofuProofreadSum,
			List<BusinessProofreadModel> businessList, List<? extends BaoFuModel> baofuList) {
		// 添加基础信息
		businessList = businessList.stream().map((e) -> {
			e.setSystemFrom(baofuProofreadSum.getFromSystem());
			e.setProofreadType(baofuProofreadSum.getProofreadType());
			e.setProofreadDate(baofuProofreadSum.getProofreadDate());
			return e;
		}).collect(Collectors.toList());
		// 先删除业务对账原始记录，后保存新的业务对账原始记录
		deleteBusinessDataList(proofreadResult);
		if (!businessList.isEmpty()) {
			businessDataDao.batchInsert(businessList);
		}
		// 初始化宝付对账前汇总结果对象
		baofuProofreadSum.initBeforeProofread(businessList, baofuList);
		// 对账异常工厂
		ProofreadErrorFactory proofreadErrorFactory = new ProofreadErrorFactory(proofreadResult);
		// 用于存放对账异常对象
		List<ProofreadError> errorList = new ArrayList<>();
		// 数据库删除当天差错账信息
		deleletProofreadErrorList(proofreadResult);
		// 开始对账
		Iterator<? extends BaoFuModel> baofuListIterator = baofuList.iterator();
		while (baofuListIterator.hasNext()) {
			BaoFuModel baofuModel = baofuListIterator.next();
			Iterator<BusinessProofreadModel> businessListIterator = businessList.iterator();
			while (businessListIterator.hasNext()) {
				BusinessProofreadModel businessModel = businessListIterator.next();
				// 根据商户订单号进行对比
				if (Objects.equals(businessModel.getBusinessOrderNum(), baofuModel.getBusinessOrderNum())) {
					// 获取双方交易金额
					BigDecimal baofuExchangeAmount = baofuModel.getExchangeAmount();
					BigDecimal businessExchangeAmount = businessModel.getExchangeAmount();
					if (baofuExchangeAmount.compareTo(businessExchangeAmount) == 0) {
						// 相等 则成功金额和成功笔数累加
						baofuProofreadSum.increaseSuccess(baofuExchangeAmount);
					} else {
						// 若不相等，则业务和渠道失败均累加
						baofuProofreadSum.increaseBusinessFail(businessExchangeAmount);
						baofuProofreadSum.increaseChannelFail(baofuExchangeAmount);
						// 构建异常明细对象（错账）
						ProofreadError proofreadError = proofreadErrorFactory.createProofreadError(businessModel,
								baofuModel);
						// 存入列表
						errorList.add(proofreadError);
					}
					// 校对后移除
					businessListIterator.remove();
					baofuListIterator.remove();
					break;
				}
			}
		}
		businessList.forEach(e -> {
			// 构建异常明细对象（业务差账）
			errorList.add(proofreadErrorFactory.createProofreadError(e));
		});
		baofuList.forEach(e -> {
			// 构建异常明细对象（渠道差账）
			errorList.add(proofreadErrorFactory.createProofreadError(e));
		});
		//保存差账记录
		if(CollectionUtils.isNotEmpty(errorList)) {
			proofreadErrorDao.batchInsert(errorList);
		}	
		// 构建宝付对账后汇总结果对象
		baofuProofreadSum.buildAfterProofread(businessList, baofuList);
		// 保存最终对账成功结果
		proofreadSumDao.updateProofreadSum(baofuProofreadSum);
		proofreadResult.setFailReason(StringUtils.EMPTY);
		proofreadResult.setSuccess(true);
		proofreadResultDao.updateProofreadResult(proofreadResult);
		return proofreadResult;
	}
    
	/**
	 *    删除业务对账数据列表
	 * @param proofreadResult
	 */
	private void deleteBusinessDataList(ProofreadResult proofreadResult) {
		Map<String, Object> queryParam = Maps.newHashMap();
		queryParam.put("proofreadDate", proofreadResult.getProofreadDate());
		queryParam.put("fromSystem", proofreadResult.getFromSystem());
		queryParam.put("proofreadType", proofreadResult.getProofreadType());
		ProofreadResultDao.deleteProofreadResult(queryParam);
	}
    
	/**
	 *   删除对账异常结果列表
	 * @param proofreadResult
	 */
	private void deleletProofreadErrorList(ProofreadResult proofreadResult) {
		Map<String, Object> queryParam = Maps.newHashMap();
		queryParam.put("proofreadDate", proofreadResult.getProofreadDate());
		queryParam.put("fromSystem", proofreadResult.getFromSystem());
		queryParam.put("proofreadType", proofreadResult.getProofreadType());
		proofreadErrorDao.deleteProofreadError(queryParam);
	}

	/**
	 * 记录对账任务失败原因，并保存对账结果
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

	// 1.保存宝付下载数据，2.更新对账结果状态记录文件下载成功，3.生成初始化对账汇总记录
	@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
	public ProofreadSum saveProcessStatus(List<? extends BaoFuModel> baofuList, ProofreadResult proofreadResult)
			throws Exception {
		if (Objects.equals(this.proofreadAccountType, ProofreadAccountType.BORROW)) {
			if (CollectionUtils.isNotEmpty(baofuList)) {
				baofuBorrowDataDao.batchInsert(baofuList);
			}
		} else if (Objects.equals(this.proofreadAccountType, ProofreadAccountType.RETURN)) {
			if (CollectionUtils.isNotEmpty(baofuList)) {
				baofuReturnDataDao.batchInsert(baofuList);
			}
		} else {
			throw new Exception("proofreadAccountType类型不正确,proofreadAccountType=" + proofreadAccountType);
		}
		// 更新下载成功状态
		proofreadResult.setDownloadSuccess(true);
		proofreadResultDao.updateProofreadResult(proofreadResult);
		// 初始化生成对账汇总记录
		ProofreadSum baofuProofreadSum = new ProofreadSum(proofreadResult);
		proofreadSumDao.createProofreadSum(baofuProofreadSum);
		return baofuProofreadSum;

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
			return borrowList == null ? Collections.emptyList() : borrowList;
		} else if (Objects.equals(proofreadType, ProofreadAccountType.RETURN)) {
			List<BaoFuModelReturn> returnList = baofuReturnDataDao.queryBaoFuReturnDataList(queryParam);
			return returnList == null ? Collections.emptyList() : returnList;
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
			// 设置商户号
			if (Objects.equals(proofreadResult.getProofreadType(), ProofreadAccountType.BORROW)) {
				proofreadResult.setBusinessNum(baoFuProofreadProperties.getBusinessBorrowNum());
			} else if (Objects.equals(proofreadResult.getProofreadType(), ProofreadAccountType.RETURN)) {
				proofreadResult.setBusinessNum(baoFuProofreadProperties.getBusinessReturnNum());
			}
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
			model.setExchangeAmount(new BigDecimal(field[8]));
			model.setExchangeTip(new BigDecimal(field[9]));
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
			model.setExchangeAmount(new BigDecimal(field[9]));
			model.setExchangeTip(new BigDecimal(field[10]));
			model.setRecievePersonNum(field[11]);
			model.setRecievePersonName(field[12]);
			model.setBaofuExchangeNum(field[13]);
			model.setOrderCreateTime(Objects.equals(StringUtils.EMPTY, field[14]) ? null : field[14]);
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
