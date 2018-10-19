package com.pgy.ups.account.business.handler;

/**
 * 
 * @author 墨凉 处理器顶层接口
 * @param <T>
 * @param <R>
 */
public interface Handler<T, R> {

	/**
	 * 带参的处理器方法
	 * 
	 * @return
	 */
	R handler(T t) throws Exception;

}
