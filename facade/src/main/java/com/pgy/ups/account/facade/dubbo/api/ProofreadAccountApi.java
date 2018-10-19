package com.pgy.ups.account.facade.dubbo.api;

import java.util.Date;
import java.util.List;

import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

public interface ProofreadAccountApi {

	/**
	 * 对账方法 由外部系统调用
	 * 
	 * @param list                 需要对账的账目列表
	 * @param fromSystem           系统编码,参考FromSystem类
	 * @param proofreadAccountType 借款 或 还款,参考 ProofreadAccountType类
	 * @param date                 需要对账的日期
	 * @param proofreadResultId    非首次调用是，需传递上一次调用失败的proofreadResultId。首次调用则传null
	 * @return
	 */
	ProofreadResult ProofreadStart(List<BusinessProofreadModel> list, String fromSystem, String proofreadAccountType,
			Date date,Long proofreadResultId);

}
