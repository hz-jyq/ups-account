package com.pgy.ups.account.business.dao.mapper;

import java.util.List;
import java.util.Map;

import com.pgy.ups.account.facade.model.proofread.BaoFuModel;
import com.pgy.ups.account.facade.model.proofread.BaoFuModelBorrow;

/**
 * 宝付借款数据
 * @author 墨凉
 *
 */
public interface BaofuBorrowDataDao {
    
	
	/**
	 * 查询下载的宝付借款数据
	 * @param proofreadDate
	 * @return
	 */
	List<BaoFuModelBorrow> queryBaoFuBorrowDataList(Map<String,Object> queryMap);
    
	/**
	 * 批量插入数据
	 * @param sourceList
	 */
	void batchInsert(List<? extends BaoFuModel> sourceList);

}
