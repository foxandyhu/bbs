package com.jeecms.bbs.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 * 投票贴
 * 
 */
@Entity
@DiscriminatorValue("101")
public class BbsVoteTopic extends BbsTopic {
	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午4:01:22
	 */
	private static final long serialVersionUID = 8853939277175256074L;
	/**
	 * 总票数
	 */
	private Integer totalCount;			//总票数

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
		return TOPIC_VOTE;
	}
}
