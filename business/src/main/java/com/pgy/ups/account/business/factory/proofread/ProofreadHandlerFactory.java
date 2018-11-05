package com.pgy.ups.account.business.factory.proofread;

import java.util.Date;

import com.pgy.ups.account.business.factory.BusinessFactory;
import com.pgy.ups.account.business.handler.proofread.ProofreadHandler;

/**
 * 对账处理器工厂
 * 
 * @author 墨凉
 *
 * @param <T>
 * @param <R>
 */
public interface ProofreadHandlerFactory<T,E> extends BusinessFactory<ProofreadHandler<T,E>>{

    
	/**
	 * 根据 来源系统名称，借款还款类型，上一次对账结果id获取对账处理器
	 * @param fromSystem
	 * @param proofreadAccountType
	 * @param date
	 * @param proofreadResultId
	 * @return
	 */
	public ProofreadHandler<T, E> getProofreadHandler(String fromSystem, String proofreadAccountType,
			Date date);

}
