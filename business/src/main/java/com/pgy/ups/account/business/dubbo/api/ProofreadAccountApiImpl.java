package com.pgy.ups.account.business.dubbo.api;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.business.factory.proofread.BaofuProofreadHandlerFactory;
import com.pgy.ups.account.business.handler.proofread.ProofreadHandler;

import com.pgy.ups.account.facade.dubbo.api.ProofreadAccountApi;
import com.pgy.ups.account.facade.model.proofread.BaoFuModel;
import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;
import com.pgy.ups.common.annotation.PrintExecuteTime;
import com.pgy.ups.common.annotation.SingleThreadQueue;
import com.pgy.ups.common.utils.DateUtils;


@Service
public class ProofreadAccountApiImpl implements ProofreadAccountApi {

	private Logger logger = LoggerFactory.getLogger(ProofreadAccountApi.class);

	@Resource(type = BaofuProofreadHandlerFactory.class)
	private BaofuProofreadHandlerFactory baofuProofreadHandlerFactory;


	/**
	 * 对账方法 由外部系统调用
	 * 
	 * @param list                 需要对账的账目列表
	 * @param fromSystem           系统编码,参考FromSystem类
	 * @param proofreadAccountType 借款 或 还款,参考 ProofreadAccountType类
	 * @param proofreadResultId    非首次调用是，需传递上一次调用失败的proofreadResultId。首次调用则传null
	 * @return
	 */

	@Override
	//@RedisLock(name = "对账锁", key = "proofreadLock", expireTime = 60000)
	@PrintExecuteTime
	@SingleThreadQueue(name="系统对账",timeout=60000)
	public ProofreadResult ProofreadStart(List<BusinessProofreadModel> list, String fromSystem,
			String proofreadAccountType, Date date) {
		logger.info(
				"对账任务开始，入参为：proofreadAccountType=" + proofreadAccountType + ",date=" + DateUtils.dateToString(date));
		if (Objects.isNull(list)) {
			logger.error("对账业务处理失败！List<BusinessProofreadModel>不能为null");
			return null;
		}
		if (StringUtils.isEmpty(fromSystem) || StringUtils.isEmpty(proofreadAccountType)) {
			logger.error("对账业务处理失败！fromSystem和proofreadAccountType不能为空");
			return null;
		}
		// 默认对账时间为昨天
		if (Objects.isNull(date)) {
			date = DateUtils.getYesterday();
		}
		// 宝付对账处理工厂
		ProofreadHandler<String, List<BaoFuModel>> proofreadHandler = baofuProofreadHandlerFactory
				.getProofreadHandler(fromSystem, proofreadAccountType, date);
		try {
			ProofreadResult proofreadResult = proofreadHandler.handler(list);
			logger.info("对账任务结束！{}",proofreadResult);
			return proofreadResult;
		} catch (Exception e) {
			logger.info("对账任务结束！但出现异常{}", e);
			return null;
		}
	}
	
}
