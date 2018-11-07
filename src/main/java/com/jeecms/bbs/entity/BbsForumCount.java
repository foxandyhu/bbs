package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;

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

import com.jeecms.common.util.DateUtils;

/**
 * 板块统计
 * 
 * @author andy_hulibo@163.com 2018年11月2日上午10:57:04
 */
@Entity
@Table(name = "bbs_forum_count")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsForumCount implements Serializable {
	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:58:36
	 */
	private static final long serialVersionUID = -500371115764163644L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="topic_count")
	private Integer topicCount;
	
	@Column(name="post_count")
	private Integer postCount;
	
	@Column(name="visit_count")
	private Integer visitCount;
	
	@Column(name="count_date")
	private Date countDate;

	@ManyToOne
	@JoinColumn(name="forum_id")
	private BbsForum forum;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	
	public Integer getPostCount() {
		return postCount;
	}

	public void setPostCount(Integer postCount) {
		this.postCount = postCount;
	}

	
	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	
	public Date getCountDate() {
		return countDate;
	}

	
	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}

	
	public BbsForum getForum() {
		return forum;
	}

	
	public void setForum(BbsForum forum) {
		this.forum = forum;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getTopicCount() != null) {
			json.put("topicCount", getTopicCount());
		} else {
			json.put("topicCount", "");
		}
		if (getPostCount() != null) {
			json.put("postCount", getPostCount());
		} else {
			json.put("postCount", "");
		}
		if (getVisitCount() != null) {
			json.put("visitCount", getVisitCount());
		} else {
			json.put("visitCount", "");
		}
		if (getCountDate() != null) {
			json.put("countDate", DateUtils.parseDateToTimeStr(getCountDate()));
		} else {
			json.put("countDate", "");
		}
		return json;
	}
}