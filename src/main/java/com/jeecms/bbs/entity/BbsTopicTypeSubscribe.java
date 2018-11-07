package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Collection;

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

/**
 * 
 * @author andy_hulibo@163.com 2018年11月2日下午5:06:06
 */
@Entity
@Table(name = "bbs_topic_type_subscribe")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsTopicTypeSubscribe implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:54:15
	 */
	private static final long serialVersionUID = 1233966866892701517L;

	public static final Integer SUBSCRIBE_OK = 1;

	public static final Integer SUBSCRIBE_CANCEL = 0;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private BbsUser user;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private BbsTopicType type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

	public BbsTopicType getType() {
		return type;
	}

	public void setType(BbsTopicType type) {
		this.type = type;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getType() != null && StringUtils.isNotBlank(getType().getTypeLog())) {
			json.put("typeLog", getType().getTypeLog());
		} else {
			json.put("typeLog", "");
		}
		if (getType() != null && StringUtils.isNotBlank(getType().getUrl())) {
			json.put("url", getType().getUrl());
		} else {
			json.put("url", "");
		}
		if (getType() != null && StringUtils.isNotBlank(getType().getName())) {
			json.put("name", getType().getName());
		} else {
			json.put("name", "");
		}
		return json;
	}

	public static Integer[] fetchIds(Collection<BbsTopicTypeSubscribe> subs) {
		if (subs == null) {
			return null;
		}
		Integer[] ids = new Integer[subs.size()];
		int i = 0;
		for (BbsTopicTypeSubscribe u : subs) {
			ids[i++] = u.getId();
		}
		return ids;
	}

	public static String[] fetchTypeIds(Collection<BbsTopicTypeSubscribe> subs) {
		if (subs == null) {
			return null;
		}
		String[] ids = new String[subs.size()];
		int i = 0;
		for (BbsTopicTypeSubscribe u : subs) {
			ids[i++] = u.getType().getId().toString();
		}
		return ids;
	}
}