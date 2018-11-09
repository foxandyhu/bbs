package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "jb_api_user_login")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class ApiUserLogin implements Serializable {

	public static Short USER_STATUS_LOGIN = 1;
	public static Short USER_STATUS_LOGOUT = 2;
	public static Short USER_STATUS_LOGOVERTIME = 3;
	public static Short USER_STATUS_FORGE = 4;

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:00:09
	 */
	private static final long serialVersionUID = 4043929346034794579L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "session_key", length = 100)
	private String sessionKey;

	@Column(name = "username", nullable = false, length = 255)
	private String username;

	@Column(name = "login_time")
	private Date loginTime;

	@Column(name = "login_count")
	private Integer loginCount;

	@Column(name = "active_time")
	private Date activeTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
}