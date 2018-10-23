package com.pgy.ups.account.business.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.pgy.ups.account.business.configuration.properties.BaoFuProofreadProperties;
import com.pgy.ups.account.facade.constant.ProofreadAccountType;
import com.pgy.ups.account.facade.model.proofread.BaoFuModel;
import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

/**
 * 
 * @author acer
 *
 */
public class BaofuProofreadSum extends ProofreadSum {

	private static final long serialVersionUID = 184202323910238627L;

	private ProofreadResult proofreadResult;

	private List<BusinessProofreadModel> businessList;

	private List<? extends BaoFuModel> baofuList;

	private BigDecimal successTotalMoneyBigDecimal;

	private BigDecimal failTotalMoneyBigDecimal;

	public BaofuProofreadSum(ProofreadResult proofreadResult, List<BusinessProofreadModel> businessList,
			List<? extends BaoFuModel> baofuList, BaoFuProofreadProperties pro) {
		this.proofreadResult = proofreadResult;
		businessList = new ArrayList<>(businessList);
		baofuList = new ArrayList<>(baofuList);
		successTotalMoneyBigDecimal = BigDecimal.ZERO;
		failTotalMoneyBigDecimal = BigDecimal.ZERO;
		// 设置商户号
		if (Objects.equals(proofreadResult.getProofreadType(), ProofreadAccountType.BORROW)) {
			this.setBusinessNum(pro.getBusinessBorrowNum());
		} else if (Objects.equals(proofreadResult.getProofreadType(), ProofreadAccountType.RETURN)) {
			this.setBusinessNum(pro.getBusinessReturnNum());
		}
		this.successTotal = 0;
		this.failTotal = 0;
	}

	/**
	 * 构建生成部分参数
	 */
	@Override
	public ProofreadSum build() {
		this.setProofreadWay("宝付");
		// 业务总笔数
		this.setBusinessTotal(businessList.size());
		// 渠道（宝付）总笔数
		this.setChannelTotal(baofuList.size());
		// 业务总金额
		this.setBusinessTotalMoney(businessList.stream().map(e -> {
			return new BigDecimal(e.getExchangeAmount());
		}).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
		// 宝付总金额
		this.setChannelTotalMoney(baofuList.stream().map(e -> {
			return new BigDecimal(e.getExchangeAmount());
		}).reduce(BigDecimal.ZERO, BigDecimal::add).toString());

		this.setFromSystem(proofreadResult.getFromSystem());
		this.setProofreadType(proofreadResult.getProofreadType());
		this.setProofreadDate(proofreadResult.getProofreadDate());
		return this;
	}

	public void increaseSuccessMoney(BigDecimal money) {

	}

	public void increaseFailMoney(BigDecimal money) {

	}

	// 成功对账数加1
	public void increaseSuccessTotal() {
		this.successTotal++;
	}

	// 失败对账数加1
	public void increaseFailTotal() {
		this.failTotal++;
	}

}
