package com.jeecms.bbs.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

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

	@Column(name="TITLE")
	private String title;

	@Id
	@OneToOne
	@JoinColumn(name="TOPIC_ID")
	private BbsTopic topic;

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