package com.jeecms.bbs.entity;

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

import com.jeecms.common.util.DateUtils;

/**
 * 用户稿费收费配置
 * 
 * @author andy_hulibo@163.com 2018年11月6日下午2:07:43
 */
@Entity
@Table(name = "bbs_user_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsUserAccount implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:53:59
	 */
	private static final long serialVersionUID = -5865394180336628780L;
	public static final byte DRAW_WEIXIN = 0;

	public static final byte DRAW_ALIPY = 1;

	@Column(name="account_weixin")
	private String accountWeixin;
	
	@Column(name="account_weixin_open_id")
	private String accountWeixinOpenId;
	
	@Column(name="account_alipy")
	private String accountAlipy;
	
	@Column(name="draw_account")
	private Short drawAccount;
	
	@Column(name="total_amount")
	private Double totalAmount;
	
	@Column(name="no_pay_amount")
	private Double noPayAmount;
	
	@Column(name="year_amount")
	private Double yearAmount;
	
	@Column(name="month_amount")
	private Double monthAmount;
	
	@Column(name="day_amount")
	private Double dayAmount;
	
	@Column(name="buy_count")
	private Integer buyCount;
	
	@Column(name="draw_count")
	private Integer drawCount;
	
	@Column(name="last_draw_time")
	private Date lastDrawTime;
	
	@Column(name="last_buy_time")
	private Date lastBuyTime;
	
	@Column(name="gift_total_amount")
	private Double giftTotalAmount;
	
	@Column(name="gift_no_draw_amount")
	private Double giftNoDrawAmount;
	
	@Column(name="gift_month_amount")
	private Double giftMonthAmount;
	
	@Column(name="gift_day_amount")
	private Double giftDayAmount;
	
	@Column(name="gift_receiver_count")
	private Integer giftReceiverCount;
	
	@Column(name="gift_draw_count")
	private Integer giftDrawCount;
	
	@Column(name="gift_last_receiver_time")
	private Date giftLastReceiverTime;
	
	@Column(name="ad_account_mount")
	private Double adAccountMount;
	
	@Column(name="ad_account_mount_total")
	private Double adAccountMountTotal;

	@Id
	@OneToOne
	@JoinColumn(name = "user_id")
	private BbsUser user;

	public String getAccountWeixin() {
		return accountWeixin;
	}

	
	public void setAccountWeixin(String accountWeixin) {
		this.accountWeixin = accountWeixin;
	}

	
	public String getAccountWeixinOpenId() {
		return accountWeixinOpenId;
	}

	
	public void setAccountWeixinOpenId(String accountWeixinOpenId) {
		this.accountWeixinOpenId = accountWeixinOpenId;
	}

	
	public String getAccountAlipy() {
		return accountAlipy;
	}

	
	public void setAccountAlipy(String accountAlipy) {
		this.accountAlipy = accountAlipy;
	}


	public Short getDrawAccount() {
		return drawAccount;
	}

	
	public void setDrawAccount(Short drawAccount) {
		this.drawAccount = drawAccount;
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

	public Double getGiftTotalAmount() {
		return giftTotalAmount;
	}

	public void setGiftTotalAmount(Double giftTotalAmount) {
		this.giftTotalAmount = giftTotalAmount;
	}

	public Double getGiftNoDrawAmount() {
		return giftNoDrawAmount;
	}

	public void setGiftNoDrawAmount(Double giftNoDrawAmount) {
		this.giftNoDrawAmount = giftNoDrawAmount;
	}

	public Double getGiftMonthAmount() {
		return giftMonthAmount;
	}

	public void setGiftMonthAmount(Double giftMonthAmount) {
		this.giftMonthAmount = giftMonthAmount;
	}

	public Double getGiftDayAmount() {
		return giftDayAmount;
	}

	public void setGiftDayAmount(Double giftDayAmount) {
		this.giftDayAmount = giftDayAmount;
	}

	public Integer getGiftReceiverCount() {
		return giftReceiverCount;
	}

	public void setGiftReceiverCount(Integer giftReceiverCount) {
		this.giftReceiverCount = giftReceiverCount;
	}

	public Integer getGiftDrawCount() {
		return giftDrawCount;
	}

	public void setGiftDrawCount(Integer giftDrawCount) {
		this.giftDrawCount = giftDrawCount;
	}

	public Date getGiftLastReceiverTime() {
		return giftLastReceiverTime;
	}

	public void setGiftLastReceiverTime(Date giftLastReceiverTime) {
		this.giftLastReceiverTime = giftLastReceiverTime;
	}

	public Double getAdAccountMount() {
		return adAccountMount;
	}

	public void setAdAccountMount(Double adAccountMount) {
		this.adAccountMount = adAccountMount;
	}

	public Double getAdAccountMountTotal() {
		return adAccountMountTotal;
	}

	public void setAdAccountMountTotal(Double adAccountMountTotal) {
		this.adAccountMountTotal = adAccountMountTotal;
	}

	
	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getUser() != null) {
			json.put("id", getUser().getId());
		} else {
			json.put("id", "");
		}
		if (getTotalAmount() != null) {
			json.put("totalAmount", getTotalAmount());
		} else {
			json.put("totalAmount", "");
		}
		if (getNoPayAmount() != null) {
			json.put("noPayAmount", getNoPayAmount());
		} else {
			json.put("noPayAmount", "");
		}
		if (getYearAmount() != null) {
			json.put("yearAmount", getYearAmount());
		} else {
			json.put("yearAmount", "");
		}
		if (getMonthAmount() != null) {
			json.put("monthAmount", getMonthAmount());
		} else {
			json.put("monthAmount", "");
		}
		if (getDayAmount() != null) {
			json.put("dayAmount", getDayAmount());
		} else {
			json.put("dayAmount", "");
		}
		if (getDrawCount() != null) {
			json.put("drawCount", getDrawCount());
		} else {
			json.put("drawCount", "");
		}
		if (getBuyCount() != null) {
			json.put("buyCount", getBuyCount());
		} else {
			json.put("buyCount", "");
		}
		if (getDrawAccount() != null) {
			json.put("drawAccount", getDrawAccount());
		} else {
			json.put("drawAccount", "");
		}
		if (getUser() != null && StringUtils.isNotBlank(getUser().getUsername())) {
			json.put("user", getUser().getUsername());
		} else {
			json.put("user", "");
		}
		if (getLastDrawTime() != null) {
			json.put("lastDrawTime", DateUtils.parseDateToTimeStr(getLastDrawTime()));
		} else {
			json.put("lastDrawTime", "");
		}
		if (getLastBuyTime() != null) {
			json.put("lastBuyTime", DateUtils.parseDateToTimeStr(getLastBuyTime()));
		} else {
			json.put("lastBuyTime", "");
		}
		if (getGiftTotalAmount() != null) {
			json.put("giftTotalAmount", getGiftTotalAmount());
		} else {
			json.put("giftTotalAmount", "");
		}
		if (getGiftNoDrawAmount() != null) {
			json.put("giftNoDrawAmount", getGiftNoDrawAmount());
		} else {
			json.put("giftNoDrawAmount", "");
		}
		if (getGiftMonthAmount() != null) {
			json.put("giftMonthAmount", getGiftMonthAmount());
		} else {
			json.put("giftMonthAmount", "");
		}
		if (getGiftDayAmount() != null) {
			json.put("giftDayAmount", getGiftDayAmount());
		} else {
			json.put("giftDayAmount", "");
		}
		if (getGiftReceiverCount() != null) {
			json.put("giftReceiverCount", getGiftReceiverCount());
		} else {
			json.put("giftReceiverCount", "");
		}
		if (getGiftDrawCount() != null) {
			json.put("giftDrawCount", getGiftDrawCount());
		} else {
			json.put("giftDrawCount", "");
		}
		if (getGiftLastReceiverTime() != null) {
			json.put("giftLastReceiverTime", DateUtils.parseDateToTimeStr(getGiftLastReceiverTime()));
		} else {
			json.put("giftLastReceiverTime", "");
		}
		return json;
	}
}