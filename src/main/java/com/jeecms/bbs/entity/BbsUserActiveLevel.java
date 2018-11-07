package com.jeecms.bbs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用户活跃等级
 * 
 * @author andy_hulibo@163.com 2018年11月1日下午1:35:20
 */
@Entity
@Table(name="bbs_user_active_level")
public class BbsUserActiveLevel implements Serializable {
	
	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:53:51
	 */
	private static final long serialVersionUID = -631415195036112752L;

	@Id
	@Column(name="level_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="level_name")
	private String levelName;
	
	@Column(name="required_hour")
	private Long requiredHour;
	
	@Column(name="level_img")
	private String levelImg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Long getRequiredHour() {
		return requiredHour;
	}

	public void setRequiredHour(Long requiredHour) {
		this.requiredHour = requiredHour;
	}

	public String getLevelImg() {
		return levelImg;
	}

	public void setLevelImg(String levelImg) {
		this.levelImg = levelImg;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getLevelName())) {
			json.put("levelName", getLevelName());
		} else {
			json.put("levelName", "");
		}
		if (getRequiredHour() != null) {
			json.put("requiredHour", getRequiredHour());
		} else {
			json.put("requiredHour", "");
		}
		if (StringUtils.isNotBlank(getLevelImg())) {
			json.put("levelImg", getLevelImg());
		} else {
			json.put("levelImg", "");
		}
		return json;
	}
}