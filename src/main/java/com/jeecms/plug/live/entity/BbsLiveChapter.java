package com.jeecms.plug.live.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SortComparator;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.common.hibernate4.HibernateTree;
import com.jeecms.common.hibernate4.PriorityComparator;
import com.jeecms.common.hibernate4.PriorityInterface;

/**
 * 活动live章节
 * @author andy_hulibo@163.com
 * 2018年11月1日下午4:45:54
 */
@Entity
@Table(name="bbs_live_chapter")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class BbsLiveChapter implements HibernateTree<Integer>, PriorityInterface, Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:40:30
	 */
	private static final long serialVersionUID = 1603113899103363463L;

	@Id
	@Column(name = "chapter_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;
	
	@Column(name="chapter_path")
	private String path;
	
	@Column(name="lft")
	private Integer lft;
	
	@Column(name="rgt")
	private Integer rgt;
	
	@Column(name="priority")
	private Integer priority;

	@ManyToOne
	@JoinColumn(name="parent_id")
	private BbsLiveChapter parent;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private BbsUser user;

	@OneToMany(cascade=CascadeType.REMOVE)
	@JoinColumn(name="parent_id")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@SortComparator(value=PriorityComparator.class)
	private Set<BbsLiveChapter> child;

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

	public BbsLiveChapter getParent() {
		return parent;
	}

	public void setParent(BbsLiveChapter parent) {
		this.parent = parent;
	}

	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

	public Set<BbsLiveChapter> getChild() {
		return child;
	}

	public void setChild(Set<BbsLiveChapter> child) {
		this.child = child;
	}

	/**
	 * 获得列表用于下拉选择。
	 * 
	 * @return
	 */
	public List<BbsLiveChapter> getListForSelect() {
		return getBeanListForSelect(null);
	}

	public List<BbsLiveChapter> getBeanListForSelect(BbsLiveChapter exclude) {
		List<BbsLiveChapter> list = new ArrayList<BbsLiveChapter>((getRgt() - getLft()) / 2);
		addChildToList(list, this, exclude);
		return list;
	}

	/**
	 * 获得列表用于下拉选择。条件：有内容的章节。
	 * 
	 * @param topList
	 *            顶级章节
	 * @return
	 */
	public static List<BbsLiveChapter> getListForSelect(List<BbsLiveChapter> topList) {
		return getListForSelect(topList, null);
	}

	public static List<BbsLiveChapter> getListForSelect(List<BbsLiveChapter> topList, BbsLiveChapter exclude) {
		List<BbsLiveChapter> list = new ArrayList<BbsLiveChapter>();
		for (BbsLiveChapter c : topList) {
			addChildToList(list, c, exclude);
		}
		return list;
	}

	/**
	 * 递归将子章节加入列表。
	 * 
	 * @param list
	 *            章节容器
	 * @param chapter
	 *            待添加的chapter章节，且递归添加子章节
	 */
	private static void addChildToList(List<BbsLiveChapter> list, BbsLiveChapter chapter, BbsLiveChapter exclude) {
		list.add(chapter);
		Set<BbsLiveChapter> child = chapter.getChild();
		for (BbsLiveChapter c : child) {
			addChildToList(list, c, exclude);
		}
	}

	/**
	 * 每个用户各自维护独立的树结构
	 * 
	 * @see HibernateTree#getTreeCondition()
	 */
	public String getTreeCondition() {
		return "bean.user.id=" + getUser().getId();
	}

	/**
	 * @see HibernateTree#getParentId()
	 */
	public Integer getParentId() {
		BbsLiveChapter parent = getParent();
		if (parent != null) {
			return parent.getId();
		} else {
			return null;
		}
	}

	/**
	 * 获得节点列表。从父节点到自身。
	 * 
	 * @return
	 */
	public List<BbsLiveChapter> getNodeList() {
		LinkedList<BbsLiveChapter> list = new LinkedList<BbsLiveChapter>();
		BbsLiveChapter node = this;
		while (node != null) {
			list.addFirst(node);
			node = node.getParent();
		}
		return list;
	}

	/**
	 * 获得节点列表ID。从父节点到自身。
	 * 
	 * @return
	 */
	public Integer[] getNodeIds() {
		List<BbsLiveChapter> channels = getNodeList();
		Integer[] ids = new Integer[channels.size()];
		int i = 0;
		for (BbsLiveChapter c : channels) {
			ids[i++] = c.getId();
		}
		return ids;
	}

	/**
	 * 获得深度
	 * 
	 * @return 第一层为0，第二层为1，以此类推。
	 */
	public int getDeep() {
		int deep = 0;
		BbsLiveChapter parent = getParent();
		while (parent != null) {
			deep++;
			parent = parent.getParent();
		}
		return deep;
	}

	public BbsLiveChapter getTopBbsLiveChapter() {
		BbsLiveChapter parent = getParent();
		while (parent != null) {
			if (parent.getParent() != null) {
				parent = parent.getParent();
			} else {
				break;
			}
		}
		return parent;
	}

	/**
	 * @see HibernateTree#getLftName()
	 */
	public String getLftName() {
		return DEF_LEFT_NAME;
	}

	/**
	 * @see HibernateTree#getParentName()
	 */
	public String getParentName() {
		return DEF_PARENT_NAME;
	}

	/**
	 * @see HibernateTree#getRgtName()
	 */
	public String getRgtName() {
		return DEF_RIGHT_NAME;
	}

	public static Integer[] fetchIds(Collection<BbsLiveChapter> channels) {
		if (channels == null) {
			return null;
		}
		Integer[] ids = new Integer[channels.size()];
		int i = 0;
		for (BbsLiveChapter c : channels) {
			ids[i++] = c.getId();
		}
		return ids;
	}

	public void init() {
		if (getPriority() == null) {
			setPriority(10);
		}
	}
}