package com.pay.ups.account.facade.model.proofread;

import com.pay.ups.account.facade.model.Model;

/**
 * 校验账单结果实体
 */
public class ProofreadResult extends Model{

	private static final long serialVersionUID = 2421214951235198688L;
	
	private Long proofreadResultId;
	
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
	
	//执行时间
	private String excuteTime;

	public Long getProofreadResultId() {
		return proofreadResultId;
	}

	public void setProofreadResultId(Long proofreadResultId) {
		this.proofreadResultId = proofreadResultId;
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

	
}