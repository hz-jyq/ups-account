package com.pgy.ups.account.business.web;


import com.pgy.ups.account.facade.dto.proofread.ProofreadSuccessCountDto;
import com.pgy.ups.account.facade.dubbo.api.ProofreadErrorService;
import com.pgy.ups.account.facade.dubbo.api.ProofreadSuccessService;
import com.pgy.ups.account.facade.dubbo.api.ProofreadSumService;
import com.pgy.ups.account.facade.from.ProofreadErrorForm;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test1")
public class test1 {

    @Resource
    private ProofreadSumService proofreadSumService;

    @Resource
    private ProofreadErrorService proofreadErrorService;

    @Resource
    private ProofreadSuccessService proofreadSuccessService;

    @GetMapping
    public ProofreadSuccessCountDto test1(){
        return proofreadSuccessService.getProofreadSuccessCount(null);
    }


}
