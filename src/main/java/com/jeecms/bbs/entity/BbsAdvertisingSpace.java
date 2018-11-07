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
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.core.entity.CmsSite;

/**
 * 广告版位表
 * 
 * @author andy_hulibo@163.com 2018年11月2日上午10:05:08
 */
@Entity
@Table(name = "jb_advertising_space")
public class BbsAdvertisingSpace implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:59:28
	 */
	private static final long serialVersionUID = 1640659098905997831L;

	@Id
	@Column(name="adspace_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="ad_name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="preview_img")
	private String previewImg;
	
	@Column(name="is_enabled")
	private Boolean enabled;

	@ManyToOne
	@JoinColumn(name="site_id")
	private CmsSite site;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPreviewImg() {
		return previewImg;
	}

	public void setPreviewImg(String previewImg) {
		this.previewImg = previewImg;
	}

	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public CmsSite getSite() {
		return site;
	}

	
	public void setSite(CmsSite site) {
		this.site = site;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getDescription())) {
			json.put("description", getDescription());
		} else {
			json.put("description", "");
		}
		if (StringUtils.isNotBlank(getName())) {
			json.put("name", getName());
		} else {
			json.put("name", "");
		}
		if (getEnabled() != null) {
			json.put("enabled", getEnabled());
		} else {
			json.put("enabled", "");
		}
		if (StringUtils.isNotBlank(getPreviewImg())) {
			json.put("previewImg", getPreviewImg());
		} else {
			json.put("previewImg", "");
		}
		return json;
	}

	public void init() {
		if (getEnabled() == null) {
			setEnabled(false);
		}
	}
}