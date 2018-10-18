package com.pgy.ups.account.facade.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class Model implements Serializable{
	
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
