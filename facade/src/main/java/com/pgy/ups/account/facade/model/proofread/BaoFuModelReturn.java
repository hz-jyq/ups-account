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

	//商户退款订单号
	private String businessRefundOrderNum;


	public String getBusinessRefundOrderNum() {
		return businessRefundOrderNum;
	}

	public void setBusinessRefundOrderNum(String businessRefundOrderNum) {
		this.businessRefundOrderNum = businessRefundOrderNum;
	}
	
}


