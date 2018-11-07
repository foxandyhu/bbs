package com.jeecms.bbs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 板块扩展表
 * 
 * @author andy_hulibo@163.com 2018年11月2日上午10:49:36
 */
@Entity
@Table(name="bbs_forum_ext")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class BbsForumExt implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:51:06
	 */
	private static final long serialVersionUID = 3004482946704737017L;

	public void init() {
		blankToNull();
	}

	private Integer id;

	@Column(name="tpl_forum")
	private String tplForum;
	
	@Column(name="tpl_topic")
	private String tplTopic;
	
	@Column(name="tpl_mobile_forum")
	private String tplMobileForum;
	
	@Column(name="tpl_mobile_topic")
	private String tplMobileTopic;

	@Id
	@OneToOne
	@Column(name="forum_id")
	@JoinColumn(name = "FORUM_ID")
	private BbsForum forum;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTplForum() {
		return tplForum;
	}

	public void setTplForum(String tplForum) {
		this.tplForum = tplForum;
	}

	public String getTplTopic() {
		return tplTopic;
	}

	public void setTplTopic(String tplTopic) {
		this.tplTopic = tplTopic;
	}

	public String getTplMobileForum() {
		return tplMobileForum;
	}

	public void setTplMobileForum(String tplMobileForum) {
		this.tplMobileForum = tplMobileForum;
	}

	public String getTplMobileTopic() {
		return tplMobileTopic;
	}

	public void setTplMobileTopic(String tplMobileTopic) {
		this.tplMobileTopic = tplMobileTopic;
	}

	public BbsForum getForum() {
		return forum;
	}

	public void setForum(BbsForum forum) {
		this.forum = forum;
	}

	public void blankToNull() {
		if (StringUtils.isBlank(getTplForum())) {
			setTplForum(null);
		}
		if (StringUtils.isBlank(getTplTopic())) {
			setTplTopic(null);
		}
		if (StringUtils.isBlank(getTplMobileForum())) {
			setTplMobileForum(null);
		}
		if (StringUtils.isBlank(getTplMobileTopic())) {
			setTplMobileTopic(null);
		}
	}

}