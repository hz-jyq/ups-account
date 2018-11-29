package com.pgy.ups.account.business.enums;

import java.util.Objects;

/**
 * 需要进行对账的业务数据订单状态，不包含指定状态则无需对账
 * @author 墨凉
 *
 */
public enum IncludeOrderStatusEnum {

	MEQI("01", new String[] { "1", "2", "5" });

	private String fromSystem;

	private String[] orderStatus;

	private IncludeOrderStatusEnum(String fromSystem, String[] orderStatus) {
		this.fromSystem = fromSystem;
		this.orderStatus = orderStatus;
	}
	
	public static String[] getIncludeOrderStatusArray(String fromSystem) {		
		for(IncludeOrderStatusEnum e:IncludeOrderStatusEnum.values()) {
			if(Objects.equals(e.fromSystem, fromSystem)) {
				return e.orderStatus;
			}
		}
		return new String[] {};
	}
	

}
