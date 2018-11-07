package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
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

/**
 * webservices认证表
 * 
 * @author andy_hulibo@163.com 2018年11月6日下午2:58:52
 */
@Entity
@Table(name = "bbs_webservice_auth")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsWebserviceAuth implements Serializable{

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:52:56
	 */
	private static final long serialVersionUID = 8302409980653419632L;

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getUsername())) {
			json.put("username", getUsername());
		} else {
			json.put("username", "");
		}
		if (StringUtils.isNotBlank(getSystem())) {
			json.put("system", getSystem());
		} else {
			json.put("system", "");
		}
		if (StringUtils.isNotBlank(getPassword())) {
			json.put("password", getPassword());
		} else {
			json.put("password", "");
		}
		if (getEnable() != null) {
			json.put("enable", getEnable());
		} else {
			json.put("enable", "");
		}
		return json;
	}

	public void init() {
		if (getEnable() == null) {
			setEnable(false);
		}
	}

	public Boolean getEnable() {
		return isEnable();
	}

	@Id
	@Column(name="auth_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="system")
	private String system;
	
	@Column(name="is_enable")
	private boolean enable;

	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="auth")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<BbsWebserviceCallRecord> callRecords;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<BbsWebserviceCallRecord> getCallRecords() {
		return callRecords;
	}

	public void setCallRecords(Set<BbsWebserviceCallRecord> callRecords) {
		this.callRecords = callRecords;
	}
}