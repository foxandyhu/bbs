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

@Entity
@Table(name="bbs_topic_count")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class BbsTopicCount implements Serializable {

	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午3:55:05
	 */
	private static final long serialVersionUID = -6424118666399519003L;


	@Column(name="ups")
	private Integer ups;
	
	@Column(name="collections")
	private Integer collections;
	
	@Column(name="rewards")
	private Integer rewards;
	
	@Column(name="attentions")
	private Integer attentions;

	@Id
	@OneToOne
	@JoinColumn(name="topic_id")
	private BbsTopic topic;

	public Integer getUps () {
		return ups;
	}

	public void setUps (Integer ups) {
		this.ups = ups;
	}

	public Integer getCollections () {
		return collections;
	}

	public void setCollections (Integer collections) {
		this.collections = collections;
	}

	public Integer getRewards () {
		return rewards;
	}

	public void setRewards (Integer rewards) {
		this.rewards = rewards;
	}

	public Integer getAttentions () {
		return attentions;
	}

	public void setAttentions (Integer attentions) {
		this.attentions = attentions;
	}

	public BbsTopic getTopic () {
		return topic;
	}

	public void setTopic (BbsTopic topic) {
		this.topic = topic;
	}

	public void init() {
		if(getCollections()==null){
			setCollections(0);
		}
		if(getRewards()==null){
			setRewards(0);
		}
		if(getUps()==null){
			setUps(0);
		}
		if(getAttentions()==null){
			setAttentions(0);
		}	
	}
}