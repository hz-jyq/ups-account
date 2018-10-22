package com.pgy.ups.account.facade.model.proofread;

/**
 * 宝付还款下载数据模型
 * @author 墨凉
 *
 */
public class BaoFuModelReturn extends BaoFuModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7733957069485306047L;
	
	//商户订单号
	private String businessOrderNum;
	
	//支付订单创建时间
	private String orderCreateTime;
		
	//商户退款订单号
	private String businessRefundOrderNum;

	public String getBusinessOrderNum() {
		return businessOrderNum;
	}

	public void setBusinessOrderNum(String businessOrderNum) {
		this.businessOrderNum = businessOrderNum;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getBusinessRefundOrderNum() {
		return businessRefundOrderNum;
	}

	public void setBusinessRefundOrderNum(String businessRefundOrderNum) {
		this.businessRefundOrderNum = businessRefundOrderNum;
	}
	
}


