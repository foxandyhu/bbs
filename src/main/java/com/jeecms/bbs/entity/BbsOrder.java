package com.jeecms.bbs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;
import com.jeecms.common.util.DateUtils;

/**
 * 订单流水记录
 * @author andy_hulibo@163.com 2018年11月2日下午3:39:42
 */
@Entity
@Table(name = "bbs_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsOrder implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:57:16
	 */
	private static final long serialVersionUID = 3443393718749417159L;

	public static final Integer PAY_METHOD_WECHAT = 1;
	public static final Integer PAY_METHOD_ALIPAY = 2;

	// 订单号错误
	public static final Integer PRE_PAY_STATUS_ORDER_NUM_ERROR = 2;
	
	// 订单成功
	public static final Integer PRE_PAY_STATUS_SUCCESS = 1;
	
	// 订单金额不足以购买内容
	public static final Integer PRE_PAY_STATUS_ORDER_AMOUNT_NOT_ENOUGH = 3;
	
	/**
	 * 主题订单类型
	 */
	public static final Short ORDER_TYPE_TOPIC = 0;
	
	/**
	 * 道具订单类型
	 */
	public static final Short ORDER_TYPE_MAGIC = 1;
	
	/**
	 * 礼物订单类型
	 */
	public static final Short ORDER_TYPE_GIFT = 2;
	
	/**
	 * 活动订单类型
	 */
	public static final Short ORDER_TYPE_LIVE = 3;
	
	/**
	 * 广告订单类型
	 */
	public static final Short ORDER_TYPE_AD = 4;

	/**
	 * 主题订单cache 0位标识
	 */
	public static final String ORDER_TYPE_CACHE_FLAG_TOPIC = "topic";
	
	/**
	 * 道具订单cache 0位标识
	 */
	public static final String ORDER_TYPE_CACHE_FLAG_MAGIC = "magic";
	
	/**
	 * 礼物订单cache 0位标识
	 */
	public static final String ORDER_TYPE_CACHE_FLAG_GIFT = "gift";
	
	/**
	 * 活动订单cache 0位标识
	 */
	public static final String ORDER_TYPE_CACHE_FLAG_LIVE = "live";
	
	/**
	 * 广告订单cache 0位标识
	 */
	public static final String ORDER_TYPE_CACHE_FLAG_AD = "ad";

	/**
	 * 支付目标标识-LIVE
	 */
	public static final String PAY_TARGET_LIVE = "live";
	
	/**
	 * 支付目标标识-主题
	 */
	public static final String PAY_TARGET_TOPIC = "topic";
	
	/**
	 * 支付目标标识-道具
	 */
	public static final String PAY_TARGET_MAGIC = "magic";
	
	/**
	 * 支付目标标识-礼物
	 */
	public static final String PAY_TARGET_GIFT = "gfit";
	
	/**
	 * 支付目标标识-广告
	 */
	public static final String PAY_TARGET_AD = "ad";

	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="order_number")
	private String orderNumber;
	
	@Column(name="data_id")
	private Integer dataId;
	
	@Column(name="data_type")
	private Short dataType;
	
	@Column(name="charge_amount")
	private Double chargeAmount;
	
	@Column(name="author_amount")
	private Double authorAmount;
	
	@Column(name="plat_amount")
	private Double platAmount;
	
	@Column(name="buy_time")
	private java.util.Date buyTime;
	
	@Column(name="order_num_weixin")
	private String orderNumWeixin;
	
	@Column(name="order_num_alipay")
	private String orderNumAlipay;
	
	@Column(name="charge_reward")
	private Short chargeReward;
	
	@Column(name="live_user_num")
	private Integer liveUserNum;
	
	@Column(name="live_used_num")
	private Integer liveUsedNum;

	@ManyToOne
	@JoinColumn(name="buy_user_id")
	private BbsUser buyUser;
	
	@ManyToOne
	@JoinColumn(name="author_user_id")
	private BbsUser authorUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public Short getDataType() {
		return dataType;
	}

	public void setDataType(Short dataType) {
		this.dataType = dataType;
	}

	public Double getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(Double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public Double getAuthorAmount() {
		return authorAmount;
	}

	public void setAuthorAmount(Double authorAmount) {
		this.authorAmount = authorAmount;
	}

	public Double getPlatAmount() {
		return platAmount;
	}

	public void setPlatAmount(Double platAmount) {
		this.platAmount = platAmount;
	}

	public java.util.Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(java.util.Date buyTime) {
		this.buyTime = buyTime;
	}

	public String getOrderNumWeixin() {
		return orderNumWeixin;
	}

	public void setOrderNumWeixin(String orderNumWeixin) {
		this.orderNumWeixin = orderNumWeixin;
	}

	public String getOrderNumAlipay() {
		return orderNumAlipay;
	}

	public void setOrderNumAlipay(String orderNumAlipay) {
		this.orderNumAlipay = orderNumAlipay;
	}

	public Short getChargeReward() {
		return chargeReward;
	}

	public void setChargeReward(Short chargeReward) {
		this.chargeReward = chargeReward;
	}

	public Integer getLiveUserNum() {
		return liveUserNum;
	}

	public void setLiveUserNum(Integer liveUserNum) {
		this.liveUserNum = liveUserNum;
	}

	public Integer getLiveUsedNum() {
		return liveUsedNum;
	}

	public void setLiveUsedNum(Integer liveUsedNum) {
		this.liveUsedNum = liveUsedNum;
	}

	
	public BbsUser getBuyUser() {
		return buyUser;
	}

	
	public void setBuyUser(BbsUser buyUser) {
		this.buyUser = buyUser;
	}

	public BbsUser getAuthorUser() {
		return authorUser;
	}

	public void setAuthorUser(BbsUser authorUser) {
		this.authorUser = authorUser;
	}

	public boolean getUserHasPaid() {
		if (StringUtils.isNotBlank(getOrderNumWeixin()) || StringUtils.isNotBlank(getOrderNumAlipay())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean getHasLiveNotUsed() {
		if (getLiveUserNum() > getLiveUsedNum()) {
			return true;
		} else {
			return false;
		}
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getChargeAmount() != null) {
			json.put("chargeAmount", getChargeAmount());
		} else {
			json.put("chargeAmount", "");
		}
		if (getBuyTime() != null) {
			json.put("buyTime", DateUtils.parseDateToTimeStr(getBuyTime()));
		} else {
			json.put("buyTime", "");
		}
		if (StringUtils.isNotBlank(getOrderNumber())) {
			json.put("orderNumber", getOrderNumber());
		} else {
			json.put("orderNumber", "");
		}
		if (getChargeReward() != null) {
			json.put("chargeReward", getChargeReward());
		} else {
			json.put("chargeReward", "");
		}
		if (getBuyUser() != null && StringUtils.isNotBlank(getBuyUser().getUsername())) {
			json.put("buyUserUserName", getBuyUser().getUsername());
		} else {
			json.put("buyUserUserName", "");
		}
		if (getBuyUser() != null && StringUtils.isNotBlank(getBuyUser().getRealname())) {
			json.put("buyUserRealName", getBuyUser().getRealname());
		} else {
			json.put("buyUserRealName", "");
		}
		if (getDataType() != null) {
			json.put("dataType", getDataType());
		} else {
			json.put("dataType", "");
		}
		if (getDataId() != null) {
			json.put("dataId", getDataId());
		} else {
			json.put("dataId", "");
		}
		if (getAuthorUser() != null && StringUtils.isNotBlank(getAuthorUser().getRealname())) {
			json.put("authorRealName", getAuthorUser().getRealname());
		} else {
			json.put("authorRealName", "");
		}
		if (getAuthorUser() != null && StringUtils.isNotBlank(getAuthorUser().getUsername())) {
			json.put("authorUserName", getAuthorUser().getUsername());
		} else {
			json.put("authorUserName", "");
		}
		if (getAuthorAmount() != null) {
			json.put("authorAmount", getAuthorAmount());
		} else {
			json.put("authorAmount", "");
		}
		if (getPlatAmount() != null) {
			json.put("platAmount", getPlatAmount());
		} else {
			json.put("platAmount", "");
		}
		if (StringUtils.isNotBlank(getOrderNumWeixin())) {
			json.put("orderNumWeixin", getOrderNumWeixin());
		} else {
			json.put("orderNumWeixin", "");
		}
		if (StringUtils.isNotBlank(getOrderNumAlipay())) {
			json.put("orderNumAlipay", getOrderNumAlipay());
		} else {
			json.put("orderNumAlipay", "");
		}
		if (getChargeReward() != null) {
			json.put("chargeReward", getChargeReward());
		} else {
			json.put("chargeReward", "");
		}
		return json;
	}

	private int prePayStatus;
	
	public int getPrePayStatus() {
		return prePayStatus;
	}

	public void setPrePayStatus(int prePayStatus) {
		this.prePayStatus = prePayStatus;
	}
}