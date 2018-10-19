package com.pgy.ups.account.facade.model.proofread;

import com.pgy.ups.account.facade.model.Model;

public class BusinessProofreadModel extends Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8549531618936728275L;
	
	private Long id;
	
	//商户订单号
	private String businessOrderNum;
	
	//交易金额
	private String exchangeAmount;
	
	//业务订单状态
	private String businessOrderStatuts;
	
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

	public String getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(String exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	public String getBussinessOrderStatuts() {
		return businessOrderStatuts;
	}

	public void setBussinessOrderStatuts(String businessOrderStatuts) {
		this.businessOrderStatuts = businessOrderStatuts;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

}
