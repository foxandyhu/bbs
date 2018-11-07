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

/**
 * 用户道具
 * 
 * @author andy_hulibo@163.com 2018年11月2日下午2:34:05
 */
@Entity
@Table(name = "bbs_member_magic")
public class BbsMemberMagic implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:56:34
	 */
	private static final long serialVersionUID = 7396837243874372622L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="num")
	private Integer num;

	@ManyToOne
	@JoinColumn(name="uid")
	private BbsUser user;
	
	@ManyToOne
	@JoinColumn(name="magicid")
	private BbsCommonMagic magic;

	
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

	
	public BbsCommonMagic getMagic() {
		return magic;
	}

	
	public void setMagic(BbsCommonMagic magic) {
		this.magic = magic;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", getId());
		json.put("num", getNum());
		json.put("user", getUser().getUsername());
		json.put("magicId", getMagic().getId());
		json.put("magicName", getMagic().getName());
		json.put("magicIdentifier", getMagic().getIdentifier());
		return json;
	}
}