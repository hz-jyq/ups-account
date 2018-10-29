package com.pgy.ups.account.facade.dubbo.api;

import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.model.proofread.BaoFuModelBorrow;

import java.util.List;

public interface BaofuBorrowDataService {

    List<BaoFuModelBorrow> getExcelList(ExcelForm form);

}
