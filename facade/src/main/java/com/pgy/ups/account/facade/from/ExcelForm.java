package com.pgy.ups.account.facade.from;

import com.pgy.ups.account.facade.model.Model;

public class ExcelForm extends Model {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6656608836585811554L;

	private String channel;

    private String fromSystem;

    private String proofreadType;

    private String proofreadDate;

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

    public String getProofreadDate() {
        return proofreadDate;
    }

    public void setProofreadDate(String proofreadDate) {
        this.proofreadDate = proofreadDate;
    }
}
