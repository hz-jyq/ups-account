package com.pgy.ups.account.facade.dubbo.api;

import com.pgy.ups.account.facade.from.ProofreadSumForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadSum;
import com.pgy.ups.common.page.PageInfo;

public interface ProofreadSumService {

    public PageInfo<ProofreadSum> getPage(ProofreadSumForm Form);

}
