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

@Entity
@Table(name = "bbs_config_charge")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsConfigCharge implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:58:58
	 */
	private static final long serialVersionUID = 8462119243376750676L;

	@Id
	@Column(name = "config_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "weixin_appid")
	private String weixinAppId;

	@Column(name = "weixin_secret")
	private String weixinSecret;

	@Column(name = "weixin_account")
	private String weixinAccount;

	@Column(name = "weixin_password")
	private String weixinPassword;

	@Column(name = "alipay_partner_id")
	private String alipayPartnerId;

	@Column(name = "alipay_account")
	private String alipayAccount;

	@Column(name = "alipay_key")
	private String alipayKey;

	@Column(name = "alipay_appid")
	private String alipayAppId;

	@Column(name = "alipay_public_key")
	private String alipayPublicKey;

	@Column(name = "alipay_private_key")
	private String alipayPrivateKey;

	@Column(name = "charge_ratio")
	private Double chargeRatio;

	@Column(name = "min_draw_amount")
	private Double minDrawAmount;

	@Column(name = "commission_total")
	private Double commissionTotal;

	@Column(name = "commission_year")
	private Double commissionYear;

	@Column(name = "commission_month")
	private Double commissionMonth;

	@Column(name = "commission_day")
	private Double commissionDay;

	@Column(name = "last_buy_time")
	private Date lastBuyTime;

	@Column(name = "pay_transfer_password")
	private String payTransferPassword;

	@Column(name = "transfer_api_password")
	private String transferApiPassword;

	@Column(name = "reward_min")
	private Double rewardMin;

	@Column(name = "reward_max")
	private Double rewardMax;

	@Column(name = "reward_pattern")
	private Boolean rewardPattern;

	@Column(name = "gift_charge_ratio")
	private Double giftChargeRatio;

	@Column(name = "profit_magic_total")
	private Double profitMagicTotal;

	@Column(name = "profit_gift_total")
	private Double profitGiftTotal;

	@Column(name = "profit_post_total")
	private Double profitPostTotal;

	@Column(name = "profit_live_total")
	private Double profitLiveTotal;

	@Column(name = "profit_ad_total")
	private Double profitAdTotal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWeixinAppId() {
		return weixinAppId;
	}

	public void setWeixinAppId(String weixinAppId) {
		this.weixinAppId = weixinAppId;
	}

	public String getWeixinSecret() {
		return weixinSecret;
	}

	public void setWeixinSecret(String weixinSecret) {
		this.weixinSecret = weixinSecret;
	}

	public String getWeixinAccount() {
		return weixinAccount;
	}

	public void setWeixinAccount(String weixinAccount) {
		this.weixinAccount = weixinAccount;
	}

	public String getWeixinPassword() {
		return weixinPassword;
	}

	public void setWeixinPassword(String weixinPassword) {
		this.weixinPassword = weixinPassword;
	}

	public String getAlipayPartnerId() {
		return alipayPartnerId;
	}

	public void setAlipayPartnerId(String alipayPartnerId) {
		this.alipayPartnerId = alipayPartnerId;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public String getAlipayKey() {
		return alipayKey;
	}

	public void setAlipayKey(String alipayKey) {
		this.alipayKey = alipayKey;
	}

	public String getAlipayAppId() {
		return alipayAppId;
	}

	public void setAlipayAppId(String alipayAppId) {
		this.alipayAppId = alipayAppId;
	}

	public String getAlipayPublicKey() {
		return alipayPublicKey;
	}

	public void setAlipayPublicKey(String alipayPublicKey) {
		this.alipayPublicKey = alipayPublicKey;
	}

	public String getAlipayPrivateKey() {
		return alipayPrivateKey;
	}

	public void setAlipayPrivateKey(String alipayPrivateKey) {
		this.alipayPrivateKey = alipayPrivateKey;
	}

	public Double getChargeRatio() {
		return chargeRatio;
	}

	public void setChargeRatio(Double chargeRatio) {
		this.chargeRatio = chargeRatio;
	}

	public Double getMinDrawAmount() {
		return minDrawAmount;
	}

	public void setMinDrawAmount(Double minDrawAmount) {
		this.minDrawAmount = minDrawAmount;
	}

	public Double getCommissionTotal() {
		return commissionTotal;
	}

	public void setCommissionTotal(Double commissionTotal) {
		this.commissionTotal = commissionTotal;
	}

	public Double getCommissionYear() {
		return commissionYear;
	}

	public void setCommissionYear(Double commissionYear) {
		this.commissionYear = commissionYear;
	}

	public Double getCommissionMonth() {
		return commissionMonth;
	}

	public void setCommissionMonth(Double commissionMonth) {
		this.commissionMonth = commissionMonth;
	}

	public Double getCommissionDay() {
		return commissionDay;
	}

	public void setCommissionDay(Double commissionDay) {
		this.commissionDay = commissionDay;
	}

	public Date getLastBuyTime() {
		return lastBuyTime;
	}

	public void setLastBuyTime(Date lastBuyTime) {
		this.lastBuyTime = lastBuyTime;
	}

	public String getPayTransferPassword() {
		return payTransferPassword;
	}

	public void setPayTransferPassword(String payTransferPassword) {
		this.payTransferPassword = payTransferPassword;
	}

	public String getTransferApiPassword() {
		return transferApiPassword;
	}

	public void setTransferApiPassword(String transferApiPassword) {
		this.transferApiPassword = transferApiPassword;
	}

	public Double getRewardMin() {
		return rewardMin;
	}

	public void setRewardMin(Double rewardMin) {
		this.rewardMin = rewardMin;
	}

	public Double getRewardMax() {
		return rewardMax;
	}

	public void setRewardMax(Double rewardMax) {
		this.rewardMax = rewardMax;
	}

	public Boolean getRewardPattern() {
		return rewardPattern;
	}

	public void setRewardPattern(Boolean rewardPattern) {
		this.rewardPattern = rewardPattern;
	}

	public Double getGiftChargeRatio() {
		return giftChargeRatio;
	}

	public void setGiftChargeRatio(Double giftChargeRatio) {
		this.giftChargeRatio = giftChargeRatio;
	}

	public Double getProfitMagicTotal() {
		return profitMagicTotal;
	}

	public void setProfitMagicTotal(Double profitMagicTotal) {
		this.profitMagicTotal = profitMagicTotal;
	}

	public Double getProfitGiftTotal() {
		return profitGiftTotal;
	}

	public void setProfitGiftTotal(Double profitGiftTotal) {
		this.profitGiftTotal = profitGiftTotal;
	}

	public Double getProfitPostTotal() {
		return profitPostTotal;
	}

	public void setProfitPostTotal(Double profitPostTotal) {
		this.profitPostTotal = profitPostTotal;
	}

	public Double getProfitLiveTotal() {
		return profitLiveTotal;
	}

	public void setProfitLiveTotal(Double profitLiveTotal) {
		this.profitLiveTotal = profitLiveTotal;
	}

	public Double getProfitAdTotal() {
		return profitAdTotal;
	}

	public void setProfitAdTotal(Double profitAdTotal) {
		this.profitAdTotal = profitAdTotal;
	}

	public JSONObject convertToJson(Boolean isConfig) throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (isConfig) {
			if (StringUtils.isNotBlank(getWeixinAppId())) {
				json.put("weixinAppId", getWeixinAppId());
			} else {
				json.put("weixinAppId", "");
			}
			if (StringUtils.isNotBlank(getWeixinSecret())) {
				json.put("weixinSecret", getWeixinSecret());
			} else {
				json.put("weixinSecret", "");
			}
			if (StringUtils.isNotBlank(getWeixinAccount())) {
				json.put("weixinAccount", getWeixinAccount());
			} else {
				json.put("weixinAccount", "");
			}
			if (StringUtils.isNotBlank(getWeixinPassword())) {
				json.put("weixinPassword", getWeixinPassword());
			} else {
				json.put("weixinPassword", "");
			}
			if (StringUtils.isNotBlank(getTransferApiPassword())) {
				json.put("transferApiPassword", getTransferApiPassword());
			} else {
				json.put("transferApiPassword", "");
			}
			if (StringUtils.isNotBlank(getPayTransferPassword())) {
				json.put("payTransferPassword", getPayTransferPassword());
			} else {
				json.put("payTransferPassword", "");
			}
			if (getRewardPattern() != null) {
				json.put("rewardPattern", getRewardPattern());
			} else {
				json.put("rewardPattern", "");
			}
			if (getRewardMin() != null) {
				json.put("rewardMin", getRewardMin());
			} else {
				json.put("rewardMin", "");
			}
			if (getRewardMax() != null) {
				json.put("rewardMax", getRewardMax());
			} else {
				json.put("rewardMax", "");
			}
			if (StringUtils.isNotBlank(getAlipayPartnerId())) {
				json.put("alipayPartnerId", getAlipayPartnerId());
			} else {
				json.put("alipayPartnerId", "");
			}
			if (StringUtils.isNotBlank(getAlipayAccount())) {
				json.put("alipayAccount", getAlipayAccount());
			} else {
				json.put("alipayAccount", "");
			}
			if (StringUtils.isNotBlank(getAlipayKey())) {
				json.put("alipayKey", getAlipayKey());
			} else {
				json.put("alipayKey", "");
			}
			if (StringUtils.isNotBlank(getAlipayAppId())) {
				json.put("alipayAppId", getAlipayAppId());
			} else {
				json.put("alipayAppId", "");
			}
			if (StringUtils.isNotBlank(getAlipayPublicKey())) {
				json.put("alipayPublicKey", getAlipayPublicKey());
			} else {
				json.put("alipayPublicKey", "");
			}
			if (StringUtils.isNotBlank(getAlipayPrivateKey())) {
				json.put("alipayPrivateKey", getAlipayPrivateKey());
			} else {
				json.put("alipayPrivateKey", "");
			}
			if (getChargeRatio() != null) {
				json.put("chargeRatio", getChargeRatio());
			} else {
				json.put("chargeRatio", "");
			}
			if (getMinDrawAmount() != null) {
				json.put("minDrawAmount", getMinDrawAmount());
			} else {
				json.put("minDrawAmount", "");
			}
			if (getGiftChargeRatio() != null) {
				json.put("giftChargeRatio", getGiftChargeRatio());
			} else {
				json.put("giftChargeRatio", "");
			}
		} else {
			if (getCommissionTotal() != null) {
				json.put("commissionTotal", getCommissionTotal());
			} else {
				json.put("commissionTotal", "");
			}
			if (getCommissionYear() != null) {
				json.put("commissionYear", getCommissionYear());
			} else {
				json.put("commissionYear", "");
			}
			if (getCommissionMonth() != null) {
				json.put("commissionMonth", getCommissionMonth());
			} else {
				json.put("commissionMonth", "");
			}
			if (getCommissionDay() != null) {
				json.put("commissionDay", getCommissionDay());
			} else {
				json.put("commissionDay", "");
			}

		}
		return json;
	}
}