package com.pgy.ups.account.business.web;

import com.pgy.ups.account.facade.dubbo.api.ProofreadSumService;
import com.pgy.ups.account.facade.from.PageInfo;
import com.pgy.ups.account.facade.from.ProofreadSumForm;
import com.pgy.ups.account.facade.model.proofread.ProofreadSum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test1")
public class test1 {

    @Resource
    private ProofreadSumService proofreadSumService;

    @GetMapping
    public  PageInfo<ProofreadSum>  test(){
        PageInfo<ProofreadSum> page   =  proofreadSumService.getPage(new ProofreadSumForm());
        return page;
    }
}
