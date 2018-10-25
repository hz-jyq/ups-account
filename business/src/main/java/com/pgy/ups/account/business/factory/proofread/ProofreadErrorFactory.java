package com.pgy.ups.account.business.factory.proofread;

import com.pgy.ups.account.business.factory.BusinessFactory;
import com.pgy.ups.account.facade.model.proofread.BaoFuModel;
import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadError;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

/**
 *    对账异常对象构建工厂 
 * @author 墨凉
 *
 */
public class ProofreadErrorFactory implements BusinessFactory<ProofreadError> {

	private ProofreadResult proofreadResult;

	// 待处理
	public static final String FLOW_STATUS_READY = "01";
	// 预留
	public static final String FLOW_STATUS_RESERVED = "02";
	// 归档
	public static final String FLOW_STATUS_SUCCESS = "03";
	// 失效
	public static final String FLOW_STATUS_FAIL = "04";

	// 错账：01 金额不一致
	public static final String ERROR_TYPE_DEFFERENT = "01";

	// 错账：02：渠道有业务没有
	public static final String ERROR_TYPE_NO_BUSINESS = "02";

	// 错账：03：业务有渠道没有
	public static final String ERROR_TYPE_NO_CHANNEL = "03";

	public ProofreadErrorFactory(ProofreadResult proofreadResult) {
		this.proofreadResult = proofreadResult;
	}
    
	/**
	 *   根据参数构建对账异常明细对象
	 * @param businessModel
	 * @param baofuModel
	 * @return
	 */
	public ProofreadError createProofreadError(BusinessProofreadModel businessModel, BaoFuModel baofuModel) {
		ProofreadError pe = new ProofreadError();
		pe.setFromSystem(proofreadResult.getFromSystem());
		pe.setProofreadDate(proofreadResult.getProofreadDate());
		pe.setProofreadType(proofreadResult.getProofreadType());
		pe.setFlowStatus(FLOW_STATUS_READY);
		if (businessModel != null) {
			pe.setBusinessOrderNum(businessModel.getBusinessOrderNum());
			pe.setBorrowNum(businessModel.getBorrowNum());
			pe.setBusinessExchangeMoney(businessModel.getExchangeAmount());
			pe.setBusinessOrderStatuts(businessModel.getBusinessOrderStatuts());
			pe.setErrorType(ERROR_TYPE_NO_CHANNEL);
		}
		if (baofuModel != null) {
			pe.setBusinessOrderCreateTime(baofuModel.getOrderCreateTime());
			pe.setChannelExchangeMoney(baofuModel.getExchangeAmount());
			pe.setChannelOrderStatus(baofuModel.getOrderStatus());
			pe.setChannelOrderCreateTime(baofuModel.getOrderCreateTime());
			pe.setErrorType(ERROR_TYPE_NO_BUSINESS);
		}
		if (baofuModel != null && businessModel != null) {
			pe.setErrorType(ERROR_TYPE_DEFFERENT);
		}
		return pe;
	}
    
	/**
	 *   根据参数构建对账异常明细对象
	 * @param businessModel
	 * @return
	 */
	public ProofreadError createProofreadError(BusinessProofreadModel businessModel) {
		return createProofreadError(businessModel, null);
	}
    
	/**
	 *   根据参数构建对账异常明细对象
	 * @param baofuModel
	 * @return
	 */
	public ProofreadError createProofreadError(BaoFuModel baofuModel) {
		return createProofreadError(null, baofuModel);
	}

}
