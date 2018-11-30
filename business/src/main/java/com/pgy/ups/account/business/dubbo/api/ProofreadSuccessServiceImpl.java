package com.pgy.ups.account.business.dubbo.api;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.business.dao.mapper.ProofreadSuccessDao;

import com.pgy.ups.account.facade.dubbo.api.ProofreadSuccessService;
import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.from.PageInfo;
import com.pgy.ups.account.facade.from.ProofreadResultForm;
import com.pgy.ups.account.facade.from.ProofreadSuccessForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;
import com.pgy.ups.account.facade.model.proofread.ProofreadSuccess;
import com.pgy.ups.common.annotation.PrintExecuteTime;

@Service
public class ProofreadSuccessServiceImpl implements ProofreadSuccessService {


    @Resource
    private ProofreadSuccessDao proofreadSuccessDao;
    
    
    @Override
    @PrintExecuteTime
    public List<ProofreadSuccess> getExcelList(ExcelForm form) {
        return   proofreadSuccessDao.getExcelList(form);

    }

    @Override
    public PageInfo<ProofreadSuccess> getPage(ProofreadSuccessForm form) {
        PageInfo<ProofreadSuccess> page =  new PageInfo<ProofreadSuccess>(proofreadSuccessDao.getPage(form.enablePaging()));
        return page;
    }


}
