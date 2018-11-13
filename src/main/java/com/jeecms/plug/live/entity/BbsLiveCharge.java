package com.jeecms.plug.live.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * live收益统计
 * @author andy_hulibo@163.com 2018年11月1日下午2:15:49
 */
@Entity
@Table(name="bbs_live_charge")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsLiveCharge implements Serializable {
	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:40:23
	 */
	private static final long serialVersionUID = 6953620611365481927L;

	@Column(name="total_amount")
	private Double totalAmount;
	
	@Column(name="year_amount")
	private Double yearAmount;
	
	@Column(name="month_amount")
	private Double monthAmount;
	
	@Column(name="day_amount")
	private Double dayAmount;
	
	@Column(name="last_buy_time")
	private Date lastBuyTime;
	
	@Column(name="ticket_num")
	private Integer ticketNum;
	
	@Column(name="gift_num")
	private Integer giftNum;

	@Id
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="live_id")
	private BbsLive live;

	public Double getTotalAmount() {
		return totalAmount;
	}

	
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public Double getYearAmount() {
		return yearAmount;
	}

	
	public void setYearAmount(Double yearAmount) {
		this.yearAmount = yearAmount;
	}

	public Double getMonthAmount() {
		return monthAmount;
	}


	public void setMonthAmount(Double monthAmount) {
		this.monthAmount = monthAmount;
	}

	
	public Double getDayAmount() {
		return dayAmount;
	}

	
	public void setDayAmount(Double dayAmount) {
		this.dayAmount = dayAmount;
	}

	
	public Date getLastBuyTime() {
		return lastBuyTime;
	}

	public void setLastBuyTime(Date lastBuyTime) {
		this.lastBuyTime = lastBuyTime;
	}

	public Integer getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(Integer ticketNum) {
		this.ticketNum = ticketNum;
	}

	public Integer getGiftNum() {
		return giftNum;
	}

	public void setGiftNum(Integer giftNum) {
		this.giftNum = giftNum;
	}

	public BbsLive getLive() {
		return live;
	}

	public void setLive(BbsLive live) {
		this.live = live;
	}
}