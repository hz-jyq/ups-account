package com.pgy.ups.account.facade.dubbo.api;


import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.from.PageInfo;
import com.pgy.ups.account.facade.from.ProofreadErrorForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadError;
import com.pgy.ups.account.facade.model.proofread.ProofreadSuccess;

import java.util.List;


public interface ProofreadErrorService {

    public PageInfo<ProofreadError> getPage(ProofreadErrorForm Form);

    List<ProofreadError> getExcelList(ExcelForm form);

}
