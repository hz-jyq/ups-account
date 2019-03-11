package com.pgy.ups.account.facade.dubbo.api;

import com.pgy.ups.account.facade.dto.proofread.ProofreadSuccessCountDto;
import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.from.ProofreadSuccessForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadSuccess;
import com.pgy.ups.common.page.PageInfo;

import java.util.List;

public interface ProofreadSuccessService {

    List<ProofreadSuccess> getExcelList(ExcelForm form);

    public PageInfo<ProofreadSuccess> getPage(ProofreadSuccessForm form);

    public ProofreadSuccessCountDto getProofreadSuccessCount(ProofreadSuccessForm form);
}
