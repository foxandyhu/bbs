package com.jeecms.bbs.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 帖子数据
 * @author andy_hulibo@163.com
 * 2018年10月30日下午5:15:03
 */
@Entity
@Table(name="bbs_post_count")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsPostCount implements Serializable{
	
	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午3:56:27
	 */
	private static final long serialVersionUID = -1146829397435164234L;

	@Column(name="replys")
	private Integer replys;

	@Column(name="ups")
	private Integer ups;

	@Id
	@OneToOne
	@JoinColumn(name="POST_ID")
	private BbsPost post;

	public Integer getReplys () {
		return replys;
	}

	public void setReplys (Integer replys) {
		this.replys = replys;
	}

	public Integer getUps () {
		return ups;
	}

	public void setUps (Integer ups) {
		this.ups = ups;
	}

	public BbsPost getPost () {
		return post;
	}

	public void setPost (BbsPost post) {
		this.post = post;
	}
	
	public void init() {
		if(getUps()==null){
			setUps(0);
		}
		if(getReplys()==null){
			setReplys(0);
		}
	}
}