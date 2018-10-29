package com.pgy.ups.account.business.factory.proofread;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.pgy.ups.account.business.factory.BusinessFactory;
import com.pgy.ups.account.facade.model.proofread.BaoFuModel;
import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;
import com.pgy.ups.account.facade.model.proofread.ProofreadSuccess;
import com.pgy.ups.account.facade.model.proofread.ProofreadSum;

/**
 *    对账成功对象构建工厂 
 * @author 墨凉
 *
 */
public class ProofreadSuccessFactory implements BusinessFactory<ProofreadSuccess> {

	private ProofreadResult proofreadResult;

	public ProofreadSuccessFactory(ProofreadResult proofreadResult) {
		this.proofreadResult = proofreadResult;
	}
	   
	/**
	 *   根据参数构建对账成功明细对象
	 * @param businessModel
	 * @param baofuModel
	 * @return
	 */
	public ProofreadSuccess createProofreadSuccess(BusinessProofreadModel businessModel, BaoFuModel baofuModel) {
		ProofreadSuccess ps = new ProofreadSuccess();
		ps.setProofreadStatus(ProofreadSum.PROOFREAD_SUCCESS);
		ps.setChannel(proofreadResult.getChannel());
        ps.setFromSystem(proofreadResult.getFromSystem());
        ps.setProofreadType(proofreadResult.getProofreadType());
        ps.setProofreadDate(proofreadResult.getProofreadDate());
        ps.setBusinessNum(proofreadResult.getBusinessNum());
        ps.setBusinessOrderNum(businessModel.getBusinessOrderNum());
        ps.setBorrowNum(businessModel.getBorrowNum());
        ps.setBusinessExchangeMoney(businessModel.getExchangeAmount());
        ps.setBusinessOrderCreateTime(businessModel.getOrderCreateTime());
        ps.setChannelExchangeMoney(baofuModel.getExchangeAmount());
        ps.setChannelOrderCreateTime(baofuModel.getOrderCreateTime());
        ps.setRemark(StringUtils.EMPTY);
        ps.setUpdateUser(StringUtils.EMPTY);
		return ps;		
	}
    
}
