package com.pgy.ups.account.business.dubbo.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.business.dao.mapper.ProofreadSumDao;
import com.pgy.ups.account.facade.dubbo.api.ProofreadSumService;
import com.pgy.ups.account.facade.from.PageInfo;
import com.pgy.ups.account.facade.from.ProofreadSumForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadSum;

import javax.annotation.Resource;

@Service
public class ProofreadSumServiceImpl  implements ProofreadSumService {

    @Resource
    private ProofreadSumDao proofreadSumDao;

    public PageInfo<ProofreadSum> getPage(ProofreadSumForm form){
         PageInfo<ProofreadSum> page =  new PageInfo<ProofreadSum>(proofreadSumDao.getPage(form.enablePaging()));
         return page;
    }


}
