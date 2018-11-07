package com.jeecms.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 认证信息
 * @author andy_hulibo@163.com
 * 2018年11月1日下午1:43:17
 */
@Entity
@Table(name = "jo_authentication")
public class Authentication implements Serializable {
	
	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:12:16
	 */
	private static final long serialVersionUID = 137267778678118534L;

	@Id
	@Column(name="authentication_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="userid")
	private Integer uid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="login_time")
	private Date loginTime;
	
	@Column(name="login_ip")
	private String loginIp;
	
	@Column(name="update_time")
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void init() {
		Date now = new Timestamp(System.currentTimeMillis());
		setLoginTime(now);
		setUpdateTime(now);
	}
}