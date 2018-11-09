package com.jeecms.plug.live.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 活动live人数佣金比例
 * 
 * @author andy_hulibo@163.com 2018年11月1日下午2:26:44
 */
@Entity
@Table(name = "bbs_live_rate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsLiveRate implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:40:08
	 */
	private static final long serialVersionUID = 6210630953274050026L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_num")
	private Integer userNum;

	@Column(name = "rate")
	private Double rate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

}