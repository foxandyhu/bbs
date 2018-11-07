package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.util.DateUtils;

@Entity
@Table(name = "jb_api_record")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApiRecord implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:00:16
	 */
	private static final long serialVersionUID = 5918777288429868950L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="call_ip",length=255)
	private String callIp;
	
	@Column(name="api_call_time",nullable=false)
	private Date callTime;
	
	@Column(name="call_time_stamp",nullable=false,length=19)
	private Long callTimeStamp;
	
	@Column(name="sign",length=100)
	private String sign;

	@ManyToOne
	@JoinColumn(name="api_account")
	private ApiAccount apiAccount;
	
	@ManyToOne
	@JoinColumn(name="api_info_id")	
	private ApiInfo apiInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCallIp() {
		return callIp;
	}

	public void setCallIp(String callIp) {
		this.callIp = callIp;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public Long getCallTimeStamp() {
		return callTimeStamp;
	}

	public void setCallTimeStamp(Long callTimeStamp) {
		this.callTimeStamp = callTimeStamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public ApiAccount getApiAccount() {
		return apiAccount;
	}

	public void setApiAccount(ApiAccount apiAccount) {
		this.apiAccount = apiAccount;
	}

	public ApiInfo getApiInfo() {
		return apiInfo;
	}

	public void setApiInfo(ApiInfo apiInfo) {
		this.apiInfo = apiInfo;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getApiAccount() != null && StringUtils.isNotBlank(getApiAccount().getAppId())) {
			json.put("apiAccountId", getApiAccount().getAppId());
		} else {
			json.put("apiAccountId", "");
		}
		if (getApiInfo() != null && StringUtils.isNotBlank(getApiInfo().getName())) {
			json.put("apiInfoName", getApiInfo().getName());
		} else {
			json.put("apiInfoName", "");
		}
		if (StringUtils.isNotBlank(getCallIp())) {
			json.put("callIp", getCallIp());
		} else {
			json.put("callIp", "");
		}
		if (getCallTime() != null) {
			json.put("callTime", DateUtils.parseDateToDateStr(getCallTime()));
		} else {
			json.put("callTime", "");
		}
		return json;
	}
}