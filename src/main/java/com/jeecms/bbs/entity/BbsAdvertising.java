package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.util.DateUtils;
import com.jeecms.core.entity.CmsSite;

/**
 * 广告
 * @author andy_hulibo@163.com
 * 2018年11月2日上午10:19:33
 */
@Entity
@Table(name="jb_advertising")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class BbsAdvertising implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:59:35
	 */
	private static final long serialVersionUID = 1929740999571847567L;

	public static final Integer CHARGE_MODE_DAY = 0;
	public static final Integer CHARGE_MODE_CLICK = 1;
	public static final Integer CHARGE_MODE_DISPLAY = 2;
	public static String PROP_CODE = "code";
	public static String PROP_END_TIME = "endTime";
	public static String PROP_START_TIME = "startTime";

	@Id
	@Column(name="advertising_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="ad_name")
	private String name;
	
	@Column(name="category")
	private String category;
	
	@Column(name="ad_code")
	private String code;
	
	@Column(name="ad_weight")
	private Integer weight;
	
	@Column(name="display_count")
	private Long displayCount;
	
	@Column(name="click_count")
	private Long clickCount;
	
	@Column(name="start_time")
	private Date startTime;
	
	@Column(name="end_time")
	private Date endTime;
	
	@Column(name="is_enabled")
	private Boolean enabled;
	
	@Column(name="charge_mode")
	private Integer chargeMode;
	
	@Column(name="charge_amount")
	private Double chargeAmount;

	@ManyToOne
	@JoinColumn(name="adspace_id")
	private BbsAdvertisingSpace adspace;
	
	@ManyToOne
	@JoinColumn(name="site_id")
	private CmsSite site;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private BbsUser owner;

	@ElementCollection
	@CollectionTable(name="jb_advertising_attr",joinColumns=@JoinColumn(name="advertising_id"))
	@MapKeyColumn(name="attr_name")
	@Column(name="attr_value")	
	private Map<String, String> attr;

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

	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public Integer getWeight() {
		return weight;
	}

	
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	
	public Long getDisplayCount() {
		return displayCount;
	}

	public void setDisplayCount(Long displayCount) {
		this.displayCount = displayCount;
	}

	public Long getClickCount() {
		return clickCount;
	}

	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}

	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(Integer chargeMode) {
		this.chargeMode = chargeMode;
	}

	public Double getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(Double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public BbsAdvertisingSpace getAdspace() {
		return adspace;
	}


	public void setAdspace(BbsAdvertisingSpace adspace) {
		this.adspace = adspace;
	}

	
	public CmsSite getSite() {
		return site;
	}


	public void setSite(CmsSite site) {
		this.site = site;
	}

	public BbsUser getOwner() {
		return owner;
	}

	public void setOwner(BbsUser owner) {
		this.owner = owner;
	}

	public Map<String, String> getAttr() {
		return attr;
	}

	public void setAttr(Map<String, String> attr) {
		this.attr = attr;
	}

	public int getPercent() {
		if (getDisplayCount() <= 0) {
			return 0;
		} else {
			return (int) (getClickCount() * 100 / getDisplayCount());
		}
	}

	public Long getStartTimeMillis() {
		if (getStartTime() != null) {
			return getStartTime().getTime();
		} else {
			return null;
		}
	}

	public Long getEndTimeMillis() {
		if (getEndTime() != null) {
			return getEndTime().getTime();
		} else {
			return null;
		}
	}

	public void init() {
		if (getClickCount() == null) {
			setClickCount(0L);
		}
		if (getDisplayCount() == null) {
			setDisplayCount(0L);
		}
		if (getEnabled() == null) {
			setEnabled(true);
		}
		if (getWeight() == null) {
			setWeight(1);
		}
		if (getChargeAmount() == null) {
			setChargeAmount(0d);
		}
		if (getChargeMode() == null) {
			setChargeMode(CHARGE_MODE_DAY);
		}
		if (StringUtils.isBlank(getCategory())) {
			setCategory("image");
		}
		blankToNull();
	}

	public void blankToNull() {
		if (StringUtils.isBlank(getCode())) {
			setCode(null);
		}
	}

	public JSONObject convertToJson(Integer https) throws JSONException {
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
		if (getOwner() != null && StringUtils.isNotBlank(getOwner().getUsername())) {
			json.put("username", getOwner().getUsername());
		} else {
			json.put("username", "");
		}
		if (StringUtils.isNotBlank(getCategory())) {
			json.put("category", getCategory());
		} else {
			json.put("category", "");
		}
		if (getChargeMode() != null) {
			json.put("chargeMode", getChargeMode());
		} else {
			json.put("chargeMode", "");
		}
		if (StringUtils.isNotBlank(getCode())) {
			json.put("code", getCode());
		} else {
			json.put("code", "");
		}
		if (getWeight() != null) {
			json.put("weight", getWeight());
		} else {
			json.put("weight", "");
		}
		if (getDisplayCount() != null) {
			json.put("displayCount", getDisplayCount());
		} else {
			json.put("displayCount", "");
		}
		if (getClickCount() != null) {
			json.put("clickCount", getClickCount());
		} else {
			json.put("clickCount", "");
		}
		if (getStartTime() != null) {
			json.put("startTime", DateUtils.parseDateToDateStr(getStartTime()));
		} else {
			json.put("startTime", "");
		}
		if (getEndTime() != null) {
			json.put("endTime", DateUtils.parseDateToDateStr(getEndTime()));
		} else {
			json.put("endTime", "");
		}
		if (getAdspace() != null && StringUtils.isNotBlank(getAdspace().getName())) {
			json.put("adspaceName", getAdspace().getName());
		} else {
			json.put("adspaceName", "");
		}
		if (getAdspace() != null) {
			json.put("adspaceId", getAdspace().getId());
		} else {
			json.put("adspaceId", "");
		}
		if (getEnabled() != null) {
			json.put("enabled", getEnabled());
		} else {
			json.put("enabled", "");
		}
		if (getChargeAmount() != null) {
			json.put("chargeAmount", getChargeAmount());
		} else {
			json.put("chargeAmount", 0d);
		}
		json.put("percent", getPercent());
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("image_link"))) {
			json.put("attr_image_link", getAttr().get("image_link"));
		} else {
			json.put("attr_image_link", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("image_url"))) {
			json.put("attr_image_url", getAttr().get("image_url"));
		} else {
			json.put("attr_image_url", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("image_target"))) {
			json.put("attr_image_target", getAttr().get("image_target"));
		} else {
			json.put("attr_image_target", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("image_title"))) {
			json.put("attr_image_title", getAttr().get("image_title"));
		} else {
			json.put("attr_image_title", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("image_width"))) {
			json.put("attr_image_width", Integer.parseInt(getAttr().get("image_width")));
		} else {
			json.put("attr_image_width", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("image_height"))) {
			json.put("attr_image_height", Integer.parseInt(getAttr().get("image_height")));
		} else {
			json.put("attr_image_height", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("flash_url"))) {
			json.put("attr_flash_url", getAttr().get("flash_url"));
		} else {
			json.put("attr_flash_url", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("flash_width"))) {
			json.put("attr_flash_width", Integer.parseInt(getAttr().get("flash_width")));
		} else {
			json.put("attr_flash_width", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("flash_height"))) {
			json.put("attr_flash_height", Integer.parseInt(getAttr().get("flash_height")));
		} else {
			json.put("attr_flash_height", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("text_link"))) {
			json.put("attr_text_link", getAttr().get("text_link"));
		} else {
			json.put("attr_text_link", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("text_target"))) {
			json.put("attr_text_target", getAttr().get("text_target"));
		} else {
			json.put("attr_text_target", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("text_color"))) {
			json.put("attr_text_color", getAttr().get("text_color"));
		} else {
			json.put("attr_text_color", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("text_font"))) {
			json.put("attr_text_font", Integer.parseInt(getAttr().get("text_font")));
		} else {
			json.put("attr_text_font", "");
		}
		if (getAttr() != null && StringUtils.isNotBlank(getAttr().get("text_title"))) {
			json.put("attr_text_title", getAttr().get("text_title"));
		} else {
			json.put("attr_text_title", "");
		}
		return json;
	}
}