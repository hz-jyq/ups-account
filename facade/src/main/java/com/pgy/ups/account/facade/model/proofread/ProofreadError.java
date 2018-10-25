package com.pgy.ups.account.facade.model.proofread;

import java.math.BigDecimal;

import com.pgy.ups.account.facade.model.Model;

/**
 * 
 * @author 对账异常明细
 *
 */
public class ProofreadError extends Model{
	
     /**
	 * 
	 */
	private static final long serialVersionUID = 2043813130447200867L;
		
	private Long id;
	
	//'对账系统编码01：美期 02：米融 03：秒呗'
	private String fromSystem;
	
	//'对账类型 01：借款对账  02：还款对账'
	private String proofreadType;
	
	//'对账日期'
	private String proofreadDate;
	
	//商户订单号
	private String businessOrderNum;
	
	//借款编号
	private String borrowNum;
	
	//业务交易金额
	private BigDecimal businessExchangeMoney;
	
	//业务订单状态
	private String businessOrderStatuts;
	
	//业务订单创建时间
	private String businessOrderCreateTime;
	
	//渠道交易金额
	private BigDecimal channelExchangeMoney;
	
	//渠道订单状态
	private String channelOrderStatus;
	
	//渠道订单创建时间
	private String channelOrderCreateTime;
	
	//处理时间
	private String disposeTime;
	
	//异常类型 01：金额不一致 02：渠道有业务没有 03：业务有渠道没有
	private String errorType;
	
	//流水状态：01：待处理 02：已预留 03：已归档 04：已失效
	private String flowStatus;
	
	//操作用户
	private String updateUser;

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

	public String getProofreadDate() {
		return proofreadDate;
	}

	public void setProofreadDate(String proofreadDate) {
		this.proofreadDate = proofreadDate;
	}

	public String getBusinessOrderNum() {
		return businessOrderNum;
	}

	public void setBusinessOrderNum(String businessOrderNum) {
		this.businessOrderNum = businessOrderNum;
	}

	public String getBorrowNum() {
		return borrowNum;
	}

	public void setBorrowNum(String borrowNum) {
		this.borrowNum = borrowNum;
	}

	public BigDecimal getBusinessExchangeMoney() {
		return businessExchangeMoney;
	}

	public void setBusinessExchangeMoney(BigDecimal businessExchangeMoney) {
		this.businessExchangeMoney = businessExchangeMoney;
	}

	public String getBusinessOrderStatuts() {
		return businessOrderStatuts;
	}

	public void setBusinessOrderStatuts(String businessOrderStatuts) {
		this.businessOrderStatuts = businessOrderStatuts;
	}

	public String getBusinessOrderCreateTime() {
		return businessOrderCreateTime;
	}

	public void setBusinessOrderCreateTime(String businessOrderCreateTime) {
		this.businessOrderCreateTime = businessOrderCreateTime;
	}

	public BigDecimal getChannelExchangeMoney() {
		return channelExchangeMoney;
	}

	public void setChannelExchangeMoney(BigDecimal channelExchangeMoney) {
		this.channelExchangeMoney = channelExchangeMoney;
	}

	public String getChannelOrderStatus() {
		return channelOrderStatus;
	}

	public void setChannelOrderStatus(String channelOrderStatus) {
		this.channelOrderStatus = channelOrderStatus;
	}

	public String getChannelOrderCreateTime() {
		return channelOrderCreateTime;
	}

	public void setChannelOrderCreateTime(String channelOrderCreateTime) {
		this.channelOrderCreateTime = channelOrderCreateTime;
	}

	public String getDisposeTime() {
		return disposeTime;
	}

	public void setDisposeTime(String disposeTime) {
		this.disposeTime = disposeTime;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
}
