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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.core.entity.CmsSite;

/**
 * CMS友情链接
 * 
 * @author andy_hulibo@163.com 2018年11月6日下午3:08:41
 */
@Entity
@Table(name = "jc_friendlink")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CmsFriendlink implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:52:30
	 */
	private static final long serialVersionUID = -6962634505371713332L;

	public JSONObject convertToJson(Integer https) throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getPriority() != null) {
			json.put("priority", getPriority());
		} else {
			json.put("priority", "");
		}
		if (StringUtils.isNotBlank(getName())) {
			json.put("name", getName());
		} else {
			json.put("name", "");
		}
		if (StringUtils.isNotBlank(getDomain())) {
			json.put("domain", getDomain());
		} else {
			json.put("domain", "");
		}
		if (StringUtils.isNotBlank(getLogo())) {
			json.put("logo", getLogo());
		} else {
			json.put("logo", "");
		}
		if (StringUtils.isNotBlank(getEmail())) {
			json.put("email", getEmail());
		} else {
			json.put("email", "");
		}
		if (StringUtils.isNotBlank(getDescription())) {
			json.put("description", getDescription());
		} else {
			json.put("description", "");
		}
		if (getViews() != null) {
			json.put("views", getViews());
		}
		if (getEnabled() != null) {
			json.put("enabled", getEnabled());
		} else {
			json.put("enabled", "");
		}
		if (getCategory() != null && StringUtils.isNotBlank(getCategory().getName())) {
			json.put("categoryName", getCategory().getName());
		} else {
			json.put("categoryName", "");
		}
		if (getCategory() != null && getCategory().getId() != null) {
			json.put("categoryId", getCategory().getId());
		} else {
			json.put("categoryId", "");
		}
		return json;
	}

	public void init() {
		if (getPriority() == null) {
			setPriority(10);
		}
		if (getViews() == null) {
			setViews(0);
		}
		if (getEnabled() == null) {
			setEnabled(true);
		}
		if (StringUtils.isBlank(getDomain())) {
			setDomain("http://");
		}
		blankToNull();
	}

	public void blankToNull() {
		if (StringUtils.isBlank(getLogo())) {
			setLogo(null);
		}
	}

	@Id
	@Column(name="friendlink_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="site_name")
	private String name;
	
	@Column(name="domain")
	private String domain;
	
	@Column(name="logo")
	private String logo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="description")
	private String description;
	
	@Column(name="views")
	private Integer views;
	
	@Column(name="priority")
	private Integer priority;
	
	@Column(name="is_enabled")
	private Boolean enabled;

	@ManyToOne
	@JoinColumn(name="friendlinkctg_id")
	private CmsFriendlinkCtg category;
	
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public CmsFriendlinkCtg getCategory() {
		return category;
	}

	public void setCategory(CmsFriendlinkCtg category) {
		this.category = category;
	}

	public CmsSite getSite() {
		return site;
	}

	public void setSite(CmsSite site) {
		this.site = site;
	}

}