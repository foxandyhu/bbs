package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name="jb_api_account")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ApiAccount implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月30日上午9:58:48
	 */
	private static final long serialVersionUID = -8720475026242768456L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="app_id",nullable=false,length=255)
	private String appId;
	
	@Column(name="app_key",nullable=false,length=255)
	private String appKey;
	
	@Column(name="aes_key",nullable=false,length=100)
	private String aesKey;
	
	@Column(name="iv_key",nullable=false,length=100)
	private String ivKey;
	
	@Column(name="disabled",nullable=false)
	private Boolean disabled;

	@OneToMany(fetch=FetchType.LAZY,mappedBy="apiAccount")
	private Set<ApiRecord> callRecords;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public String getIvKey() {
		return ivKey;
	}

	public void setIvKey(String ivKey) {
		this.ivKey = ivKey;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Set<ApiRecord> getCallRecords() {
		return callRecords;
	}

	public void setCallRecords(Set<ApiRecord> callRecords) {
		this.callRecords = callRecords;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getAppId())) {
			json.put("setAppId", getAppId());
		} else {
			json.put("setAppId", "");
		}
		if (getDisabled() != null) {
			json.put("disabled", getDisabled());
		} else {
			json.put("disabled", "");
		}
		if (StringUtils.isNotBlank(getAppKey())) {
			json.put("appKey", getAppKey());
		} else {
			json.put("appKey", "");
		}
		if (StringUtils.isNotBlank(getAesKey())) {
			json.put("aesKey", getAesKey());
		} else {
			json.put("aesKey", "");
		}
		if (StringUtils.isNotBlank(getIvKey())) {
			json.put("ivKey", getIvKey());
		} else {
			json.put("ivKey", "");
		}
		return json;
	}

	public void init() {
		if (getDisabled() == null) {
			setDisabled(false);
		}
	}
}