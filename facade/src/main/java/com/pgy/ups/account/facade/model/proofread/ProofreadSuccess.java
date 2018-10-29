package com.pgy.ups.account.facade.model.proofread;

import java.math.BigDecimal;

import com.pgy.ups.account.facade.model.Model;

/**
 * 
 * @author 对账成功明细
 *
 */
public class ProofreadSuccess extends Model{
	
     /**
	 * 
	 */
	private static final long serialVersionUID = 2043813130447200897L;
		
    private Long id;
	
	//'对账系统编码01：美期 02：米融 03：秒呗'
	private String fromSystem;
	
	//'对账类型 01：借款对账  02：还款对账'
	private String proofreadType;
	
	//'对账日期'
	private String proofreadDate;
	
	//'对账渠道 01:宝付',
	private String channel;
	
	// 商户号
	private String businessNum;
	
	//商户订单号
	private String businessOrderNum;
	
	//渠道订单创建时间
	private String channelOrderCreateTime;
	
	//渠道交易金额
	private BigDecimal channelExchangeMoney;
	
	//业务订单创建时间
	private  String businessOrderCreateTime;
	
	//业务交易金额
	private BigDecimal businessExchangeMoney;
	
	//借款编号
	private String borrowNum;
	
	//对账状态
	private String proofreadStatus;
	
	//备注
	private String remark;
	
	//操作员
	private String UpdateUser;

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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	public String getBusinessOrderNum() {
		return businessOrderNum;
	}

	public void setBusinessOrderNum(String businessOrderNum) {
		this.businessOrderNum = businessOrderNum;
	}

	public String getChannelOrderCreateTime() {
		return channelOrderCreateTime;
	}

	public void setChannelOrderCreateTime(String channelOrderCreateTime) {
		this.channelOrderCreateTime = channelOrderCreateTime;
	}

	public BigDecimal getChannelExchangeMoney() {
		return channelExchangeMoney;
	}

	public void setChannelExchangeMoney(BigDecimal channelExchangeMoney) {
		this.channelExchangeMoney = channelExchangeMoney;
	}

	public String getBusinessOrderCreateTime() {
		return businessOrderCreateTime;
	}

	public void setBusinessOrderCreateTime(String businessOrderCreateTime) {
		this.businessOrderCreateTime = businessOrderCreateTime;
	}

	public BigDecimal getBusinessExchangeMoney() {
		return businessExchangeMoney;
	}

	public void setBusinessExchangeMoney(BigDecimal businessExchangeMoney) {
		this.businessExchangeMoney = businessExchangeMoney;
	}

	public String getBorrowNum() {
		return borrowNum;
	}

	public void setBorrowNum(String borrowNum) {
		this.borrowNum = borrowNum;
	}

	public String getProofreadStatus() {
		return proofreadStatus;
	}

	public void setProofreadStatus(String proofreadStatus) {
		this.proofreadStatus = proofreadStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdateUser() {
		return UpdateUser;
	}

	public void setUpdateUser(String updateUser) {
		UpdateUser = updateUser;
	}
	
}
