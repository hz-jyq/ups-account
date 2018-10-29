package com.pgy.ups.account.business.dubbo.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.business.dao.mapper.BaofuBorrowDataDao;
import com.pgy.ups.account.business.dao.mapper.ProofreadSuccessDao;
import com.pgy.ups.account.facade.dubbo.api.ProofreadSuccessService;
import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadSuccess;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProofreadSuccessServiceImpl implements ProofreadSuccessService {


    @Resource
    private ProofreadSuccessDao proofreadSuccessDao;

    @Override
    public List<ProofreadSuccess> getExcelList(ExcelForm form) {
        return   proofreadSuccessDao.getExcelList(form);

    }
}
