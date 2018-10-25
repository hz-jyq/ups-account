package com.pgy.ups.account.business.dao.mapper;

import java.util.Map;

import com.pgy.ups.account.facade.model.proofread.ProofreadSum;

public interface ProofreadSumDao {
    
	/**
	 * 创建对账汇总结果
	 * @param proofreadSum
	 */
	void createProofreadSum(ProofreadSum proofreadSum);
   
	/**
	 *  更新对账汇总结果
	 * @param proofreadSum
	 */
	void updateProofreadSum(ProofreadSum proofreadSum);
	
    /**
         * 查询对账结果汇总记录
     * @param queryParam
     * @return
     */
	ProofreadSum queryproofreadSum(Map<String, Object> queryParam);

}
