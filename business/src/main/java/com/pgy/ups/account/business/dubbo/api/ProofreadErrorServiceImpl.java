package com.pgy.ups.account.business.dubbo.api;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.business.dao.mapper.ProofreadErrorDao;
import com.pgy.ups.account.facade.dto.proofread.ProofreadErrorCount;
import com.pgy.ups.account.facade.dto.proofread.ProofreadErrorCountDto;
import com.pgy.ups.account.facade.dubbo.api.ProofreadErrorService;
import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.from.PageInfo;
import com.pgy.ups.account.facade.from.ProofreadErrorForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadError;
import com.pgy.ups.common.annotation.PrintExecuteTime;

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
    @PrintExecuteTime
    public List<ProofreadError> getExcelList(ExcelForm form) {
        List<ProofreadError>  list = proofreadErrorDao.getExcelList(form);
        for(ProofreadError proofreadError:list){
            proofreadError.setProofreadType(proofreadError.getFromSystem() + "-" +  proofreadError.getProofreadType());
        }
        return list;
    }

    @Override
    public ProofreadErrorCountDto getProofreadErrorCount(ProofreadErrorForm form) {
      ProofreadErrorCountDto  dto = new ProofreadErrorCountDto();
      form.setProofreadField("businessExchangeMoney");
      ProofreadErrorCount proofreadErrorCount =  proofreadErrorDao.getProofreadErrorCount(form);
      dto.createDto(proofreadErrorCount);

      form.setProofreadField("channelExchangeMoney");
      proofreadErrorCount =  proofreadErrorDao.getProofreadErrorCount(form);
       dto.createDto(proofreadErrorCount);
      return dto;
    }

    @Override
    public void cancelProofread(Long id,String remark,String updateUser) {
        ProofreadError error = new ProofreadError();
        error.setFlowStatus("04");
        error.setUpdateUser(updateUser);
        error.setRemark(remark);
        proofreadErrorDao.updateByPrimaryKeySelective(error);
    }

    @Override
    public void reservedProofread(Long id,String updateUser) {
        ProofreadError error = new ProofreadError();
        error.setId(id);
        error.setFlowStatus("02");
        error.setUpdateUser(updateUser);
        proofreadErrorDao.updateByPrimaryKeySelective(error);
    }
}
