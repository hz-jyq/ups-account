
package com.pgy.ups.account.facade.from;

public class ProofreadErrorForm extends  AbstractPageForm<ProofreadErrorForm>{

    private static final long serialVersionUID = 1L;

    private  String channel;

    private String fromSystem;

    private  String proofreadType;

    private String errorType;


    private String proofreadDateStart;

    private String proofreadDateEnd;

    private String flowStatus;

    private String businessOrderNum;


    private String  proofreadField;

    //判断页面的类型
    private String flowStatusType;

    public String getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(String borrowNum) {
        this.borrowNum = borrowNum;
    }

    private String borrowNum;

    public String getProofreadField() {
        return proofreadField;
    }

    public void setProofreadField(String proofreadField) {
        this.proofreadField = proofreadField;
    }

    public String getFlowStatusType() {
        return flowStatusType;
    }

    public void setFlowStatusType(String flowStatusType) {
        this.flowStatusType = flowStatusType;
    }




    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

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

    public String getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public String getBusinessOrderNum() {
        return businessOrderNum;
    }

    public void setBusinessOrderNum(String businessOrderNum) {
        this.businessOrderNum = businessOrderNum;
    }
}
