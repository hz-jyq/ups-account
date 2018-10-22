package com.pgy.ups.account.business.dubbo.api;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.business.factory.proofread.BaofuProofreadHandlerFactory;
import com.pgy.ups.account.business.factory.proofread.ProofreadHandlerFactory;
import com.pgy.ups.account.business.handler.proofread.ProofreadHandler;
import com.pgy.ups.account.facade.dubbo.api.ProofreadAccountApi;
import com.pgy.ups.account.facade.model.proofread.BaoFuModel;
import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

@Service
public class ProofreadAccountApiImpl implements ProofreadAccountApi {

	@Resource(type = BaofuProofreadHandlerFactory.class)
	private ProofreadHandlerFactory baofuProofreadHandlerFactory;

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
		// 宝付对账处理工厂
		ProofreadHandler<String, List<? extends BaoFuModel>> proofreadHandler = baofuProofreadHandlerFactory
				.getProofreadHandler(fromSystem, proofreadAccountType, date);
		try {
			return proofreadHandler.handler(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
