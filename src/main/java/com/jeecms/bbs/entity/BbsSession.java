package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Calendar;
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
 * 论坛会话
 * @author andy_hulibo@163.com
 * 2018年11月2日下午4:09:04
 */
@Entity
@Table(name = "bbs_session")
public class BbsSession implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:55:39
	 */
	private static final long serialVersionUID = 6447570201996772571L;

	@Id
	@Column(name="sid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="session_id")
	private String sessionId;
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="first_activetime")
	private Date firstActivetime;
	
	@Column(name="last_activetime")
	private Date lastActivetime;

	@ManyToOne
	@JoinColumn(name="user_id")
	private BbsUser user;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getSessionId() {
		return sessionId;
	}

	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
	public String getIp() {
		return ip;
	}

	
	public void setIp(String ip) {
		this.ip = ip;
	}

	
	public Date getFirstActivetime() {
		return firstActivetime;
	}

	
	public void setFirstActivetime(Date firstActivetime) {
		this.firstActivetime = firstActivetime;
	}

	
	public Date getLastActivetime() {
		return lastActivetime;
	}

	
	public void setLastActivetime(Date lastActivetime) {
		this.lastActivetime = lastActivetime;
	}

	
	public BbsUser getUser() {
		return user;
	}

	
	public void setUser(BbsUser user) {
		this.user = user;
	}

	public boolean isOnline() {
		Date lastActiveTime = getLastActivetime();
		Date now = Calendar.getInstance().getTime();
		long interval = now.getTime() - lastActiveTime.getTime();
		long intervalMinu = interval / 1000 / 60;
		if (intervalMinu > 20) {
			return false;
		} else {
			return true;
		}
	}
}