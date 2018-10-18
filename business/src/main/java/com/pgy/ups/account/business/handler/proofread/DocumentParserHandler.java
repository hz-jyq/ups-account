package com.pgy.ups.account.business.handler.proofread;

import com.pgy.ups.account.business.handler.Handler;

/**
 * 对账文件解析器
 * @author 墨凉
 * 
 * @param <T>
 * @param <R>
 */
public interface DocumentParserHandler<T,R> extends Handler<T,R>{
		
	String getDocumentName();
		
}
