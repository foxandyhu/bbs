package com.jeecms.plug.live.entity;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jeecms.bbs.entity.BbsOrder;
import com.jeecms.bbs.entity.BbsUser;

/**
 * 活动live参与者
 * 
 * @author andy_hulibo@163.com 2018年11月1日下午4:17:12
 */
@Entity
@Table(name = "bbs_live_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsLiveUser implements Serializable {
	
	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:40:00
	 */
	private static final long serialVersionUID = -6773584063076956868L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private java.lang.Long id;

	@Column(name="buy_time")
	private Date buyTime;
	
	@Column(name="join_time")
	private Date joinTime;

	@ManyToOne
	@JoinColumn(name = "live_id")
	private BbsLive live;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private BbsUser joinUser;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private BbsOrder order;

	
	public java.lang.Long getId() {
		return id;
	}


	public void setId(java.lang.Long id) {
		this.id = id;
	}

	
	public Date getBuyTime() {
		return buyTime;
	}

	
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	
	public Date getJoinTime() {
		return joinTime;
	}

	
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	
	public BbsLive getLive() {
		return live;
	}

	
	public void setLive(BbsLive live) {
		this.live = live;
	}


	public BbsUser getJoinUser() {
		return joinUser;
	}

	
	public void setJoinUser(BbsUser joinUser) {
		this.joinUser = joinUser;
	}

	public BbsOrder getOrder() {
		return order;
	}

	public void setOrder(BbsOrder order) {
		this.order = order;
	}

}