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

/**
 * 登录日志
 *
 * @author: andy_hulibo@163.com
 * @date: 2018/11/12 16:21
 */
@Entity
@Table(name = "bbs_login_log")
public class BbsLoginLog implements Serializable {

    private static final long serialVersionUID = 257829176572308376L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 退出时间
     */
    @Column(name = "logout_time")
    private Date logoutTime;

    /**
     * 登录IP
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 登录用户
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private BbsUser user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public BbsUser getUser() {
        return user;
    }

    public void setUser(BbsUser user) {
        this.user = user;
    }
}