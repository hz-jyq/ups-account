package com.pgy.ups.account.business.dubbo.api;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.business.factory.proofread.BaofuProofreadHandlerFactory;
import com.pgy.ups.account.business.factory.proofread.ProofreadHandlerFactory;
import com.pgy.ups.account.business.handler.proofread.ProofreadHandler;
import com.pgy.ups.account.commom.utils.DateUtils;
import com.pgy.ups.account.commom.utils.RedisUtils;
import com.pgy.ups.account.facade.dubbo.api.ProofreadAccountApi;
import com.pgy.ups.account.facade.model.proofread.BaoFuModel;
import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

@Service
public class ProofreadAccountApiImpl implements ProofreadAccountApi {

	private Logger logger = LoggerFactory.getLogger(ProofreadAccountApi.class);

	@Resource(type = BaofuProofreadHandlerFactory.class)
	private ProofreadHandlerFactory baofuProofreadHandlerFactory;

	@Resource
	private RedisUtils redisUtils;

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
		ProofreadResult proofreadResult=null;		
		String proofreadLockKey = "proofreadLocks";
		String requestId = UUID.randomUUID().toString();		
		try {
			while (!redisUtils.redisLock(proofreadLockKey, requestId, 20000)) {
				logger.info("redis获取对账锁失败，lockKey:" + proofreadLockKey + ",lockValue:" + requestId);
				return null;
			}
			logger.info("redis获取对账锁成功，lockKey:" + proofreadLockKey + ",lockValue:" + requestId);
			// 宝付对账处理工厂
			ProofreadHandler<String, List<? extends BaoFuModel>> proofreadHandler = baofuProofreadHandlerFactory
					.getProofreadHandler(fromSystem, proofreadAccountType, date);
			proofreadResult= proofreadHandler.handler(list);			
		} catch (Exception e) {
			logger.error("对账业务处理失败！{}", ExceptionUtils.getStackTrace(e));
		} finally {
			if (redisUtils.redisUnLock(proofreadLockKey, requestId)) {
				logger.info("redis解除对账锁成功，lockKey:" + proofreadLockKey + ",lockValue:" + requestId);
			} else {
				logger.info("redis解除对账锁失败，lockKey:" + proofreadLockKey + ",lockValue:" + requestId);
			}
			logger.info("对账任务结束");
		}
		return proofreadResult;
	}

}
