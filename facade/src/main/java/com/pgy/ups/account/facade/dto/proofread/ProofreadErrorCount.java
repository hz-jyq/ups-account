package com.pgy.ups.account.facade.dto.proofread;

import com.pgy.ups.account.facade.model.Model;

import java.math.BigDecimal;

public class ProofreadErrorCount extends Model {

    private BigDecimal proofreadTotalMoney;



    private int  proofreadCount;

    private String proofreadType;



    public BigDecimal getProofreadTotalMoney() {
        return proofreadTotalMoney;
    }

    public void setProofreadTotalMoney(BigDecimal proofreadTotalMoney) {
        this.proofreadTotalMoney = proofreadTotalMoney;
    }

    public int getProofreadCount() {
        return proofreadCount;
    }

    public void setProofreadCount(int proofreadCount) {
        this.proofreadCount = proofreadCount;
    }

    public String getProofreadType() {
        return proofreadType;
    }

    public void setProofreadType(String proofreadType) {
        this.proofreadType = proofreadType;
    }



}