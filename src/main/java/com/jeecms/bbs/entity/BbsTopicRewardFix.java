package com.jeecms.bbs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 打赏设置固定值
 * @author andy_hulibo@163.com
 * 2018年10月30日下午4:38:31
 */
@Embeddable
public class BbsTopicRewardFix implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:54:38
	 */
	private static final long serialVersionUID = 8970442175206276070L;

	@Column(name="priority")
	private Integer priority;
	
	@Column(name="reward_fix")
	private Double fixVal;
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Double getFixVal() {
		return fixVal;
	}

	public void setFixVal(Double fixVal) {
		this.fixVal = fixVal;
	}
}