package com.pgy.ups.account.facade.model.proofread;

import com.pgy.ups.account.facade.model.Model;

/**
 * 宝付数据模型
 * @author 墨凉
 *
 */
public class BaoFuModel extends Model{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3832400894624542019L;
	
	//id
	private Long id;
		
	//系统编码：参考 FromSystem
	private String systemFrom;
	
	//下载时间
	private String downLoadTime;
	
	//对账时间
	private String proofreadDate;
	
	public String getProofreadDate() {
		return proofreadDate;
	}

	public void setProofreadDate(String proofreadDate) {
		this.proofreadDate = proofreadDate;
	}

	//商户号
	private String businessNum;
	
	//终端号
	private String terminalNum;
	
	//交易类型
	private String exchangeType;
	
	//交易子类型 00：成功 01：退款 02：撤销
	private String subExchangeType;
	
	//宝付订单号
	private String baofuOrderNum;
	
	
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
		
	//退款订单创建时间
	private String refundOrderCreateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSystemFrom() {
		return systemFrom;
	}

	public void setSystemFrom(String systemFrom) {
		this.systemFrom = systemFrom;
	}

	public String getDownLoadTime() {
		return downLoadTime;
	}

	public void setDownLoadTime(String downLoadTime) {
		this.downLoadTime = downLoadTime;
	}

	public String getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
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

	public String getRefundOrderCreateTime() {
		return refundOrderCreateTime;
	}

	public void setRefundOrderCreateTime(String refundOrderCreateTime) {
		this.refundOrderCreateTime = refundOrderCreateTime;
	}
			
}
