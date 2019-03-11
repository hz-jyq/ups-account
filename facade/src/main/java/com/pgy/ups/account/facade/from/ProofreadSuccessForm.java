package com.pgy.ups.account.facade.from;

import com.pgy.ups.common.page.AbstractPageForm;

public class ProofreadSuccessForm extends AbstractPageForm<ProofreadSuccessForm> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1230545867667178843L;

	private String proofreadStatus;

    private String  channel;

    private String proofreadDateStart;

    private String updateUser;

    private String proofreadDateEnd;

    private String fromSystem;


    private String proofreadType;

    private String businessOrderNum;

    private String businessNum;


    private String borrowNum;


    public String getProofreadDateStart() {
        return proofreadDateStart;
    }

    public void setProofreadDateStart(String proofreadDateStart) {
        this.proofreadDateStart = proofreadDateStart;
    }

    public String getProofreadDateEnd() {
        return proofreadDateEnd;
    }

    public void setProofreadDateEnd(String proofreadDateEnd) {
        this.proofreadDateEnd = proofreadDateEnd;
    }

    public String getBusinessOrderNum() {
        return businessOrderNum;
    }

    public void setBusinessOrderNum(String businessOrderNum) {
        this.businessOrderNum = businessOrderNum;
    }

    public String getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(String businessNum) {
        this.businessNum = businessNum;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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
}
