package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jeecms.core.entity.CmsSite;

@Entity
@Table(name="bbs_post_type")
public class BbsPostType implements Serializable {
	
	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:56:12
	 */
	private static final long serialVersionUID = -8654770322944926945L;


	@Id
	@Column(name="type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="type_name")
	private String typeName;
	
	@Column(name="priority")
	private Integer priority;

	@ManyToOne
	@JoinColumn(name="site_id")
	private CmsSite site;
	
	@ManyToOne
	@JoinColumn(name="FORUM_ID")	
	private BbsForum forum;
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private BbsPostType parent;

	@OneToMany(cascade=CascadeType.REFRESH,orphanRemoval=true)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name="parent_id")
	private Set<BbsPostType> childs;
	
	@ManyToMany(mappedBy="postTypes")
	private Set<BbsUserGroup> groups;



	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public String getTypeName () {
		return typeName;
	}

	public void setTypeName (String typeName) {
		this.typeName = typeName;
	}

	public Integer getPriority () {
		return priority;
	}

	public void setPriority (Integer priority) {
		this.priority = priority;
	}

	public CmsSite getSite () {
		return site;
	}

	public void setSite (CmsSite site) {
		this.site = site;
	}

	public BbsForum getForum () {
		return forum;
	}

	public void setForum (BbsForum forum) {
		this.forum = forum;
	}

	public BbsPostType getParent () {
		return parent;
	}

	
	public void setParent (BbsPostType parent) {
		this.parent = parent;
	}


	public Set<BbsPostType> getChilds () {
		return childs;
	}

	
	public void setChilds (Set<BbsPostType> childs) {
		this.childs = childs;
	}


	public Set<BbsUserGroup> getGroups () {
		return groups;
	}

	public void setGroups (Set<BbsUserGroup> groups) {
		this.groups = groups;
	}
}