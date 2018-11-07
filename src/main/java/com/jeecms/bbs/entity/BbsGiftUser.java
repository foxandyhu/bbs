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

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "bbs_gift_user")
public class BbsGiftUser implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:58:14
	 */
	private static final long serialVersionUID = -1094310151209779203L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="num")
	private Integer num;

	@ManyToOne
	@JoinColumn(name="user_id")
	private BbsUser user;

	@ManyToOne
	@JoinColumn(name="gift_id")
	private BbsGift gift;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

	public BbsGift getGift() {
		return gift;
	}

	public void setGift(BbsGift gift) {
		this.gift = gift;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", getId());
		json.put("user", getUser().getUsername());
		json.put("giftId", getGift().getId());
		json.put("giftName", getGift().getName());
		json.put("num", getNum());
		return json;
	}
}