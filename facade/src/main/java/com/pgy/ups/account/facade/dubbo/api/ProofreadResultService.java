package com.pgy.ups.account.facade.dubbo.api;

import com.pgy.ups.account.facade.from.PageInfo;
import com.pgy.ups.account.facade.from.ProofreadResultForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

public interface ProofreadResultService {

    public PageInfo<ProofreadResult> getPage(ProofreadResultForm form);
}
