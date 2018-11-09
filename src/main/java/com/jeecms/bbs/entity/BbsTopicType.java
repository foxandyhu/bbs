package com.jeecms.bbs.entity;

import static com.jeecms.common.web.Constants.INDEX;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SortComparator;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.hibernate4.HibernateTree;
import com.jeecms.common.hibernate4.PriorityComparator;
import com.jeecms.common.hibernate4.PriorityInterface;
import com.jeecms.core.entity.CmsSite;

/**
 * 主题分类
 * 
 * @author andy_hulibo@163.com 2018年10月30日下午3:10:05
 */
@Entity
@Table(name = "bbs_topic_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsTopicType implements HibernateTree<Integer>, PriorityInterface, Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:54:22
	 */
	private static final long serialVersionUID = 1992265054882607776L;

	public static final int ORDER_PRIORITY_DESC = 1;

	@Id
	@Column(name="type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;
	
	@Column(name="type_path")
	private String path;
	
	@Column(name="lft")
	private Integer lft;
	
	@Column(name="rgt")
	private Integer rgt;
	
	@Column(name="priority")
	private Integer priority;
	
	@Column(name="is_display")
	private boolean display;
	
	@Column(name="description")
	private String description;
	
	@Column(name="type_log")
	private String typeLog;
	
	@Column(name="topic_count")
	private Integer topicCount;
	
	@Column(name="topic_essence_count")
	private Integer topicEssenceCount;
	
	@Column(name="subscribe_count")
	private Integer subscribeCount;

	@ManyToOne
	@JoinColumn(name="parent_id")
	private BbsTopicType parent;
	@ManyToOne
	@JoinColumn(name="site_id")
	private CmsSite site;
	
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="parent")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
	@SortComparator(value=PriorityComparator.class)
	private Set<BbsTopicType> child;
	
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="type")
	private Set<BbsTopicTypeSubscribe> subscribes;

	
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

	
	public String getPath() {
		return path;
	}

	
	public void setPath(String path) {
		this.path = path;
	}

	
	public Integer getLft() {
		return lft;
	}

	
	public void setLft(Integer lft) {
		this.lft = lft;
	}

	
	public Integer getRgt() {
		return rgt;
	}

	
	public void setRgt(Integer rgt) {
		this.rgt = rgt;
	}

	
	public Integer getPriority() {
		return priority;
	}

	
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public boolean isDisplay() {
		return display;
	}

	
	public void setDisplay(boolean display) {
		this.display = display;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getTypeLog() {
		return typeLog;
	}

	public void setTypeLog(String typeLog) {
		this.typeLog = typeLog;
	}

	
	public Integer getTopicCount() {
		return topicCount;
	}

	
	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	
	public Integer getTopicEssenceCount() {
		return topicEssenceCount;
	}

	
	public void setTopicEssenceCount(Integer topicEssenceCount) {
		this.topicEssenceCount = topicEssenceCount;
	}

	
	public Integer getSubscribeCount() {
		return subscribeCount;
	}

	
	public void setSubscribeCount(Integer subscribeCount) {
		this.subscribeCount = subscribeCount;
	}

	public BbsTopicType getParent() {
		return parent;
	}

	public void setParent(BbsTopicType parent) {
		this.parent = parent;
	}

	public com.jeecms.core.entity.CmsSite getSite() {
		return site;
	}

	public void setSite(com.jeecms.core.entity.CmsSite site) {
		this.site = site;
	}

	public Set<BbsTopicType> getChild() {
		return child;
	}

	public void setChild(Set<BbsTopicType> child) {
		this.child = child;
	}

	public Set<BbsTopicTypeSubscribe> getSubscribes() {
		return subscribes;
	}

	public void setSubscribes(Set<BbsTopicTypeSubscribe> subscribes) {
		this.subscribes = subscribes;
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
		if (StringUtils.isNotBlank(getPath())) {
			json.put("path", getPath());
		} else {
			json.put("path", "");
		}
		if (getLft() != null) {
			json.put("lft", getLft());
		} else {
			json.put("lft", "");
		}
		if (getRgt() != null) {
			json.put("rgt", getRgt());
		} else {
			json.put("rgt", "");
		}
		if (getPriority() != null) {
			json.put("priority", getPriority());
		} else {
			json.put("priority", "");
		}
		json.put("display", getDisplay());
		if (StringUtils.isNotBlank(getDescription())) {
			json.put("description", getDescription());
		} else {
			json.put("description", "");
		}

		if (StringUtils.isNotBlank(getTypeLog())) {
			json.put("typeLog", getTypeLog());
		} else {
			json.put("typeLog", "");
		}

		if (getTopicCount() != null) {
			json.put("topicCount", getTopicCount());
		} else {
			json.put("topicCount", "");
		}
		if (getTopicEssenceCount() != null) {
			json.put("topicEssenceCount", getTopicEssenceCount());
		} else {
			json.put("topicEssenceCount", "");
		}
		if (getSubscribeCount() != null) {
			json.put("subscribeCount", getSubscribeCount());
		} else {
			json.put("subscribeCount", "");
		}
		if (getChild() != null && getChild().size() > 0) {
			json.put("haveChild", true);
		} else {
			json.put("haveChild", false);
		}
		if (getParent() != null && getParent().getId() != null) {
			json.put("parentId", getParent().getId());
		} else {
			json.put("parentId", "");
		}
		if (getParent() != null && StringUtils.isNotBlank(getParent().getName())) {
			json.put("parentName", getParent().getName());
		} else {
			json.put("parentName", "");
		}
		return json;
	}

	/**
	 * 分类详细访问URL
	 * 
	 * @return
	 */
	public String getUrl() {
		return getSite().getUrlBuffer(true, null, false).append("/topicType").append("/").append(getPath()).append("/")
				.append(INDEX).append(getSite().getDynamicSuffix()).toString();
	}

	public String getRedirectUrl() {
		StringBuffer buff = new StringBuffer();
		buff.append("/topicType").append("/").append(getPath()).append("/").append(INDEX)
				.append(getSite().getDynamicSuffix());
		return buff.toString();
	}

	/**
	 * 分类精华主题访问url
	 * 
	 * @return
	 */
	public String getTopUrl() {
		return getSite().getUrlBuffer(true, null, false).append("/topicType").append("/").append(getPath()).append("/")
				.append(com.jeecms.common.web.Constants.TOP).append(getSite().getDynamicSuffix()).toString();
	}

	/**
	 * 获取子类目访问URL
	 * 
	 * @return
	 */
	public String getTypeUrl() {
		return getSite().getUrlBuffer(true, null, false).append("/topicType").append("/").append(getPath())
				.append(getSite().getDynamicSuffix()).toString();
	}

	public void init() {
		if (getTopicCount() == null) {
			setTopicCount(0);
		}
		if (getTopicEssenceCount() == null) {
			setTopicEssenceCount(0);
		}
		if (getSubscribeCount() == null) {
			setSubscribeCount(0);
		}
	}

	/**
	 * 获得深度
	 * 
	 * @return 第一层为0，第二层为1，以此类推。
	 */
	public int getDeep() {
		int deep = 0;
		BbsTopicType parent = getParent();
		while (parent != null) {
			deep++;
			parent = parent.getParent();
		}
		return deep;
	}

	public static List<BbsTopicType> getListForSelect(List<BbsTopicType> topList, BbsTopicType exclude) {
		List<BbsTopicType> list = new ArrayList<BbsTopicType>();
		for (BbsTopicType c : topList) {
			addChildToList(list, c, exclude);
		}
		return list;
	}

	/**
	 * 递归将子分类加入列表。
	 * 
	 * @param list
	 *            分类容器
	 * @param type
	 *            待添加的分类，且递归添加子栏目
	 */
	private static void addChildToList(List<BbsTopicType> list, BbsTopicType type, BbsTopicType exclude) {
		list.add(type);
		Set<BbsTopicType> child = type.getChild();
		for (BbsTopicType c : child) {
			addChildToList(list, c, exclude);
		}
	}

	public String getLftName() {
		return DEF_LEFT_NAME;
	}

	public String getRgtName() {
		return DEF_RIGHT_NAME;
	}

	public String getParentName() {
		return DEF_PARENT_NAME;
	}

	public Integer getParentId() {
		BbsTopicType parent = getParent();
		if (parent != null) {
			return parent.getId();
		} else {
			return null;
		}
	}

	public boolean getDisplay() {
		return display;
	}

	public String getTreeCondition() {
		return "";
	}
}