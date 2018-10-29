package com.pgy.ups.account.business.dubbo.api;

import com.pgy.ups.account.business.dao.mapper.ProofreadResultDao;
import com.pgy.ups.account.facade.dubbo.api.ProofreadResultIService;
import com.pgy.ups.account.facade.from.PageInfo;
import com.pgy.ups.account.facade.from.ProofreadResultForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

import javax.annotation.Resource;

public class ProofreadResultIServicelmpl implements ProofreadResultIService {

    @Resource
    private ProofreadResultDao  proofreadResultDao;

    @Override
    public PageInfo<ProofreadResult> getPage(ProofreadResultForm form) {

        PageInfo<ProofreadResult> page =  new PageInfo<ProofreadResult>(proofreadResultDao.getPage(form.enablePaging()));
        return page;
    }

}
