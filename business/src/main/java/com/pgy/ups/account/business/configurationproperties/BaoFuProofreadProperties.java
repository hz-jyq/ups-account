package com.pgy.ups.account.business.configurationproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = BaoFuProofreadProperties.BAO_FU_PROOFREAD_PREFI)
public class BaoFuProofreadProperties {

	public static final String BAO_FU_PROOFREAD_PREFI = "ups.account.proofread.baofu.params";

	private String version;

	private String requestUrl;

	private String borrows;

	private String returns;

	private String businessReturnNum;

	private String businessBorrowNum;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getBusinessReturnNum() {
		return businessReturnNum;
	}

	public void setBusinessReturnNum(String businessReturnNum) {
		this.businessReturnNum = businessReturnNum;
	}

	public String getBusinessBorrowNum() {
		return businessBorrowNum;
	}

	public void setBusinessBorrowNum(String businessBorrowNum) {
		this.businessBorrowNum = businessBorrowNum;
	}

	

	public String getBorrows() {
		return borrows;
	}

	public void setBorrows(String borrows) {
		this.borrows = borrows;
	}

	public String getReturns() {
		return returns;
	}

	public void setReturns(String returns) {
		this.returns = returns;
	}

}
