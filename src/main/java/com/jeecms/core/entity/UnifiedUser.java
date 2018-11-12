package com.jeecms.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BBS用户实体类主要描述用户注册登录基本信息相关
 *
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

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 电子邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 注册时间
     */
    @Column(name = "register_time")
    private java.util.Date registerTime;

    /**
     * 注册IP
     */
    @Column(name = "register_ip")
    private String registerIp;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private java.util.Date lastLoginTime;

    /**
     * 最后登录IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 登录次数
     */
    @Column(name = "login_count")
    private Integer loginCount;

    /**
     * 重置密码Key
     */
    @Column(name = "reset_key")
    private String resetKey;

    /**
     * 重置密码value
     */
    @Column(name = "reset_pwd")
    private String resetPwd;

    /**
     * 登录错误时间
     */
    @Column(name = "error_time")
    private java.util.Date errorTime;

    /**
     * 登录错误次数
     */
    @Column(name = "error_count")
    private Integer errorCount;

    /**
     * 登录错误IP
     */
    @Column(name = "error_ip")
    private String errorIp;

    /**
     * 激活状态
     */
    @Column(name = "activation")
    private Boolean activation;

    /**
     * 激活码
     */
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