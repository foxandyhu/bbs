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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "jb_friendship")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsFriendShip implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:58:29
	 */
	private static final long serialVersionUID = 7437233497285500142L;

	/**
	 * 申请中
	 */
	public static final int APPLYING = 0;
	/**
	 * 接受
	 */
	public static final int ACCEPT = 1;
	/**
	 * 拒绝
	 */
	public static final int REFUSE = 2;

	@Id
	@Column(name="friendship_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="status")
	private Integer status;

	@ManyToOne
	@JoinColumn(name="user_id")
	private BbsUser user;
	
	@ManyToOne
	@JoinColumn(name="friend_id")	
	private BbsUser friend;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BbsUser getUser() {
		return user;
	}


	public void setUser(BbsUser user) {
		this.user = user;
	}

	
	public BbsUser getFriend() {
		return friend;
	}

	public void setFriend(BbsUser friend) {
		this.friend = friend;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", getId());
		// 0:申请中;1:接受;2:拒绝
		json.put("status", getStatus());
		json.put("user", getUser().getUsername());
		json.put("friend", getFriend().getUsername());
		return json;
	}

	public void init() {
		if (getStatus() == null) {
			setStatus(APPLYING);
		}
	}
}