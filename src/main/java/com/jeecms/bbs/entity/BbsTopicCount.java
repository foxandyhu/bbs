package com.jeecms.bbs.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 主题数据
 * @author: andy_hulibo@163.com
 * @date: 2018/11/13 11:46
 */
@Entity
@Table(name="bbs_topic_count")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsTopicCount implements Serializable {

	private static final long serialVersionUID = -6424118666399519003L;

	/**
	 * 点赞数
	 */
	@Column(name="ups")
	private Integer ups;
	
	/**
	 * 收藏数
	 */
	@Column(name="collections")
	private Integer collections;
	
	/**
	 * 打赏数
	 */
	@Column(name="rewards")
	private Integer rewards;
	
	/**
	 * 关注数
	 */
	@Column(name="attentions")
	private Integer attentions;

	/**
	 * 关联的帖子
	 */
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