package com.pgy.ups.account.facade.dubbo.api;

import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.model.proofread.BaoFuModelReturn;

import java.util.List;

public interface BaofuReturnDataService {

    List<BaoFuModelReturn> getExcelList(ExcelForm form);
}
