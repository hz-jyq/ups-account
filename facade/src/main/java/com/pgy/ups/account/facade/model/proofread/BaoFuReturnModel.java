package com.pgy.ups.account.facade.model.proofread;

import com.pgy.ups.account.facade.model.Model;

/**
 * 宝付还款数据模型
 * @author 墨凉
 *
 */
public class BaoFuReturnModel extends Model{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3832400894624542019L;
	
	//id
	private Long id;
	
	//商户号
	private String bussinessNum;
	
	//系统编码：参考 FromSystem
	private String systeFrom;
	
	//下载时间
	private String downLoadTime;
	
	//终端号
	private String terminalNum;
	
	//交易类型
	private String exchangeType;
	
	//交易子类型 00：成功 01：退款 02：撤销
	private String subExchangeType;
	
	//宝付订单号
	private String baofuOrderNum;
	
	//商户订单号
	private String bussinessOrderNum;
	
	//清算日期
	private  String caculateTime;
	
	//订单状态
	private String orderStatus;
	
	//交易金额
	private String exchangeAmount;
	
	//手续费
	private String exchangeTip;
	
	//宝付交易号
	private String baofuExchangeNum;
	
	//支付订单创建时间
	private String orderCreateTime;
	
	//商户退款订单号
	private String bussinessRefundOrderNum;
	
	//退款订单创建时间
	private String refundOrderCreateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBussinessNum() {
		return bussinessNum;
	}

	public void setBussinessNum(String bussinessNum) {
		this.bussinessNum = bussinessNum;
	}

	public String getSysteFrom() {
		return systeFrom;
	}

	public void setSysteFrom(String systeFrom) {
		this.systeFrom = systeFrom;
	}

	public String getDownLoadTime() {
		return downLoadTime;
	}

	public void setDownLoadTime(String downLoadTime) {
		this.downLoadTime = downLoadTime;
	}

	public String getTerminalNum() {
		return terminalNum;
	}

	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}

	public String getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}

	public String getSubExchangeType() {
		return subExchangeType;
	}

	public void setSubExchangeType(String subExchangeType) {
		this.subExchangeType = subExchangeType;
	}

	public String getBaofuOrderNum() {
		return baofuOrderNum;
	}

	public void setBaofuOrderNum(String baofuOrderNum) {
		this.baofuOrderNum = baofuOrderNum;
	}

	public String getBussinessOrderNum() {
		return bussinessOrderNum;
	}

	public void setBussinessOrderNum(String bussinessOrderNum) {
		this.bussinessOrderNum = bussinessOrderNum;
	}

	public String getCaculateTime() {
		return caculateTime;
	}

	public void setCaculateTime(String caculateTime) {
		this.caculateTime = caculateTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(String exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	public String getExchangeTip() {
		return exchangeTip;
	}

	public void setExchangeTip(String exchangeTip) {
		this.exchangeTip = exchangeTip;
	}

	public String getBaofuExchangeNum() {
		return baofuExchangeNum;
	}

	public void setBaofuExchangeNum(String baofuExchangeNum) {
		this.baofuExchangeNum = baofuExchangeNum;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getBussinessRefundOrderNum() {
		return bussinessRefundOrderNum;
	}

	public void setBussinessRefundOrderNum(String bussinessRefundOrderNum) {
		this.bussinessRefundOrderNum = bussinessRefundOrderNum;
	}

	public String getRefundOrderCreateTime() {
		return refundOrderCreateTime;
	}

	public void setRefundOrderCreateTime(String refundOrderCreateTime) {
		this.refundOrderCreateTime = refundOrderCreateTime;
	}
	
		
}
