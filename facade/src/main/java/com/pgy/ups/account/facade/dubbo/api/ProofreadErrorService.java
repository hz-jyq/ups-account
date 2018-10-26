package com.pgy.ups.account.facade.dubbo.api;


import com.pgy.ups.account.facade.from.PageInfo;
import com.pgy.ups.account.facade.from.ProofreadErrorForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadError;


public interface ProofreadErrorService {

    public PageInfo<ProofreadError> getPage(ProofreadErrorForm Form);

}
