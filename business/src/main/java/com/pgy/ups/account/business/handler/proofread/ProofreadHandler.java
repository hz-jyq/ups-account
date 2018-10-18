package com.pgy.ups.account.business.handler.proofread;

import java.util.List;

import com.pgy.ups.account.business.handler.Handler;
import com.pgy.ups.account.facade.model.proofread.BussinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

/**
 * 对账处理器接口
 * @author 墨涼
 *
 * @param <T>
 * @param <E>
 */
public interface ProofreadHandler<T,E> extends Handler<List<BussinessProofreadModel>, ProofreadResult> {
	
	public void setDocumentParserHandler(DocumentParserHandler<T,E> documentParserHandler);
}
