package com.pgy.ups.account.business.dao.mapper;

import java.util.List;

import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;

public interface BusinessDataDao {
    
	/**
	 * 批量保存业务对账原始数据
	 * @param businessList
	 */
	void batchInsert(List<BusinessProofreadModel> businessList);

}
