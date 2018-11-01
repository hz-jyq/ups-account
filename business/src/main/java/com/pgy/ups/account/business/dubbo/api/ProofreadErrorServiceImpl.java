package com.pgy.ups.account.business.dubbo.api;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.business.dao.mapper.ProofreadErrorDao;
import com.pgy.ups.account.facade.dubbo.api.ProofreadErrorService;
import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.from.PageInfo;
import com.pgy.ups.account.facade.from.ProofreadErrorForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadError;

import java.util.List;

@Service
public class ProofreadErrorServiceImpl  implements ProofreadErrorService {

    @Resource
    private ProofreadErrorDao  proofreadErrorDao;

    @Override
    public PageInfo<ProofreadError> getPage(ProofreadErrorForm form) {
        PageInfo<ProofreadError> page =  new PageInfo<ProofreadError>(proofreadErrorDao.getPage(form.enablePaging()));
        return page;
    }

    @Override
    public List<ProofreadError> getExcelList(ExcelForm form) {
        List<ProofreadError>  list = proofreadErrorDao.getExcelList(form);
        for(ProofreadError proofreadError:list){
            proofreadError.setProofreadType(proofreadError.getFromSystem() + "-" +  proofreadError.getProofreadType());
        }
        return list;
    }
}
