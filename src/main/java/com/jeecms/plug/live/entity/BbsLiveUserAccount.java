package com.jeecms.plug.live.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.common.util.DateUtils;

/**
 * 主讲人收益统计
 * @author andy_hulibo@163.com
 * 2018年11月1日下午2:36:18
 */
@Entity
@Table(name = "bbs_live_user_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsLiveUserAccount implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:39:53
	 */
	private static final long serialVersionUID = -1594260021077691667L;

	private Integer id;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "no_pay_amount")
	private Double noPayAmount;

	@Column(name = "year_amount")
	private Double yearAmount;

	@Column(name = "month_amount")
	private Double monthAmount;

	@Column(name = "day_amount")
	private Double dayAmount;

	@Column(name = "buy_count")
	private Integer buyCount;

	@Column(name = "draw_count")
	private Integer drawCount;

	@Column(name = "last_draw_time")
	private Date lastDrawTime;

	@Column(name = "last_buy_time")
	private Date lastBuyTime;

	@Column(name = "ticket_num")
	private Integer ticketNum;

	@Column(name = "gift_num")
	private Integer giftNum;

	@Column(name = "top_priority")
	private Integer topPriority;

	@Column(name = "check_time")
	private Date checkTime;

	@Id
	@OneToOne
	@JoinColumn(name = "user_id")
	private BbsUser user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getNoPayAmount() {
		return noPayAmount;
	}

	public void setNoPayAmount(Double noPayAmount) {
		this.noPayAmount = noPayAmount;
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

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Integer getDrawCount() {
		return drawCount;
	}

	public void setDrawCount(Integer drawCount) {
		this.drawCount = drawCount;
	}

	public Date getLastDrawTime() {
		return lastDrawTime;
	}

	public void setLastDrawTime(Date lastDrawTime) {
		this.lastDrawTime = lastDrawTime;
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

	public Integer getTopPriority() {
		return topPriority;
	}

	public void setTopPriority(Integer topPriority) {
		this.topPriority = topPriority;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

	public void init() {
		if (getDayAmount() == null) {
			setDayAmount(0d);
		}
		if (getMonthAmount() == null) {
			setMonthAmount(0d);
		}
		if (getYearAmount() == null) {
			setYearAmount(0d);
		}
		if (getNoPayAmount() == null) {
			setNoPayAmount(0d);
		}
		if (getTotalAmount() == null) {
			setTotalAmount(0d);
		}
		if (getDrawCount() == null) {
			setDrawCount(0);
		}
		if (getBuyCount() == null) {
			setBuyCount(0);
		}
		if (getTicketNum() == null) {
			setTicketNum(0);
		}
		if (getGiftNum() == null) {
			setGiftNum(0);
		}
		if (getTopPriority() == null) {
			setTopPriority(0);
		}
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getUser() != null && StringUtils.isNotBlank(getUser().getRealname())) {
			json.put("realName", getUser().getRealname());
		} else {
			json.put("realName", "");
		}
		if (getUser() != null && StringUtils.isNotBlank(getUser().getUsername())) {
			json.put("userName", getUser().getUsername());
		} else {
			json.put("userName", "");
		}
		if (getCheckTime() != null) {
			json.put("checkTime", DateUtils.parseDateToDateStr(getCheckTime()));
		} else {
			json.put("checkTime", "");
		}
		if (getTicketNum() != null) {
			json.put("ticketNum", getTicketNum());
		} else {
			json.put("ticketNum", "");
		}
		if (getTotalAmount() != null) {
			json.put("totalAmount", getTotalAmount());
		} else {
			json.put("totalAmount", "");
		}
		if (getGiftNum() != null) {
			json.put("giftNum", getGiftNum());
		} else {
			json.put("giftNum", "");
		}
		if (getTopPriority() != null) {
			json.put("topPriority", getTopPriority());
		} else {
			json.put("topPriority", "");
		}
		return json;
	}
}