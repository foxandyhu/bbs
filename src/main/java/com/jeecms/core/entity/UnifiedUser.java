package com.jeecms.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户
 * @author andy_hulibo@163.com 2018年11月1日下午2:05:17
 */
@Entity
@Table(name = "jo_user")
public class UnifiedUser implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:11:04
	 */
	private static final long serialVersionUID = 765327637121759904L;

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "register_time")
	private java.util.Date registerTime;

	@Column(name = "register_ip")
	private String registerIp;

	@Column(name = "last_login_time")
	private java.util.Date lastLoginTime;

	@Column(name = "last_login_ip")
	private String lastLoginIp;

	@Column(name = "login_count")
	private Integer loginCount;

	@Column(name = "reset_key")
	private String resetKey;

	@Column(name = "reset_pwd")
	private String resetPwd;

	@Column(name = "error_time")
	private java.util.Date errorTime;

	@Column(name = "error_count")
	private Integer errorCount;

	@Column(name = "error_ip")
	private String errorIp;

	@Column(name = "activation")
	private Boolean activation;

	@Column(name = "activation_code")
	private String activationCode;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public java.util.Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(java.util.Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public java.util.Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getResetKey() {
		return resetKey;
	}

	public void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}

	public String getResetPwd() {
		return resetPwd;
	}

	public void setResetPwd(String resetPwd) {
		this.resetPwd = resetPwd;
	}

	public java.util.Date getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(java.util.Date errorTime) {
		this.errorTime = errorTime;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public String getErrorIp() {
		return errorIp;
	}

	public void setErrorIp(String errorIp) {
		this.errorIp = errorIp;
	}

	public Boolean getActivation() {
		return activation;
	}

	public void setActivation(Boolean activation) {
		this.activation = activation;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

}