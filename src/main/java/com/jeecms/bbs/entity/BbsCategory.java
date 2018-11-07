package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SortComparator;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.hibernate4.PriorityComparator;
import com.jeecms.core.entity.CmsSite;

/**
 * 论坛分区
 * @author andy_hulibo@163.com
 * 2018年11月2日上午10:28:44
 */
@Entity
@Table(name = "BBS_CATEGORY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsCategory implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:59:20
	 */
	private static final long serialVersionUID = 325291382275663076L;

	@Id
	@Column(name="CATEGORY_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="PATH")
	private String path;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="PRIORITY")
	private Integer priority;
	
	@Column(name="FORUM_COLS")
	private Integer forumCols;
	
	@Column(name="moderators")
	private String moderators;

	@ManyToOne
	@JoinColumn(name="site_id")
	private CmsSite site;

	@OneToMany(mappedBy="category")
	@SortComparator(value=PriorityComparator.class)
	private Set<BbsForum> forums;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPriority() {
		return priority;
	}


	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	
	public Integer getForumCols() {
		return forumCols;
	}

	public void setForumCols(Integer forumCols) {
		this.forumCols = forumCols;
	}

	public String getModerators() {
		return moderators;
	}

	public void setModerators(String moderators) {
		this.moderators = moderators;
	}

	
	public CmsSite getSite() {
		return site;
	}

	public void setSite(CmsSite site) {
		this.site = site;
	}

	public Set<BbsForum> getForums() {
		return forums;
	}

	
	public void setForums(Set<BbsForum> forums) {
		this.forums = forums;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getPath())) {
			json.put("path", getPath());
		} else {
			json.put("path", "");
		}
		if (StringUtils.isNotBlank(getTitle())) {
			json.put("title", getTitle());
		} else {
			json.put("title", "");
		}
		if (getPriority() != null) {
			json.put("priority", getPriority());
		} else {
			json.put("priority", "");
		}
		return json;
	}

	public void init() {
		if (getForumCols() == null) {
			setForumCols(3);
		}
		if (getPriority() == null) {
			setPriority(0);
		}
	}
}