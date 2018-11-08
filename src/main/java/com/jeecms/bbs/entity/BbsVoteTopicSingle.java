package com.jeecms.bbs.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 * 投票贴
 * 
 */
@Entity
@DiscriminatorValue("2")
public class BbsVoteTopicSingle extends BbsVoteTopic {

	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午4:00:55
	 */
	private static final long serialVersionUID = -7463888843103514447L;

	public void init() {
		super.init();
		if (getTotalCount() == null) {
			setTotalCount(0);
		}
	}

	public short getCategory() {
		return TOPIC_VOTE_SINGLE;
	}
}
