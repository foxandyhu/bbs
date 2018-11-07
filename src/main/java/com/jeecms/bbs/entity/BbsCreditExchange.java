package com.jeecms.bbs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 积分交易规则
 * @author andy_hulibo@163.com 2018年10月31日下午1:22:29
 */
@Entity
@Table(name = "bbs_credit_exchange")
public class BbsCreditExchange implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:58:50
	 */
	private static final long serialVersionUID = -8569866151991662517L;

	@Id
	@Column(name="eid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="expoint")
	private Integer expoint;
	
	@Column(name="exprestige")
	private Integer exprestige;
	
	@Column(name="pointoutavailable")
	private Boolean pointoutavailable;
	
	@Column(name="pointinavailable")
	private Boolean pointinavailable;
	
	@Column(name="prestigeoutavailable")
	private Boolean prestigeoutavailable;
	
	@Column(name="prestigeinavailable")
	private Boolean prestigeinavailable;
	
	@Column(name="exchangetax")
	private Float exchangetax;
	
	@Column(name="mini_balance")
	private Integer miniBalance;
	
	@Column(name="ex_money")
	private Integer exmoney;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExpoint() {
		return expoint;
	}

	public void setExpoint(Integer expoint) {
		this.expoint = expoint;
	}

	public Integer getExprestige() {
		return exprestige;
	}

	public void setExprestige(Integer exprestige) {
		this.exprestige = exprestige;
	}

	public Boolean getPointoutavailable() {
		return pointoutavailable;
	}

	public void setPointoutavailable(Boolean pointoutavailable) {
		this.pointoutavailable = pointoutavailable;
	}

	public Boolean getPointinavailable() {
		return pointinavailable;
	}

	public void setPointinavailable(Boolean pointinavailable) {
		this.pointinavailable = pointinavailable;
	}

	public Boolean getPrestigeoutavailable() {
		return prestigeoutavailable;
	}

	public void setPrestigeoutavailable(Boolean prestigeoutavailable) {
		this.prestigeoutavailable = prestigeoutavailable;
	}

	public Boolean getPrestigeinavailable() {
		return prestigeinavailable;
	}

	public void setPrestigeinavailable(Boolean prestigeinavailable) {
		this.prestigeinavailable = prestigeinavailable;
	}

	public Float getExchangetax() {
		return exchangetax;
	}

	public void setExchangetax(Float exchangetax) {
		this.exchangetax = exchangetax;
	}

	public Integer getMiniBalance() {
		return miniBalance;
	}

	public void setMiniBalance(Integer miniBalance) {
		this.miniBalance = miniBalance;
	}

	public Integer getExmoney() {
		return exmoney;
	}

	public void setExmoney(Integer exmoney) {
		this.exmoney = exmoney;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getExpoint() != null) {
			json.put("expoint", getExpoint());
		} else {
			json.put("expoint", "");
		}
		if (getExprestige() != null) {
			json.put("exprestige", getExprestige());
		} else {
			json.put("exprestige", "");
		}
		if (getExmoney() != null) {
			json.put("exmoney", getExmoney());
		} else {
			json.put("exmoney", "");
		}
		if (getPointoutavailable() != null) {
			json.put("pointoutavailable", getPointoutavailable());
		} else {
			json.put("pointoutavailable", "");
		}
		if (getPointinavailable() != null) {
			json.put("pointinavailable", getPointinavailable());
		} else {
			json.put("pointinavailable", "");
		}
		if (getPrestigeoutavailable() != null) {
			json.put("prestigeoutavailable", getPrestigeoutavailable());
		} else {
			json.put("prestigeoutavailable", "");
		}
		if (getPrestigeinavailable() != null) {
			json.put("prestigeinavailable", getPrestigeinavailable());
		} else {
			json.put("prestigeinavailable", "");
		}
		if (getExchangetax() != null) {
			json.put("exchangetax", getExchangetax());
		} else {
			json.put("exchangetax", "");
		}
		if (getMiniBalance() != null) {
			json.put("miniBalance", getMiniBalance());
		} else {
			json.put("miniBalance", "");
		}
		return json;
	}
}