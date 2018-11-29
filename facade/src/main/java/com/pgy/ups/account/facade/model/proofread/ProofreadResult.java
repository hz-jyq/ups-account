package com.pgy.ups.account.facade.model.proofread;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.pgy.ups.account.facade.model.Model;

/**
 * 校验账单结果实体
 */
public class ProofreadResult extends Model{

	private static final long serialVersionUID = 2421214951235198688L;
	
	private Long id;
	
	
	//对账用的商户号
	private String businessNum;
	
	//'对账渠道 01:宝付',
	private String channel;
	
	//对账系统 参考 FromSystem
	private String fromSystem;
	
	//对账类型  参见ProofreadAccountType 01借款对账  02还款对账
	private String proofreadType;
	
	//总体执行是否成功 true 成功 false 失败
	private Boolean success;
	
	//失败原因
	private String failReason;
	
	//下载解析是否 成功
    private Boolean downloadSuccess;
    	
	//失败次数
	private Integer failCount;
	
	//需要对账的日期
	private String proofreadDate;
	
	//执行时间
	private String excuteTime;
	//更新时间
	private String updateTime;
	// 创建人员
	private String createUser;
	// 更新人员
	private String updateUser;

	public Long getId() {
		return id;
	}

	public void setProofreadResultId(Long id) {
		this.id=id;
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

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public Boolean getDownloadSuccess() {
		return downloadSuccess;
	}

	public void setDownloadSuccess(Boolean downloadSuccess) {
		this.downloadSuccess = downloadSuccess;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	public String getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(String excuteTime) {
		this.excuteTime = excuteTime;
	}
	
	public void appendFailReason(String newReason) {
		StringBuilder sb=new StringBuilder(failReason);
		if(!StringUtils.isEmpty(failReason)) {
			sb.append(",");
		}
		sb.append(newReason);
		failReason=sb.toString();
	}
	
	public void increaseFailCount() {
		if(failCount==null) {
			failCount=1;
		}else {
			failCount++;
		}
	}

	public String getProofreadDate() {
		return proofreadDate;
	}

	public void setProofreadDate(String proofreadDate) {
		this.proofreadDate = proofreadDate;
	}

	public String getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
}
