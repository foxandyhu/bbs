package com.jeecms.plug.live.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.common.util.DateUtils;

@Entity
@Table(name = "bbs_live_apply")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsLiveApply implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:40:44
	 */
	private static final long serialVersionUID = -1814475074870587433L;

	public static final Short STATUS_CHECKING = 0;
	public static final Short STATUS_CHECKED = 1;
	public static final Short STATUS_REBACK = 2;
	public static final Short STATUS_STOP = 3;

	@Id
	@Column(name="apply_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="intro")
	private String intro;
	
	@Column(name="brief")
	private String brief;
	
	@Column(name="experience")
	private String experience;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="address")
	private String address;
	
	@Column(name="apply_time")
	private Date applyTime;
	
	@Column(name="check_time")
	private Date checkTime;
	
	@Column(name="status")
	private Short status;

	@ManyToOne
	@JoinColumn(name="check_user_id",referencedColumnName="user_id")
	private BbsUser checkUser;
	
	@ManyToOne
	@JoinColumn(name="apply_user_id",referencedColumnName="user_id")
	private BbsUser applyUser;

	@ElementCollection
	@OrderBy("priority")
	@CollectionTable(name="bbs_live_apply_picture",joinColumns=@JoinColumn(name="apply_id"))
	private List<BbsLiveApplyPicture> pictures;

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getIntro() {
		return intro;
	}

	
	public void setIntro(String intro) {
		this.intro = intro;
	}

	
	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	
	public String getExperience() {
		return experience;
	}

	
	public void setExperience(String experience) {
		this.experience = experience;
	}

	
	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	
	public Date getCheckTime() {
		return checkTime;
	}

	
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Short getStatus() {
		return status;
	}

	
	public void setStatus(Short status) {
		this.status = status;
	}

	
	public BbsUser getCheckUser() {
		return checkUser;
	}

	
	public void setCheckUser(BbsUser checkUser) {
		this.checkUser = checkUser;
	}

	
	public BbsUser getApplyUser() {
		return applyUser;
	}

	
	public void setApplyUser(BbsUser applyUser) {
		this.applyUser = applyUser;
	}

	
	public List<BbsLiveApplyPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<BbsLiveApplyPicture> pictures) {
		this.pictures = pictures;
	}

	public void addToPictures(String path, String desc) {
		List<BbsLiveApplyPicture> list = getPictures();
		if (list == null) {
			list = new ArrayList<BbsLiveApplyPicture>();
			setPictures(list);
		}
		BbsLiveApplyPicture cp = new BbsLiveApplyPicture();
		cp.setImgPath(path);
		cp.setDescription(desc);
		list.add(cp);
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (getApplyUser() != null && StringUtils.isNotBlank(getApplyUser().getUsername())) {
			json.put("username", getApplyUser().getUsername());
		} else {
			json.put("username", "");
		}
		if (getApplyTime() != null) {
			json.put("applyTime", DateUtils.parseDateToDateStr(getApplyTime()));
		} else {
			json.put("applyTime", "");
		}
		if (StringUtils.isNotBlank(getIntro())) {
			json.put("intro", getIntro());
		} else {
			json.put("intro", "");
		}
		if (StringUtils.isNotBlank(getBrief())) {
			json.put("brief", getBrief());
		} else {
			json.put("brief", "");
		}
		if (StringUtils.isNotBlank(getExperience())) {
			json.put("experience", getExperience());
		} else {
			json.put("experience", "");
		}
		if (StringUtils.isNotBlank(getMobile())) {
			json.put("mobile", getMobile());
		} else {
			json.put("mobile", "");
		}
		if (StringUtils.isNotBlank(getAddress())) {
			json.put("address", getAddress());
		} else {
			json.put("address", "");
		}
		JSONArray jsonArray = new JSONArray();
		if (getPictures() != null && getPictures().size() > 0) {
			List<BbsLiveApplyPicture> list = getPictures();
			for (int i = 0; i < list.size(); i++) {
				JSONObject json2 = new JSONObject();

				if (StringUtils.isNotBlank(list.get(i).getImgPath())) {
					json2.put("picPaths", list.get(i).getImgPath());
				} else {
					json2.put("picPaths", "");
				}
				if (StringUtils.isNotBlank(list.get(i).getDescription())) {
					json2.put("picDescs", list.get(i).getDescription());
				} else {
					json2.put("picDescs", "");
				}
				jsonArray.put(i, json2);
			}
		}
		json.put("images", jsonArray);
		return json;
	}
}