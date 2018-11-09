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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "bbs_topic_msg")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsTopicMsg implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:54:58
	 */
	private static final long serialVersionUID = -3447794712671589127L;

	@Id
	@Column(name = "msg_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "is_read")
	private Boolean status;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private BbsUser user;

	@ManyToOne
	@JoinColumn(name = "topic_id")
	private BbsTopic topic;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private BbsPost post;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

	public BbsTopic getTopic() {
		return topic;
	}

	public void setTopic(BbsTopic topic) {
		this.topic = topic;
	}

	public BbsPost getPost() {
		return post;
	}

	public void setPost(BbsPost post) {
		this.post = post;
	}

}