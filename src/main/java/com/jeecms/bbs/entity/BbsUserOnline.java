package com.jeecms.bbs.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 用户在线时长统计
 * @author andy_hulibo@163.com
 * 2018年11月6日上午11:48:46
 */
@Entity
@Table(name="bbs_user_online")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class BbsUserOnline implements Serializable{

	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午3:53:26
	 */
	private static final long serialVersionUID = 1907276615616703256L;

	private Integer id;

	@Column(name="online_latest")
	private Double onlineLatest;
	
	@Column(name="online_day")
	private Double onlineDay;
	
	@Column(name="online_week")
	private Double onlineWeek;
	
	@Column(name="online_month")
	private Double onlineMonth;
	
	@Column(name="online_year")
	private Double onlineYear;
	
	@Column(name="online_total")
	private Double onlineTotal;

	@Id
	@OneToOne
	@Column(name="user_id")
	@JoinColumn(name="user_id")
	private BbsUser user;

	public void initial(){
		setOnlineDay(0d);
		setOnlineLatest(0d);
		setOnlineMonth(0d);
		setOnlineWeek(0d);
		setOnlineYear(0d);
		setOnlineTotal(0d);
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Double getOnlineLatest() {
		return onlineLatest;
	}


	public void setOnlineLatest(Double onlineLatest) {
		this.onlineLatest = onlineLatest;
	}

	
	public Double getOnlineDay() {
		return onlineDay;
	}

	
	public void setOnlineDay(Double onlineDay) {
		this.onlineDay = onlineDay;
	}


	public Double getOnlineWeek() {
		return onlineWeek;
	}

	
	public void setOnlineWeek(Double onlineWeek) {
		this.onlineWeek = onlineWeek;
	}

	
	public Double getOnlineMonth() {
		return onlineMonth;
	}

	
	public void setOnlineMonth(Double onlineMonth) {
		this.onlineMonth = onlineMonth;
	}

	public Double getOnlineYear() {
		return onlineYear;
	}

	
	public void setOnlineYear(Double onlineYear) {
		this.onlineYear = onlineYear;
	}

	public Double getOnlineTotal() {
		return onlineTotal;
	}

	
	public void setOnlineTotal(Double onlineTotal) {
		this.onlineTotal = onlineTotal;
	}


	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

}