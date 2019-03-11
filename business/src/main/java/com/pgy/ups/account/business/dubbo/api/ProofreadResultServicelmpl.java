package com.pgy.ups.account.business.dubbo.api;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.business.dao.mapper.ProofreadResultDao;
import com.pgy.ups.account.facade.dubbo.api.ProofreadResultService;
import com.pgy.ups.account.facade.from.ProofreadResultForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;
import com.pgy.ups.common.page.PageInfo;

@Service
public class ProofreadResultServicelmpl implements ProofreadResultService {

    @Resource
    private ProofreadResultDao  proofreadResultDao;

    @Override
    public PageInfo<ProofreadResult> getPage(ProofreadResultForm form) {

        PageInfo<ProofreadResult> page =  new PageInfo<ProofreadResult>(proofreadResultDao.getPage(form.enablePaging()));
        return page;
    }

}
