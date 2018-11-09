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
 * CMS友情链接类别
 * @author andy_hulibo@163.com 2018年11月6日下午3:13:13
 */
@Entity
@Table(name = "jc_friendlink_ctg")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class CmsFriendlinkCtg implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:51:20
	 */
	private static final long serialVersionUID = -6264911644339673051L;

	public JSONObject convertToJson() throws JSONException {
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
		return json;
	}

	@Id
	@Column(name="friendlinkctg_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="friendlinkctg_name")
	private String name;
	
	@Column(name="priority")
	private Integer priority;

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

	
	public Integer getPriority() {
		return priority;
	}

	
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	
	public CmsSite getSite() {
		return site;
	}

	
	public void setSite(CmsSite site) {
		this.site = site;
	}

}