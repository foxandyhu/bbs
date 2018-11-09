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
 * 用户账户提现支付
 * 
 * @author andy_hulibo@163.com 2018年11月2日上午9:58:07
 */
@Entity
@Table(name = "bbs_account_pay")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsAccountPay implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:59:42
	 */
	private static final long serialVersionUID = -8910753803346545375L;

	@Id
	@Column(name="account_pay_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="draw_num")
	private String drawNum;
	
	@Column(name="pay_account")
	private String payAccount;
	
	@Column(name="draw_account")
	private String drawAccount;
	
	@Column(name="pay_time")
	private Date payTime;
	
	@Column(name="weixin_num")
	private String weixinNum;
	
	@Column(name="alipay_num")
	private String alipayNum;

	@ManyToOne
	@JoinColumn(name="pay_user_id")
	private BbsUser payUser;
	
	@ManyToOne
	@JoinColumn(name="draw_user_id")
	private BbsUser drawUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDrawNum() {
		return drawNum;
	}

	public void setDrawNum(String drawNum) {
		this.drawNum = drawNum;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getDrawAccount() {
		return drawAccount;
	}

	public void setDrawAccount(String drawAccount) {
		this.drawAccount = drawAccount;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getWeixinNum() {
		return weixinNum;
	}

	public void setWeixinNum(String weixinNum) {
		this.weixinNum = weixinNum;
	}

	public String getAlipayNum() {
		return alipayNum;
	}

	public void setAlipayNum(String alipayNum) {
		this.alipayNum = alipayNum;
	}

	public BbsUser getPayUser() {
		return payUser;
	}

	public void setPayUser(BbsUser payUser) {
		this.payUser = payUser;
	}

	public BbsUser getDrawUser() {
		return drawUser;
	}

	public void setDrawUser(BbsUser drawUser) {
		this.drawUser = drawUser;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getDrawNum())) {
			json.put("drawNum", getDrawNum());
		} else {
			json.put("drawNum", "");
		}
		if (StringUtils.isNotBlank(getPayAccount())) {
			json.put("payAccount", getPayAccount());
		} else {
			json.put("payAccount", "");
		}
		if (StringUtils.isNotBlank(getDrawAccount())) {
			json.put("drawAccount", getDrawAccount());
		} else {
			json.put("drawAccount", "");
		}
		if (getPayTime() != null) {
			json.put("payTime", DateUtils.parseDateToTimeStr(getPayTime()));
		} else {
			json.put("payTime", "");
		}
		if (StringUtils.isNotBlank(getWeixinNum())) {
			json.put("weixinNum", getWeixinNum());
		} else {
			json.put("weixinNum", "");
		}
		if (StringUtils.isNotBlank(getAlipayNum())) {
			json.put("alipayNum", getAlipayNum());
		} else {
			json.put("alipayNum", "");
		}
		return json;
	}
}