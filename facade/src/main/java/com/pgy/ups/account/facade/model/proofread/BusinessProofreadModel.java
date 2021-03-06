package com.pgy.ups.account.facade.model.proofread;

import java.math.BigDecimal;

import com.pgy.ups.account.facade.model.Model;

public class BusinessProofreadModel extends Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8549531618936728275L;
	
	private Long id;
	
	//商户订单号
	private String businessOrderNum;


	//系统编码 01：美期 02：米融  03：秒呗',
	private String fromSystem;
	
	//支付渠道 宝付 01
	private String channel;
	//对账类型 01：借款对账  02：还款对账',
	private String proofreadType;
	
	//对账时间
	private String proofreadDate;
	
	//交易金额
	private BigDecimal exchangeAmount;
	
	//业务订单状态
	private String businessOrderStatuts;
	
	//借款编号
	private String borrowNum;
	
	//业务订单创建时间
	private String orderCreateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessOrderNum() {
		return businessOrderNum;
	}

	public void setBusinessOrderNum(String businessOrderNum) {
		this.businessOrderNum = businessOrderNum;
	}

	public String getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}

	public String getProofreadType() {
		return proofreadType;
	}

	public void setProofreadType(String proofreadType) {
		this.proofreadType = proofreadType;
	}

	public String getProofreadDate() {
		return proofreadDate;
	}

	public void setProofreadDate(String proofreadDate) {
		this.proofreadDate = proofreadDate;
	}

	public BigDecimal getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(BigDecimal exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	public String getBusinessOrderStatuts() {
		return businessOrderStatuts;
	}

	public void setBusinessOrderStatuts(String businessOrderStatuts) {
		this.businessOrderStatuts = businessOrderStatuts;
	}

	public String getBorrowNum() {
		return borrowNum;
	}

	public void setBorrowNum(String borrowNum) {
		this.borrowNum = borrowNum;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
    
	
}
