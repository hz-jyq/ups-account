package com.pgy.ups.account.business.dubbo.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.business.dao.mapper.ProofreadSuccessDao;
import com.pgy.ups.account.facade.dto.proofread.ProofreadCount;
import com.pgy.ups.account.facade.dto.proofread.ProofreadSuccessCountDto;
import com.pgy.ups.account.facade.dubbo.api.ProofreadSuccessService;
import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.from.PageInfo;
import com.pgy.ups.account.facade.from.ProofreadSuccessForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadSuccess;
import com.pgy.ups.common.annotation.PrintExecuteTime;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public ProofreadSuccessCountDto getProofreadSuccessCount(ProofreadSuccessForm form) {
        ProofreadSuccessCountDto dto = new ProofreadSuccessCountDto();
        ProofreadCount proofreadCount = proofreadSuccessDao.getProofreadSuccessCount(form);
        dto.setProofreadCount(proofreadCount.getProofreadCount());
        dto.setProofreadTotalMoney(proofreadCount.getProofreadTotalMoney());
        return dto;
    }


}
