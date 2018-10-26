package com.pgy.ups.account.facade.dubbo.api;

import com.pgy.ups.account.facade.from.PageInfo;
import com.pgy.ups.account.facade.from.ProofreadSumForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadSum;

public interface ProofreadSumService {

    public PageInfo<ProofreadSum> getPage(ProofreadSumForm Form);

}
