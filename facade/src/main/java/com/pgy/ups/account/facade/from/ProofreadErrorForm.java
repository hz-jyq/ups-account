package com.pgy.ups.account.facade.from;

public class ProofreadErrorForm extends  AbstractPageForm<ProofreadErrorForm>{

    private  String channel;

    private String fromSystem;

    private  String proofreadType;

    private String errorType;

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
}
