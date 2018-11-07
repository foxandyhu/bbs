package com.jeecms.bbs.entity;

import java.io.Serializable;

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

/**
 * 第三方登录平台账号
 * 
 * @author andy_hulibo@163.com 2018年11月6日下午2:24:29
 */
@Entity
@Table(name = "bbs_third_account")
public class BbsThirdAccount implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:55:31
	 */
	private static final long serialVersionUID = 6667634913326776935L;

	public static final String QQ_KEY = "openId";
	public static final String SINA_KEY = "uid";
	public static final String QQ_PLAT = "QQ";
	public static final String SINA_PLAT = "SINA";
	public static final String WEIXIN_PLAT = "WEIXIN";
	public static final String QQ_WEBO_KEY = "weboOpenId";
	public static final String QQ_WEBO_PLAT = "QQWEBO";
	public static final String WEIXIN_KEY = "weixinOpenId";

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
		if (StringUtils.isNotBlank(getAccountKey())) {
			json.put("accountKey", getAccountKey());
		} else {
			json.put("accountKey", "");
		}
		if (StringUtils.isNotBlank(getSource())) {
			json.put("source", getSource());
		} else {
			json.put("source", "");
		}
		if (getUser() != null && StringUtils.isNotBlank(getUser().getComefrom())) {
			json.put("comefrom", getUser().getComefrom());
		} else {
			json.put("comefrom", "");
		}
		if (getUser() != null && StringUtils.isNotBlank(getUser().getRealname())) {
			json.put("realname", getUser().getRealname());
		} else {
			json.put("realname", "");
		}
		if (getUser().getGender() != null) {
			json.put("gender", getUser().getGender());
		} else {
			json.put("gender", false);
		}
		return json;
	}

	@Id
	@Column(name="account_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="account_key")
	private String accountKey;
	
	@Column(name="username")
	private String username;
	
	@Column(name="source")
	private String source;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private BbsUser user;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getAccountKey() {
		return accountKey;
	}

	
	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	
	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getSource() {
		return source;
	}

	
	public void setSource(String source) {
		this.source = source;
	}

	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

}