package com.pgy.ups.account.bussiness.dubbo.api;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.facade.dubbo.api.ProofreadAccountApi;
import com.pgy.ups.account.facade.model.proofread.BussinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

@Service
public class ProofreadAccountApiImpl implements ProofreadAccountApi{
	
	
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
	public ProofreadResult ProofreadInStart(List<BussinessProofreadModel> list, String fromSystem,
			String proofreadAccountType, Long proofreadResultId) {
		// TODO Auto-generated method stub
		return null;
	}

      
}
