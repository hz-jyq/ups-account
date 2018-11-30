package com.pgy.ups.account.facade.from;

public class ProofreadSuccessForm extends AbstractPageForm<ProofreadSuccessForm> {



    /**
	 * 
	 */
	private static final long serialVersionUID = -1230545867667178843L;

	private String proofreadStatus;

    private String  channel;

    private String proofreadDate;

    private String updateUser;

    private String createTimeStart;

    private String createTimeEnd;

    private String fromSystem;

    private String proofreadType;


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
