package com.pgy.ups.account.business.dao.mapper;

import java.util.Map;

import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

public interface ProofreadResultDao {
	/**
	 * 通过id查询对账结果
	 * @param id
	 * @return
	 */
    ProofreadResult queryProofreadResultById(Long id);
    
    /**
     * 通过条件查询对账结果
     * @param queryMap
     * @return
     */
	ProofreadResult queryProofreadResult(Map<String, Object> queryMap);
    
	/**
	 * 新增一个对账结果记录
	 * @param proofreadResult
	 * @return
	 */
	Long createProofreadResult(ProofreadResult proofreadResult);
    
	/**
	 * 更新对账结果记录
	 * @param proofreadResult
	 */
	void updateProofreadResult(ProofreadResult proofreadResult);
}
