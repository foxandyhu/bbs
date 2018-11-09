package com.jeecms.core.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.bbs.entity.BbsUser;

/**
 * BBS角色
 * @author andy_hulibo@163.com
 * 2018年11月1日下午4:55:27
 */
@Entity
@Table(name = "jc_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class CmsRole implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:11:54
	 */
	private static final long serialVersionUID = 5598497956932212589L;

	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="role_name")
	private String name;
	
	@Column(name="priority")
	private Integer priority;
	
	@Column(name="role_level")
	private Integer level;
	
	@Column(name="is_super")
	private Boolean all;

	@ManyToOne
	@JoinColumn(name="site_id")
	private CmsSite site;

	@ElementCollection
	@CollectionTable(name="jb_role_permission",joinColumns=@JoinColumn(name="role_id"))
	@Column(name="uri")
	private Set<String> perms;
	
	@ManyToMany(cascade=CascadeType.REMOVE)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
	@JoinTable(name="jb_user_role",
	joinColumns=@JoinColumn(name="role_id",referencedColumnName="role_id"),
	inverseJoinColumns=@JoinColumn(name="user_id",referencedColumnName="user_id"))
	private Set<BbsUser> users;

	
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


	public Integer getPriority() {
		return priority;
	}

	
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getAll() {
		return all;
	}

	public void setAll(Boolean all) {
		this.all = all;
	}

	public CmsSite getSite() {
		return site;
	}


	public void setSite(CmsSite site) {
		this.site = site;
	}

	
	public Set<String> getPerms() {
		return perms;
	}

	
	public void setPerms(Set<String> perms) {
		this.perms = perms;
	}

	public Set<BbsUser> getUsers() {
		return users;
	}

	public void setUsers(Set<BbsUser> users) {
		this.users = users;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getName())) {
			json.put("name", getName());
		} else {
			json.put("name", "");
		}
		if (getPriority() != null) {
			json.put("priority", getPriority());
		} else {
			json.put("priority", "");
		}
		if (getLevel() != null) {
			json.put("level", getLevel());
		} else {
			json.put("level", "");
		}
		if (getAll() != null) {
			json.put("all", getAll());
		} else {
			json.put("all", "");
		}

		if (getPerms() != null) {
			Set<String> set = getPerms();
			String perms = "";
			for (String string : set) {
				perms += string + ",";
			}
			if (perms.length() > 0) {
				perms = perms.substring(0, perms.length() - 1);
			}
			json.put("perms", perms);
		} else {
			json.put("perms", "");
		}
		return json;
	}

	/**
	 * 数据默认初始化
	 */
	public void init() {
		if (getAll() == null) {
			setAll(false);
		}
	}

	public static Integer[] fetchIds(Collection<CmsRole> roles) {
		if (roles == null) {
			return null;
		}
		Integer[] ids = new Integer[roles.size()];
		int i = 0;
		for (CmsRole r : roles) {
			ids[i++] = r.getId();
		}
		return ids;
	}


	public void delFromUsers(BbsUser user) {
		if (user == null) {
			return;
		}
		Set<BbsUser> set = getUsers();
		if (set == null) {
			return;
		}
		set.remove(user);
	}
}