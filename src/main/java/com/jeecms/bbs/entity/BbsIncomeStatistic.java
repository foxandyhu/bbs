package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.util.DateUtils;

/**
 * 每日收益统计
 * @author andy_hulibo@163.com
 * 2018年10月31日下午5:36:43
 */
@Entity
@Table(name="bbs_income_statistic")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class BbsIncomeStatistic implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:58:00
	 */
	private static final long serialVersionUID = -1808663216466903262L;

	// 0总收益 1广告收益、2道具收益、3礼物收益、4live门票收益、5帖子收益
	public static final Short TYPE_AD = 1;
	public static final Short TYPE_MAGIC = 2;
	public static final Short TYPE_GIFT = 3;
	public static final Short TYPE_TICKET = 4;
	public static final Short TYPE_POST = 5;
	public static final Short TYPE_ALL = 0;

	@Id
	@Column(name="income_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="total_income_amount")
	private Double totalIncomeAmount;
	
	@Column(name="ad_income_amount")
	private Double adIncomeAmount;
	
	@Column(name="magic_income_amount")
	private Double magicIncomeAmount;
	
	@Column(name="gift_income_amount")
	private Double giftIncomeAmount;
	
	@Column(name="live_income_amount")
	private Double liveIncomeAmount;
	
	@Column(name="post_income_amount")
	private Double postIncomeAmount;
	
	@Column(name="income_date")
	private Date incomeDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotalIncomeAmount() {
		return totalIncomeAmount;
	}

	public void setTotalIncomeAmount(Double totalIncomeAmount) {
		this.totalIncomeAmount = totalIncomeAmount;
	}

	public Double getAdIncomeAmount() {
		return adIncomeAmount;
	}

	public void setAdIncomeAmount(Double adIncomeAmount) {
		this.adIncomeAmount = adIncomeAmount;
	}

	public Double getMagicIncomeAmount() {
		return magicIncomeAmount;
	}

	public void setMagicIncomeAmount(Double magicIncomeAmount) {
		this.magicIncomeAmount = magicIncomeAmount;
	}

	public Double getGiftIncomeAmount() {
		return giftIncomeAmount;
	}

	public void setGiftIncomeAmount(Double giftIncomeAmount) {
		this.giftIncomeAmount = giftIncomeAmount;
	}

	public Double getLiveIncomeAmount() {
		return liveIncomeAmount;
	}

	public void setLiveIncomeAmount(Double liveIncomeAmount) {
		this.liveIncomeAmount = liveIncomeAmount;
	}

	public Double getPostIncomeAmount() {
		return postIncomeAmount;
	}

	public void setPostIncomeAmount(Double postIncomeAmount) {
		this.postIncomeAmount = postIncomeAmount;
	}

	public Date getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getTotalIncomeAmount() != null) {
			json.put("totalIncomeAmount", getTotalIncomeAmount());
		} else {
			json.put("totalIncomeAmount", "");
		}
		if (getAdIncomeAmount() != null) {
			json.put("adIncomeAmount", getAdIncomeAmount());
		} else {
			json.put("adIncomeAmount", "");
		}
		if (getMagicIncomeAmount() != null) {
			json.put("magicIncomeAmount", getMagicIncomeAmount());
		} else {
			json.put("magicIncomeAmount", "");
		}
		if (getGiftIncomeAmount() != null) {
			json.put("giftIncomeAmount", getGiftIncomeAmount());
		} else {
			json.put("giftIncomeAmount", "");
		}
		if (getLiveIncomeAmount() != null) {
			json.put("liveIncomeAmount", getLiveIncomeAmount());
		} else {
			json.put("liveIncomeAmount", "");
		}
		if (getPostIncomeAmount() != null) {
			json.put("postIncomeAmount", getPostIncomeAmount());
		} else {
			json.put("postIncomeAmount", "");
		}
		if (getIncomeDate() != null) {
			json.put("incomeDate", DateUtils.parseDateToTimeStr(getIncomeDate()));
		} else {
			json.put("incomeDate", "");
		}
		return json;
	}

	public enum BbsIncomeType {
		/**
		 * 总收益
		 */
		all,
		/**
		 * 广告收益
		 */
		ad,
		/**
		 * 道具收益
		 */
		magic,
		/**
		 * 礼物收益
		 */
		gift,
		/**
		 * live门票收益
		 */
		ticket,
		/**
		 * 帖子收益
		 */
		post
	};

	public void init() {
		if (getAdIncomeAmount() == null) {
			setAdIncomeAmount(0d);
		}
		if (getGiftIncomeAmount() == null) {
			setGiftIncomeAmount(0d);
		}
		if (getLiveIncomeAmount() == null) {
			setLiveIncomeAmount(0d);
		}
		if (getMagicIncomeAmount() == null) {
			setMagicIncomeAmount(0d);
		}
		if (getPostIncomeAmount() == null) {
			setPostIncomeAmount(0d);
		}
		if (getTotalIncomeAmount() == null) {
			setTotalIncomeAmount(0d);
		}
		if (getIncomeDate() == null) {
			setIncomeDate(Calendar.getInstance().getTime());
		}
	}
}