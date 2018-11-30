package com.pgy.ups.account.facade.dto.proofread;


import com.pgy.ups.account.facade.model.Model;

import java.math.BigDecimal;

/**
 *
 */
public class ProofreadSuccessCountDto extends Model {

    private static final long serialVersionUID = -7120049343713722557L;

    private BigDecimal proofreadTotalMoney;

    private int  proofreadCount;

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
}
