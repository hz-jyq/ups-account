package com.pgy.ups.account.facade.dubbo.api;


import java.util.List;

import com.pgy.ups.account.facade.dto.proofread.ProofreadErrorCountDto;
import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.from.ProofreadErrorForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadError;
import com.pgy.ups.common.page.PageInfo;


public interface ProofreadErrorService {

    public PageInfo<ProofreadError> getPage(ProofreadErrorForm Form);

    List<ProofreadError> getExcelList(ExcelForm form);


   public ProofreadErrorCountDto getProofreadErrorCount(ProofreadErrorForm form);

    /**
     *
     * @param id
     * @param remark
     * @param updateUser
     */
   int cancelProofread(Long id,String remark,String updateUser);

    /**
     *
     * @param id
     * @param updateUser
     */
    int  reservedProofread(Long id,String updateUser);
}
