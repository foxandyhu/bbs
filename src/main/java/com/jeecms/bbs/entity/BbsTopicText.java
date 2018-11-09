package com.jeecms.bbs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 论坛主题内容
 * @author andy_hulibo@163.com
 * 2018年10月30日下午2:41:06
 */
@Entity
@Table(name="bbs_topic_text")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsTopicText implements Serializable{
	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午3:54:31
	 */
	private static final long serialVersionUID = -6615323672306064639L;

	@Id
    @Column(name = "TOPIC_ID")
	private Integer id;

	@Column(name="TITLE")
	private String title;

	@OneToOne
	@JoinColumn(name="TOPIC_ID")
	private BbsTopic topic;

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public String getTitle () {
		return title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	public BbsTopic getTopic () {
		return topic;
	}

	public void setTopic (BbsTopic topic) {
		this.topic = topic;
	}
}