package com.pgy.ups.account.business;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pgy.ups.account.facade.dubbo.api.ProofreadErrorService;
import com.pgy.ups.account.facade.dubbo.api.ProofreadSuccessService;
import com.pgy.ups.account.facade.dubbo.api.ProofreadSumService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Resource
    private ProofreadSumService proofreadSumService;

    @Resource
    private ProofreadErrorService proofreadErrorService;

    @Resource
    private ProofreadSuccessService proofreadSuccessService;

    @Test
    public void test1(){
       proofreadSuccessService.getProofreadSuccessCount(null);
    }


}
