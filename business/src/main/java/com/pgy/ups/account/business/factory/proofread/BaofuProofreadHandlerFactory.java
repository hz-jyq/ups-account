package com.pgy.ups.account.business.factory.proofread;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pgy.ups.account.business.configurationproperties.BaoFuProofreadProperties;
import com.pgy.ups.account.business.handler.proofread.DocumentParserHandler;
import com.pgy.ups.account.business.handler.proofread.ProofreadHandler;
import com.pgy.ups.account.commom.utils.DateUtils;
import com.pgy.ups.account.commom.utils.HttpClientUtils;
import com.pgy.ups.account.commom.utils.SecurityUtil;
import com.pgy.ups.account.facade.constant.ProofreadAccountType;
import com.pgy.ups.account.facade.model.proofread.BaoFuModel;
import com.pgy.ups.account.facade.model.proofread.BaoFuModelReturn;
import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

/**
 * 宝付对账处理器工厂
 * 
 * @author 米粮
 *
 */
@Component
public class BaofuProofreadHandlerFactory implements ProofreadHandlerFactory {
	/**
	 * 宝付对账处理器
	 */
	@Resource(type = BaoFuProofreadHandler.class)
	private ProofreadHandler<String, List<BaoFuModel>> baofuProofreadHandler;

	public ProofreadHandler<String, List<BaoFuModel>> getProofreadHandler(String fromSystem,
			String proofreadAccountType, Date date, Long proofreadResultId) {
		// 设置下载文本解析器
		BaoFuDocumentParserHandler baoFuDocumentParserHandler = new BaoFuDocumentParserHandler();
		// 设置借款或者还款解析方式
		baoFuDocumentParserHandler.setProofreadAccountType(proofreadAccountType);

		// 设置下载文件解析器
		baofuProofreadHandler.setDocumentParserHandler(baoFuDocumentParserHandler);
		// 设置要对账的系统
		baofuProofreadHandler.setFromSystem(fromSystem);
		// 设置借款或者还款
		baofuProofreadHandler.setProofreadAccountType(proofreadAccountType);

		// 设置文件下载日期
		baofuProofreadHandler.setDate(date);
		// 设置上一次对账结果Id(非首次对账需要传递)
		baofuProofreadHandler.setProofreadResultId(proofreadResultId);

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
class BaoFuProofreadHandler implements ProofreadHandler<String, List<BaoFuModel>> {

	private Logger logger = LoggerFactory.getLogger(BaoFuProofreadHandler.class);

	@Resource
	private BaoFuProofreadProperties baoFuProofreadProperties;

	private DocumentParserHandler<String, List<BaoFuModel>> documentParserHandler;

	private String fromSystem;

	private String proofreadAccountType;

	private Long proofreadResultId;

	private Date date;

	public BaoFuProofreadHandler() {
	}

	/**
	 * 处理流程
	 */
	@Override
	public ProofreadResult handler(List<BusinessProofreadModel> list) {
		// successFlag记录执行最终是否成功
		boolean successFlag = true;
		// 初始化返回结果
		ProofreadResult proofreadResult = initProofreadResult();
		// 下载文件
		Map<String, Object> param = generateDownLoadParam(date);
		String responseStr = HttpClientUtils.sentRequest(baoFuProofreadProperties.getRequestUrl(), param);
		if (StringUtils.isEmpty(responseStr)) {
			successFlag = false;
			proofreadResult.appendFailReason("文件下载失败");
			proofreadResult.setSuccess(successFlag);
			logger.error("对账任务执行失败，文件下载失败:");
			return proofreadResult;
		}
		// 返回结果不为空时，对responseStr进行解析
		List<BaoFuModel> sourceList = null;
		try {
			sourceList = documentParserHandler.handler(responseStr);
		} catch (Exception e) {
			successFlag = false;
			proofreadResult.appendFailReason("文件解析失败");
			proofreadResult.setSuccess(successFlag);
			logger.error("对账任务执行失败，下载文件解析失败:{}", ExceptionUtils.getStackTrace(e));
			return proofreadResult;
		}
		//解析文件获取为null时
		if (Objects.isNull(sourceList)) {
			successFlag = false;
			proofreadResult.appendFailReason("未从文件中获取到宝付数据");
			proofreadResult.setSuccess(successFlag);
			logger.error("对账任务执行失败，未从文件中获取到宝付数据");
			return proofreadResult;
		}
		// 接受原系统的数据list为null时
		if (Objects.isNull(list)) {
			// 标记设为失败
			successFlag = false;
			proofreadResult.appendFailReason("没有接受到系统来源数据");
			logger.error("对账任务执行失败，没有接受到系统来源数据:{}", list);
		}
		// list数据入表
		return proofreadResult;
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

	public void setProofreadResultId(Long proofreadResultId) {
		this.proofreadResultId = proofreadResultId;
	}

	@Override
	public void setDocumentParserHandler(DocumentParserHandler<String, List<BaoFuModel>> documentParserHandler) {
		this.documentParserHandler = documentParserHandler;

	}

	/**
	 * 初始化返回结果
	 * 
	 * @return
	 */
	private ProofreadResult initProofreadResult() {
		ProofreadResult proofreadResult = new ProofreadResult();

		proofreadResult.setDownloadSuccess(false);
		proofreadResult.setExcuteTime(DateUtils.getCurrentDateTime());
		proofreadResult.setFailCount(0);
		proofreadResult.setFailReason(StringUtils.EMPTY);
		proofreadResult.setFromSystem(fromSystem);
		proofreadResult.setProofreadResultId(proofreadResultId);

		proofreadResult.setProofreadType(proofreadAccountType);
		proofreadResult.setSuccess(false);
		return proofreadResult;
	}

	/**
	 * 创建文件下载参数
	 * 
	 * @param date
	 * @return
	 */
	private Map<String, Object> generateDownLoadParam(Date date) {
		// 默认为昨天对账
		if (Objects.isNull(date)) {
			date = DateUtils.getYesterday();
		}
		Map<String, Object> param = new HashMap<>();
		param.put("version", baoFuProofreadProperties.getVersion());
		if (Objects.equals(proofreadAccountType, ProofreadAccountType.BORROW)) {
			param.put("member_id", baoFuProofreadProperties.getBusinessBorrowNum());// 商户号
			param.put("file_type", baoFuProofreadProperties.getBorrows());// 收款：fi 出款：fo
		} else if (Objects.equals(proofreadAccountType, ProofreadAccountType.RETURN)) {
			param.put("member_id", baoFuProofreadProperties.getBusinessReturnNum());// 商户号
			param.put("file_type", baoFuProofreadProperties.getReturns());// 收款：fi 出款：fo
		}
		param.put("settle_date", DateUtils.dateToString(date));// 指定日期的对帐文件（除当天）
		return param;
	}

}

/**
 * 宝付对账文件解析器
 * 
 * @author 墨凉
 *
 */
@Component
class BaoFuDocumentParserHandler implements DocumentParserHandler<String, List<BaoFuModel>> {

	private String fileName;

	private String proofreadAccountType;

	public void setProofreadAccountType(String proofreadAccountType) {
		this.proofreadAccountType = proofreadAccountType;
	}

	@Override
	public List<BaoFuModel> handler(String responseStr) throws Exception {
		// base64解密
		String[] responeseContent = responseStr.split("=");
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
		if (Objects.equals(proofreadAccountType, ProofreadAccountType.RETURN)) {
			return parseReturn(br);
		}
		if (Objects.equals(proofreadAccountType, ProofreadAccountType.BORROW)) {
			return parseBorrow(br);
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
	private List<BaoFuModel> parseReturn(BufferedReader br) throws Exception {
		List<BaoFuModel> list = new ArrayList<>();
		String line = "";
		while ((line = br.readLine()) != null) {
			BaoFuModelReturn model = new BaoFuModelReturn();
			String field[] = line.split("|");

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
	private List<BaoFuModel> parseBorrow(BufferedReader br) throws Exception {
		return null;
	}

	@Override
	public String getDocumentName() {
		return fileName;
	}

}
