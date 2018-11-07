package com.jeecms.bbs.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 * 投票贴
 * 
 */
@Entity
@DiscriminatorValue("102")
public class BbsVoteTopicSingle extends BbsVoteTopic {
	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午4:00:55
	 */
	private static final long serialVersionUID = -7463888843103514447L;
	/**
	 * 总票数
	 */
	private Integer totalCount;

	public void init() {
		super.init();
		if (totalCount == null) {
			totalCount = 0;
		}
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public short getCategory() {
		return TOPIC_VOTE_SINGLE;
	}
}
