package com.pgy.ups.account.facade.dubbo.api;

import java.util.List;

import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadSuccess;

public interface ProofreadSuccessService {

    List<ProofreadSuccess> getExcelList(ExcelForm form);
}
