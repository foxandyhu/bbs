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


@Entity
@Table(name="bbs_vote_record")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsVoteRecord implements Serializable{
	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午3:53:11
	 */
	private static final long serialVersionUID = -2470786562517458639L;
	

	@Id
	@Column(name="record_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="vote_time")
	private Date voteTime;

	@ManyToOne
	@JoinColumn(name="user_id")
	private BbsUser user;
	
	@ManyToOne
	@JoinColumn(name="topic_id")
	private BbsVoteTopic topic;



	public Integer getId () {
		return id;
	}

	
	public void setId (Integer id) {
		this.id = id;
	}

	public Date getVoteTime () {
		return voteTime;
	}

	public void setVoteTime (Date voteTime) {
		this.voteTime = voteTime;
	}


	public BbsUser getUser () {
		return user;
	}

	public void setUser (BbsUser user) {
		this.user = user;
	}


	public BbsVoteTopic getTopic () {
		return topic;
	}

	public void setTopic (BbsVoteTopic topic) {
		this.topic = topic;
	}


}