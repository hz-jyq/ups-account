package com.pgy.ups.account.business.dao.mapper;

import java.util.List;
import java.util.Map;

import com.pgy.ups.account.facade.model.proofread.ProofreadSuccess;

/**
 * 对账成功明细表操作
 * @author 墨凉
 *
 */
public interface ProofreadSuccessDao {
    
	/**
	  *  批量插入成功明细对象
	 * @param errorList
	 */
	void batchInsert(List<ProofreadSuccess> successList);
    
	/**
	  *  清空所有成功账信息
	 * @param queryParam
	 */
	void deleteProofreadSuccess(Map<String, Object> queryParam);

}
