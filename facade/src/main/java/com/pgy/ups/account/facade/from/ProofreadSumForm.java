package com.pgy.ups.account.facade.from;

public class ProofreadSumForm extends  AbstractPageForm<ProofreadSumForm>{

    private String proofreadStatus;

    private String  channel;

    private String proofreadDate;

    private String updateUser;

    private String createTimeStart;

    private String createTimeEnd;


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

    public String getProofreadDate() {
        return proofreadDate;
    }

    public void setProofreadDate(String proofreadDate) {
        this.proofreadDate = proofreadDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
