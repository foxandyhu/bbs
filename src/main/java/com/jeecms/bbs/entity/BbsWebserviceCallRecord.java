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
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.util.DateUtils;

/**
 * 接口调用记录
 * 
 * @author andy_hulibo@163.com 2018年11月6日下午3:05:02
 */
@Entity
@Table(name = "bbs_webservice_call_record")
public class BbsWebserviceCallRecord implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:52:49
	 */
	private static final long serialVersionUID = -1579755869006301829L;

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getServiceCode())) {
			json.put("operate", getServiceCode());
		} else {
			json.put("operate", "");
		}
		if (getAuth() != null && StringUtils.isNotBlank(getAuth().getUsername())) {
			json.put("username", getAuth().getUsername());
		} else {
			json.put("username", "");
		}
		if (getAuth() != null && StringUtils.isNotBlank(getAuth().getSystem())) {
			json.put("system", getAuth().getSystem());
		} else {
			json.put("system", "");
		}
		if (getRecordTime() != null) {
			json.put("recordTime", DateUtils.parseDateToDateStr(getRecordTime()));
		} else {
			json.put("recordTime", "");
		}
		return json;
	}

	@Id
	@Column(name="record_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="service_code")
	private String serviceCode;
	
	@Column(name="record_time")
	private Date recordTime;

	@ManyToOne
	@JoinColumn(name = "auth_id")
	private BbsWebserviceAuth auth;

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getServiceCode() {
		return serviceCode;
	}

	
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public BbsWebserviceAuth getAuth() {
		return auth;
	}

	
	public void setAuth(BbsWebserviceAuth auth) {
		this.auth = auth;
	}

}