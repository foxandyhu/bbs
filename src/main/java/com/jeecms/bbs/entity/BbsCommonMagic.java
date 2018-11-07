package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 道具数据
 * 
 * @author andy_hulibo@163.com 2018年10月31日下午2:57:21
 */
@Entity
@Table(name = "bbs_common_magic")
public class BbsCommonMagic implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:59:13
	 */
	private static final long serialVersionUID = -4917815718543717942L;

	@Id
	@Column(name = "magicid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "available")
	private Boolean available;

	@Column(name = "name")
	private String name;

	@Column(name = "identifier")
	private String identifier;

	@Column(name = "description")
	private String description;

	@Column(name = "displayorder")
	private Byte displayorder;

	@Column(name = "credit")
	private Byte credit;

	@Column(name = "price")
	private Double price;

	@Column(name = "num")
	private Integer num;

	@Column(name = "salevolume")
	private Integer salevolume;

	@Column(name = "supplytype")
	private Integer supplytype;

	@Column(name = "supplynum")
	private Integer supplynum;

	@Column(name = "useperoid")
	private Integer useperoid;

	@Column(name = "usenum")
	private Integer usenum;

	@Column(name = "weight")
	private Integer weight;

	@Column(name = "useevent")
	private Boolean useevent;

	@Column(name = "magic_logo")
	private String magicLogo;

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="bbs_member_magic",joinColumns=@JoinColumn(name="magicid",referencedColumnName="magicid"),inverseJoinColumns=@JoinColumn(name="uid",referencedColumnName="user_id"))
	private Set<BbsUser> users;

	@ManyToMany(mappedBy = "magics")
	private Set<BbsUserGroup> useGroups;

	@ManyToMany(mappedBy = "beUsedMagics")
	private Set<BbsUserGroup> toUseGroups;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getDisplayorder() {
		return displayorder;
	}

	public void setDisplayorder(Byte displayorder) {
		this.displayorder = displayorder;
	}

	public Byte getCredit() {
		return credit;
	}

	public void setCredit(Byte credit) {
		this.credit = credit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getSalevolume() {
		return salevolume;
	}

	public void setSalevolume(Integer salevolume) {
		this.salevolume = salevolume;
	}

	public Integer getSupplytype() {
		return supplytype;
	}

	public void setSupplytype(Integer supplytype) {
		this.supplytype = supplytype;
	}

	public Integer getSupplynum() {
		return supplynum;
	}

	public void setSupplynum(Integer supplynum) {
		this.supplynum = supplynum;
	}

	public Integer getUseperoid() {
		return useperoid;
	}

	public void setUseperoid(Integer useperoid) {
		this.useperoid = useperoid;
	}

	public Integer getUsenum() {
		return usenum;
	}

	public void setUsenum(Integer usenum) {
		this.usenum = usenum;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Boolean getUseevent() {
		return useevent;
	}

	public void setUseevent(Boolean useevent) {
		this.useevent = useevent;
	}

	public String getMagicLogo() {
		return magicLogo;
	}

	public void setMagicLogo(String magicLogo) {
		this.magicLogo = magicLogo;
	}

	public Set<com.jeecms.bbs.entity.BbsUser> getUsers() {
		return users;
	}

	public void setUsers(Set<com.jeecms.bbs.entity.BbsUser> users) {
		this.users = users;
	}

	public Set<com.jeecms.bbs.entity.BbsUserGroup> getUseGroups() {
		return useGroups;
	}

	public void setUseGroups(Set<BbsUserGroup> useGroups) {
		this.useGroups = useGroups;
	}

	public Set<BbsUserGroup> getToUseGroups() {
		return toUseGroups;
	}

	public void setToUseGroups(Set<BbsUserGroup> toUseGroups) {
		this.toUseGroups = toUseGroups;
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
		if (StringUtils.isNotBlank(getIdentifier())) {
			json.put("identifier", getIdentifier());
		} else {
			json.put("identifier", "");
		}
		if (getDescription() != null) {
			json.put("description", getDescription());
		} else {
			json.put("description", "");
		}
		if (getDisplayorder() != null) {
			json.put("displayorder", getDisplayorder());
		} else {
			json.put("displayorder", "");
		}
		if (getCredit() != null) {
			json.put("credit", getCredit());
		} else {
			json.put("credit", "");
		}
		if (getPrice() != null) {
			json.put("price", getPrice());
		} else {
			json.put("price", "");
		}
		if (getNum() != null) {
			json.put("num", getNum());
		} else {
			json.put("num", "");
		}
		if (StringUtils.isNotBlank(getMagicLogo())) {
			json.put("magicLogo", getMagicLogo());
		} else {
			json.put("magicLogo", "");
		}
		if (getAvailable() != null) {
			json.put("available", getAvailable());
		} else {
			json.put("available", false);
		}
		String groupIds = "";
		if (getGroupIds() != null && getGroupIds().length > 0) {
			for (int i = 0; i < getGroupIds().length; i++) {
				groupIds += getGroupIds()[i] + ",";
			}
			groupIds = groupIds.substring(0, groupIds.length() - 1);
		}
		json.put("groupIds", groupIds);
		String beUsedGroupIds = "";
		if (getToUseGroupIds() != null && getToUseGroupIds().length > 0) {
			for (int i = 0; i < getToUseGroupIds().length; i++) {
				beUsedGroupIds += getToUseGroupIds()[i] + ",";
			}
			beUsedGroupIds = beUsedGroupIds.substring(0, beUsedGroupIds.length() - 1);
			json.put("canBeUsed", true);
		} else {
			json.put("canBeUsed", false);
		}
		json.put("beUsedGroupIds", beUsedGroupIds);
		return json;
	}

	public void addToGroups(BbsUserGroup group) {
		Set<BbsUserGroup> groups = getUseGroups();
		if (groups == null) {
			groups = new HashSet<BbsUserGroup>();
			setUseGroups(groups);
		}
		groups.add(group);
	}

	public void addToToUseGroups(BbsUserGroup group) {
		Set<BbsUserGroup> groups = getToUseGroups();
		if (groups == null) {
			groups = new HashSet<BbsUserGroup>();
			setToUseGroups(groups);
		}
		groups.add(group);
	}

	public Integer[] getGroupIds() {
		Set<BbsUserGroup> groups = getUseGroups();
		return fetchIds(groups);
	}

	public Integer[] getToUseGroupIds() {
		Set<BbsUserGroup> groups = getToUseGroups();
		return fetchIds(groups);
	}

	public static Integer[] fetchIds(Collection<BbsUserGroup> groups) {
		if (groups == null) {
			return null;
		}
		Integer[] ids = new Integer[groups.size()];
		int i = 0;
		for (BbsUserGroup g : groups) {
			ids[i++] = g.getId();
		}
		return ids;
	}
}