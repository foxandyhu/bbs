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
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "bbs_vote_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsVoteItem implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:53:18
	 */
	private static final long serialVersionUID = 7430460173261923999L;

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", getId());
		json.put("name", getName());
		json.put("voteCount", getVoteCount());
		json.put("percent", getPercent());
		return json;
	}

	public int getPercent() {
		Integer totalCount = getTopic().getTotalCount();
		if (totalCount != null && totalCount != 0) {
			return (getVoteCount() * 100) / totalCount;
		} else {
			return 0;
		}
	}

	public void init() {
		if (getVoteCount() == null) {
			setVoteCount(0);
		}
	}

	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;
	
	@Column(name="vote_count")
	private Integer voteCount;

	@ManyToOne
	@JoinColumn(name="topic_id")
	private BbsVoteTopic topic;

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public BbsVoteTopic getTopic() {
		return topic;
	}

	
	public void setTopic(BbsVoteTopic topic) {
		this.topic = topic;
	}

}