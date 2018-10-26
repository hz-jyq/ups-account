package com.pgy.ups.account.business.factory.proofread;

import java.util.Date;
import java.util.List;

import com.pgy.ups.account.business.factory.BusinessFactory;
import com.pgy.ups.account.business.handler.proofread.ProofreadHandler;
import com.pgy.ups.account.facade.model.proofread.BaoFuModel;

/**
 * 对账处理器工厂
 * 
 * @author 墨凉
 *
 * @param <T>
 * @param <R>
 */
public interface ProofreadHandlerFactory extends BusinessFactory<ProofreadHandler<String,List<? extends 
		BaoFuModel>>> {

    
	/**
	 * 根据 来源系统名称，借款还款类型，上一次对账结果id获取对账处理器
	 * @param fromSystem
	 * @param proofreadAccountType
	 * @param date
	 * @param proofreadResultId
	 * @return
	 */
	public ProofreadHandler<String, List<? extends BaoFuModel>> getProofreadHandler(String fromSystem, String proofreadAccountType,
			Date date);

}
