package com.jeecms.bbs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 接口参数
 * @author andy_hulibo@163.com
 * 2018年11月6日下午2:57:24
 */
@Embeddable
public class BbsWebserviceParam implements Serializable {
	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午3:52:37
	 */
	private static final long serialVersionUID = 509461921500118874L;

	@Column(name="param_name")
	private java.lang.String paramName;
	
	@Column(name="default_value")
	private java.lang.String defaultValue;

	public java.lang.String getParamName () {
		return paramName;
	}

	public void setParamName (java.lang.String paramName) {
		this.paramName = paramName;
	}

	public java.lang.String getDefaultValue () {
		return defaultValue;
	}

	public void setDefaultValue (java.lang.String defaultValue) {
		this.defaultValue = defaultValue;
	}
}