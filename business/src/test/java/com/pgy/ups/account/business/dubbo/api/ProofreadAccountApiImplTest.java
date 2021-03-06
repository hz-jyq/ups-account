package com.pgy.ups.account.business.dubbo.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pgy.ups.account.business.dao.mapper.BaofuReturnDataDao;
import com.pgy.ups.account.facade.constant.FromSystem;
import com.pgy.ups.account.facade.constant.ProofreadAccountType;
import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;
import com.pgy.ups.common.utils.DateUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProofreadAccountApiImplTest {

	@Resource
	private BaofooProofreadAccountApiImpl ProofreadAccountApiImpl;

	@Resource
	private BaofuReturnDataDao baofuReturnDataDao;


	@Test
	public void TestFail() throws InterruptedException {

		BusinessProofreadModel m1 = new BusinessProofreadModel();
		m1.setBorrowNum("11111");
		m1.setBusinessOrderNum("10188");
		m1.setExchangeAmount(new BigDecimal("102"));
		m1.setBusinessOrderStatuts("1");
		BusinessProofreadModel m2 = new BusinessProofreadModel();
		m2.setBorrowNum("22222");
		m2.setBusinessOrderNum("10191");
		m2.setExchangeAmount(new BigDecimal("1000"));
		m2.setBusinessOrderStatuts("2");
		BusinessProofreadModel m3 = new BusinessProofreadModel();
		m3.setBorrowNum("33333");
		m3.setBusinessOrderNum("ac38a0a7f3c243b783d61f83127191eb");
		m3.setExchangeAmount(new BigDecimal("1000"));// 这个是错的1750
		m3.setBusinessOrderStatuts("3");
		BusinessProofreadModel m4 = new BusinessProofreadModel();
		m4.setBorrowNum("4444");
		m4.setBusinessOrderNum("e5c28cdf70614d928f7d0e4c39cfb5ed");
		m4.setExchangeAmount(new BigDecimal("10000"));
		m4.setBusinessOrderStatuts("4");
		BusinessProofreadModel m5 = new BusinessProofreadModel();
		m5.setBorrowNum("5555");
		m5.setBusinessOrderNum("abc");
		m5.setExchangeAmount(new BigDecimal("10000"));
		m5.setBusinessOrderStatuts("5");
		List<BusinessProofreadModel> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		list.add(m5);
		
		new Thread(() -> {
			System.out.println(ProofreadAccountApiImpl.ProofreadStart(list, FromSystem.MEI_QI, ProofreadAccountType.BORROW,
					DateUtils.stringToDate("2019-03-10")));
		}).start();

		/*new Thread(() -> {
			System.out.println(ProofreadAccountApiImpl.ProofreadStart(list, FromSystem.MEI_QI, ProofreadAccountType.RETURN,
					DateUtils.stringToDate("2018-11-15")));
		}).start();*/
		Thread.sleep(100000);

		/*
		 * ProofreadAccountApiImpl.ProofreadStart(list, FromSystem.MEI_QI,
		 * ProofreadAccountType.RETURN, DateUtils.stringToDate("2018-10-29"));
		 * ProofreadAccountApiImpl.ProofreadStart(list, FromSystem.MEI_QI,
		 * ProofreadAccountType.BORROW, DateUtils.stringToDate("2018-10-29"));
		 */
	}

}
