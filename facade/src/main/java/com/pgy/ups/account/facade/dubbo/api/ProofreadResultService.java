package com.pgy.ups.account.facade.dubbo.api;

import com.pgy.ups.account.facade.from.ProofreadResultForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;
import com.pgy.ups.common.page.PageInfo;

public interface ProofreadResultService {

    public PageInfo<ProofreadResult> getPage(ProofreadResultForm form);
}
