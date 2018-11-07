package com.jeecms.bbs.entity;

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

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.util.DateUtils;

/**
 * 用户账户提现申请
 * 
 * @author andy_hulibo@163.com 2018年11月1日下午5:13:13
 */
@Entity
@Table(name = "bbs_account_draw")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsAccountDraw implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:59:49
	 */
	private static final long serialVersionUID = 3831543348600129419L;

	public static final Short CHECKING = 0;
	public static final Short CHECKED_SUCC = 1;
	public static final Short CHECKED_FAIL = 2;
	public static final Short DRAW_SUCC = 3;

	public static final Short APPLY_TYPE_TOPIC = 1;
	public static final Short APPLY_TYPE_GIFT = 2;
	public static final Short APPLY_TYPE_LIVE = 3;

	@Id
	@Column(name = "account_draw_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "apply_account")
	private String applyAccount;

	@Column(name = "apply_amount")
	private Double applyAmount;

	@Column(name = "apply_status")
	private Short applyStatus;

	@Column(name = "apply_time")
	private Date applyTime;

	@Column(name = "apply_type")
	private Short applyType;

	@ManyToOne
	@JoinColumn(name="draw_user_id")
	private BbsUser drawUser;
	
	@ManyToOne
	@JoinColumn(name="account_pay_id")
	private BbsAccountPay accountPay;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplyAccount() {
		return applyAccount;
	}

	public void setApplyAccount(String applyAccount) {
		this.applyAccount = applyAccount;
	}

	public Double getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(Double applyAmount) {
		this.applyAmount = applyAmount;
	}

	public Short getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Short applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Short getApplyType() {
		return applyType;
	}

	public void setApplyType(Short applyType) {
		this.applyType = applyType;
	}

	public BbsUser getDrawUser() {
		return drawUser;
	}

	public void setDrawUser(BbsUser drawUser) {
		this.drawUser = drawUser;
	}

	public BbsAccountPay getAccountPay() {
		return accountPay;
	}

	public void setAccountPay(BbsAccountPay accountPay) {
		this.accountPay = accountPay;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getApplyAmount() != null) {
			json.put("applyAmount", getApplyAmount());
		} else {
			json.put("applyAmount", "");
		}
		if (getApplyStatus() != null) {
			json.put("applyStatus", getApplyStatus());
		} else {
			json.put("applyStatus", "");
		}

		if (getApplyTime() != null) {
			json.put("applyTime", DateUtils.parseDateToTimeStr(getApplyTime()));
		} else {
			json.put("applyTime", "");
		}
		if (getApplyType() != null) {
			json.put("applyType", getApplyType());
		} else {
			json.put("applyType", "");
		}
		if (getDrawUser() != null && StringUtils.isNotBlank(getDrawUser().getUsername())) {
			json.put("drawUser", getDrawUser().getUsername());
		} else {
			json.put("drawUser", "");
		}
		return json;
	}
}