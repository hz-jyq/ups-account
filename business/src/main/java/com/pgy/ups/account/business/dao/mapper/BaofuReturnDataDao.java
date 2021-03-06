package com.pgy.ups.account.business.dao.mapper;

import java.util.List;
import java.util.Map;

import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.model.proofread.BaoFuModel;
import com.pgy.ups.account.facade.model.proofread.BaoFuModelReturn;
import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;

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
	List<BaoFuModel> queryBaoFuReturnDataList(Map<String, Object> queryParam);
    
	/**
	 * 批量插入数据
	 * @param sourceList
	 */
	void batchInsert(List<? extends BaoFuModel> sourceList);
	
    //临时的，生产需要删除 
	List<BusinessProofreadModel> query();
    
	/**
	 * 获取excel数据
	 * @param from
	 * @return
	 */
	List<BaoFuModelReturn> getExcelList(ExcelForm from);
}
