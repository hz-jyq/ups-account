package com.pgy.ups.account.business.entity;

import com.pgy.ups.account.facade.model.Model;

/**
 * 对账汇总结果数据
 * 
 * @author 墨凉
 *
 */
public abstract class ProofreadSum extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6385302551739865578L;

	protected Long id;

	// 对账系统编码 01：美期 02：米融 03：秒呗
	protected String fromSystem;

	// '对账类型 01：借款对账 02：还款对账',
	protected String proofreadType;
	
	//对账日期
	protected String proofreadDate;

	// 创建时间
	protected String createTime;

	// 对账渠道（宝付或其他第三方）
	protected String proofreadWay;

	// 商户订单号
	protected String businessNum;

	// 业务交易总金额
	protected String businessTotalMoney;

	// 业务交易总笔数
	protected Integer businessTotal;

	// 渠道交易金额（宝付或其他第三方）
	protected String channelTotalMoney;

	// 渠道交易总笔数（宝付或其他第三方）
	protected Integer channelTotal;

	// 对账成功交易金额
	protected String successTotalMoney;

	// 对账成功总笔数
	protected Integer successTotal;

	// 对账失败交易金额
	protected String failTotalMoney;

	// 对账失败总笔数
	protected Integer failTotal;
	
	//'对账状态 01 已对账 02 有异常 03待对账'
	protected String proofreadStatus;
	
	//最後一次修改時間
	protected String updateTime;
	
	//操作人員
	protected String updateUser;
	
	public abstract ProofreadSum  build() ;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getProofreadWay() {
		return proofreadWay;
	}

	public void setProofreadWay(String proofreadWay) {
		this.proofreadWay = proofreadWay;
	}

	public String getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	public String getBusinessTotalMoney() {
		return businessTotalMoney;
	}

	public void setBusinessTotalMoney(String businessTotalMoney) {
		this.businessTotalMoney = businessTotalMoney;
	}

	public Integer getBusinessTotal() {
		return businessTotal;
	}

	public void setBusinessTotal(Integer businessTotal) {
		this.businessTotal = businessTotal;
	}

	public String getChannelTotalMoney() {
		return channelTotalMoney;
	}

	public void setChannelTotalMoney(String channelTotalMoney) {
		this.channelTotalMoney = channelTotalMoney;
	}

	public Integer getChannelTotal() {
		return channelTotal;
	}

	public void setChannelTotal(Integer channelTotal) {
		this.channelTotal = channelTotal;
	}

	public String getSuccessTotalMoney() {
		return successTotalMoney;
	}

	public void setSuccessTotalMoney(String successTotalMoney) {
		this.successTotalMoney = successTotalMoney;
	}

	public Integer getSuccessTotal() {
		return successTotal;
	}

	public void setSuccessTotal(Integer successTotal) {
		this.successTotal = successTotal;
	}

	public String getFailTotalMoney() {
		return failTotalMoney;
	}

	public void setFailTotalMoney(String failTotalMoney) {
		this.failTotalMoney = failTotalMoney;
	}

	public Integer getFailTotal() {
		return failTotal;
	}

	public void setFailTotal(Integer failTotal) {
		this.failTotal = failTotal;
	}

	public String getProofreadStatus() {
		return proofreadStatus;
	}

	public void setProofreadStatus(String proofreadStatus) {
		this.proofreadStatus = proofreadStatus;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public String getProofreadDate() {
		return proofreadDate;
	}

	public void setProofreadDate(String proofreadDate) {
		this.proofreadDate = proofreadDate;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	

}
