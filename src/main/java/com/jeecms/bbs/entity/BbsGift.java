package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.core.entity.CmsSite;

/**
 * 礼物
 * @author andy_hulibo@163.com
 * 2018年10月31日下午5:37:11
 */
@Entity
@Table(name = "bbs_gift")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsGift implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:58:21
	 */
	private static final long serialVersionUID = -1947041964368328898L;

	@Id
	@Column(name="gift_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;
	
	@Column(name="pic_path")
	private String picPath;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="priority")
	private Integer priority;
	
	@Column(name="is_disabled")
	private Boolean disabled;
	
	@Column(name="gift_type")
	private Short giftType;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Short getGiftType() {
		return giftType;
	}

	public void setGiftType(Short giftType) {
		this.giftType = giftType;
	}

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

	public JSONObject convertToJson(CmsSite site, Integer https, BbsUser user) throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getName())) {
			json.put("name", getName());
		} else {
			json.put("name", "");
		}
		if (getPrice() != null) {
			json.put("price", getPrice());
		} else {
			json.put("price", "");
		}
		if (getPriority() != null) {
			json.put("priority", getPriority());
		} else {
			json.put("priority", "");
		}
		if (getGiftType() != null) {
			json.put("giftType", getGiftType());
		} else {
			json.put("giftType", "");
		}
		json.put("userGiftCount", user.getGiftCount(getId()));
		if (StringUtils.isNotBlank(getPicPath())) {
			json.put("picPath", getPicPath());
		} else {
			json.put("picPath", "");
		}
		if (getDisabled() != null) {
			json.put("disabled", getDisabled());
		} else {
			json.put("disabled", "");
		}
		if (getPriority() != null) {
			json.put("priority", getPriority());
		} else {
			json.put("priority", "");
		}
		return json;
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
		if (getTotalAmount() == null) {
			setTotalAmount(0d);
		}
		if (getDisabled() == null) {
			setDisabled(false);
		}
		if (getGiftType() == null) {
			setGiftType((short) 0);
		}
	}
}