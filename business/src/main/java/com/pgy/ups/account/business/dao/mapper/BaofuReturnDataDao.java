package com.pgy.ups.account.business.dao.mapper;

import java.util.List;
import java.util.Map;

import com.pgy.ups.account.facade.model.proofread.BaoFuModel;
import com.pgy.ups.account.facade.model.proofread.BaoFuModelReturn;

/**
 * 宝付还款数据
 * @author 墨凉
 *
 */
public interface BaofuReturnDataDao {
	
	/**
	 * 查询下载的宝付还款数据
	 * @param proofreadDate
	 * @return
	 */
	List<BaoFuModelReturn> queryBaoFuReturnDataList(Map<String, Object> queryParam);
    
	/**
	 * 批量插入数据
	 * @param sourceList
	 */
	void batchInsert(List<? extends BaoFuModel> sourceList);

}
