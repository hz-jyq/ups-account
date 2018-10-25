package com.pgy.ups.account.business.dao.mapper;

import java.util.List;
import java.util.Map;

import com.pgy.ups.account.facade.model.proofread.ProofreadError;

/**
 * 对账异常明细表操作
 * @author 墨凉
 *
 */
public interface ProofreadErrorDao {
    
	/**
	  *  批量插入异常明细对象
	 * @param errorList
	 */
	void batchInsert(List<ProofreadError> errorList);
    
	/**
	  *  清空所有差错账信息
	 * @param queryParam
	 */
	void deleteProofreadError(Map<String, Object> queryParam);

}
