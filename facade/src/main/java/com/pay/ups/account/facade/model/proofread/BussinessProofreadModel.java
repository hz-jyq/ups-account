package com.pay.ups.account.facade.model.proofread;

import com.pay.ups.account.facade.model.Model;

public class BussinessProofreadModel extends Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8549531618936728275L;
	
	private Long id;
	
	//商户订单号
	private String bussinessOrderNum;
	
	//交易金额
	private String exchangeAmount;
	
	//业务订单状态
	private String bussinessOrderStatuts;
	
	//业务订单创建时间
	private String orderCreateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBussinessOrderNum() {
		return bussinessOrderNum;
	}

	public void setBussinessOrderNum(String bussinessOrderNum) {
		this.bussinessOrderNum = bussinessOrderNum;
	}

	public String getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(String exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	public String getBussinessOrderStatuts() {
		return bussinessOrderStatuts;
	}

	public void setBussinessOrderStatuts(String bussinessOrderStatuts) {
		this.bussinessOrderStatuts = bussinessOrderStatuts;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

}
