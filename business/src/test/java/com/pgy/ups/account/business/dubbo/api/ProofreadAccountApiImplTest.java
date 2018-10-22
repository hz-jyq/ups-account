package com.pgy.ups.account.business.dubbo.api;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pgy.ups.account.commom.utils.DateUtils;
import com.pgy.ups.account.facade.constant.FromSystem;
import com.pgy.ups.account.facade.constant.ProofreadAccountType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProofreadAccountApiImplTest{
	 
	 @Resource
	 private ProofreadAccountApiImpl ProofreadAccountApiImpl;
	 
	 @Test
	 public void Test() {
		 ProofreadAccountApiImpl.ProofreadStart(null, FromSystem.MEI_QI, ProofreadAccountType.BORROW, DateUtils.addDay(new Date(), -1));
	 }
	
}
