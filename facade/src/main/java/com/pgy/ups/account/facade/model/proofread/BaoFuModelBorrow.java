package com.pgy.ups.account.facade.model.proofread;

/**
 * 宝付还款数据模型
 * @author 墨凉
 *
 */
public class BaoFuModelBorrow extends BaoFuModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4066937147493379118L;
	/**
	 * 商户代付订单号
	 */
	private String proxyBusinessOrderNum;

	/* 批次号 */
	private String batchNum;
	/**
	 * 收款人账号
	 */
	private String recievePersonNum;
	/**
	 * 收款人姓名
	 */
	private String recievePersonName;

	public String getProxyBusinessOrderNum() {
		return proxyBusinessOrderNum;
	}

	public void setProxyBusinessOrderNum(String proxyBusinessOrderNum) {
		this.proxyBusinessOrderNum = proxyBusinessOrderNum;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getRecievePersonNum() {
		return recievePersonNum;
	}

	public void setRecievePersonNum(String recievePersonNum) {
		this.recievePersonNum = recievePersonNum;
	}

	public String getRecievePersonName() {
		return recievePersonName;
	}

	public void setRecievePersonName(String recievePersonName) {
		this.recievePersonName = recievePersonName;
	}

}