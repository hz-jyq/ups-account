package com.pgy.ups.account.facade.dto.proofread;

import java.math.BigDecimal;

import com.pgy.ups.account.facade.model.Model;

/**
 *
 */
public class ProofreadErrorCountDto extends Model {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4876989845161238772L;

	private BigDecimal businessExchangeTotalMoney;

    private int  businessExchangeCount;

    private BigDecimal channelExchangeTotalMoney;

    private int  channelExchangeTotalCount;

    public  void  createDto( ProofreadCount dto){
        if("businessExchangeMoney".equals(dto.getProofreadType())){
            this.businessExchangeTotalMoney = dto.getProofreadTotalMoney();
            this.businessExchangeCount = dto.getProofreadCount();
        }else{
            this.channelExchangeTotalMoney = dto.getProofreadTotalMoney();
            this.channelExchangeTotalCount = dto.getProofreadCount();
        }
    }


    public BigDecimal getBusinessExchangeTotalMoney() {
        return businessExchangeTotalMoney;
    }

    public void setBusinessExchangeTotalMoney(BigDecimal businessExchangeTotalMoney) {
        this.businessExchangeTotalMoney = businessExchangeTotalMoney;
    }

    public int getBusinessExchangeCount() {
        return businessExchangeCount;
    }

    public void setBusinessExchangeCount(int businessExchangeCount) {
        this.businessExchangeCount = businessExchangeCount;
    }

    public BigDecimal getChannelExchangeTotalMoney() {
        return channelExchangeTotalMoney;
    }

    public void setChannelExchangeTotalMoney(BigDecimal channelExchangeTotalMoney) {
        this.channelExchangeTotalMoney = channelExchangeTotalMoney;
    }

    public int getChannelExchangeTotalCount() {
        return channelExchangeTotalCount;
    }

    public void setChannelExchangeTotalCount(int channelExchangeTotalCount) {
        this.channelExchangeTotalCount = channelExchangeTotalCount;
    }




}
