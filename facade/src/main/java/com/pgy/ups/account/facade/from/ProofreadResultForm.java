package com.pgy.ups.account.facade.from;

public class ProofreadResultForm extends  AbstractPageForm<ProofreadResultForm> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4570733760378518305L;
	
	private String proofreadDate;
	
	private String channel;
	
	private String proofreadType;
	
	private String fromSystem;

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

	public String getProofreadType() {
		return proofreadType;
	}

	public void setProofreadType(String proofreadType) {
		this.proofreadType = proofreadType;
	}

	public String getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}
	
}
