package com.jeecms.bbs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 限制发帖回帖
 * @author andy_hulibo@163.com
 * 2018年10月31日下午5:40:43
 */
@Entity
@Table(name="bbs_limit")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsLimit implements Serializable {
	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:57:53
	 */
	private static final long serialVersionUID = 577265935886289080L;

	@Id
	@Column(name="limit_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="ip")
	private String ip;
	
	@Column(name="user_id")
	private Integer userId;

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getIp())) {
			json.put("ip", getIp());
		} else {
			json.put("ip", "");
		}
		if (getUserId() != null) {
			json.put("userId", getUserId());
		} else {
			json.put("userId", "");
		}
		return json;
	}

}