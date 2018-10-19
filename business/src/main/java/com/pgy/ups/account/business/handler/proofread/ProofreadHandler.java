package com.pgy.ups.account.business.handler.proofread;

import java.util.Date;
import java.util.List;

import com.pgy.ups.account.business.handler.Handler;
import com.pgy.ups.account.facade.model.proofread.BusinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

/**
 * 对账处理器接口
 * 
 * @author 墨涼
 *
 * @param <T>
 * @param <E>
 */
public interface ProofreadHandler<T, E> extends Handler<List<BusinessProofreadModel>, ProofreadResult> {

	/**
	 * 设置下载文件解析器
	 * 
	 * @param documentParserHandler
	 */
	public void setDocumentParserHandler(DocumentParserHandler<T, E> documentParserHandler);

	/**
	 * 对账系统
	 */
	public void setFromSystem(String fromSystem);

	/**
	 * 对账类型
	 */
	public void setProofreadAccountType(String proofreadAccountType);

	/**
	 * 对账结果Id
	 */
	public void setProofreadResultId(Long proofreadResultId);
	
	/**
	 * 设置对账日期
	 */
	public void setDate(Date date) ;
		
}
