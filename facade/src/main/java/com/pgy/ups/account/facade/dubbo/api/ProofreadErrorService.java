package com.pgy.ups.account.facade.dubbo.api;


import java.util.List;

import com.pgy.ups.account.facade.dto.proofread.ProofreadErrorCountDto;
import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.from.PageInfo;
import com.pgy.ups.account.facade.from.ProofreadErrorForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadError;


public interface ProofreadErrorService {

    public PageInfo<ProofreadError> getPage(ProofreadErrorForm Form);

    List<ProofreadError> getExcelList(ExcelForm form);


   public ProofreadErrorCountDto getProofreadErrorCount(ProofreadErrorForm form);
}
