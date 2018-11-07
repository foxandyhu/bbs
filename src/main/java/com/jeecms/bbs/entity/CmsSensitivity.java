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
 * CMS敏感词
 * 
 * @author andy_hulibo@163.com 2018年11月6日下午3:17:19
 */
@Entity
@Table(name = "jc_sensitivity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CmsSensitivity implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:51:13
	 */
	private static final long serialVersionUID = -3079890783026821713L;

	public static final Integer TYPE_UPDATE = 1;
	public static final Integer TYPE_REPLACE = 2;
	public static final Integer TYPE_CLEARSAVE = 3;

	public enum CmsSensitivityImportType {
		/**
		 * 更新敏感词，不替换已经存在的敏感词
		 */
		update,
		/**
		 * 替换全部
		 */
		replace,
		/**
		 * 清空当前敏感词并导入新敏感词
		 */
		clearSave
	};

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getSearch())) {
			json.put("search", getSearch());
		} else {
			json.put("search", getSearch());
		}
		if (StringUtils.isNotBlank(getReplacement())) {
			json.put("replacement", getReplacement());
		} else {
			json.put("replacement", "");
		}

		return json;
	}

	@Id
	@Column(name="sensitivity_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="search")
	private String search;
	
	@Column(name="replacement")
	private String replacement;

	@ManyToOne
	@JoinColumn(name="site_id")
	private CmsSite site;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getReplacement() {
		return replacement;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

	public CmsSite getSite() {
		return site;
	}

	public void setSite(CmsSite site) {
		this.site = site;
	}

}