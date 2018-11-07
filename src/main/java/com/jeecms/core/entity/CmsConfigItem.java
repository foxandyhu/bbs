package com.jeecms.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * CMS配置模型项表
 * 
 * @author andy_hulibo@163.com 2018年11月1日下午4:46:32
 */
@Entity
@Table(name = "jc_config_item")
public class CmsConfigItem implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:12:01
	 */
	private static final long serialVersionUID = -5057208963814469159L;

	// 用户模型
	public static final int CATEGORY_REGISTER = 10;

	@Id
	@Column(name="modelitem_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="field")
	private String field;
	
	@Column(name="item_label")
	private String label;
	
	@Column(name="priority")
	private Integer priority;
	
	@Column(name="def_value")
	private String defValue;
	
	@Column(name="opt_value")
	private String optValue;
	
	@Column(name="text_size")
	private String size;
	
	@Column(name="area_rows")
	private String rows;
	
	@Column(name="area_cols")
	private String cols;
	
	@Column(name="help")
	private String help;
	
	@Column(name="help_position")
	private String helpPosition;
	
	@Column(name="data_type")
	private Integer dataType;
	
	@Column(name="is_required")
	private boolean required;
	
	@Column(name="category")
	private Integer category;
	
	@ManyToOne
	@JoinColumn(name="config_id")
	private CmsConfig config;

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getField() {
		return field;
	}

	
	public void setField(String field) {
		this.field = field;
	}

	
	public String getLabel() {
		return label;
	}

	
	public void setLabel(String label) {
		this.label = label;
	}

	
	public Integer getPriority() {
		return priority;
	}

	
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	
	public String getDefValue() {
		return defValue;
	}

	
	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	
	public String getOptValue() {
		return optValue;
	}

	
	public void setOptValue(String optValue) {
		this.optValue = optValue;
	}

	
	public String getSize() {
		return size;
	}

	
	public void setSize(String size) {
		this.size = size;
	}

	
	public String getRows() {
		return rows;
	}

	
	public void setRows(String rows) {
		this.rows = rows;
	}

	
	public String getCols() {
		return cols;
	}

	
	public void setCols(String cols) {
		this.cols = cols;
	}

	
	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getHelpPosition() {
		return helpPosition;
	}

	public void setHelpPosition(String helpPosition) {
		this.helpPosition = helpPosition;
	}

	public Integer getDataType() {
		return dataType;
	}


	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	
	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	
	public CmsConfig getConfig() {
		return config;
	}

	
	public void setConfig(CmsConfig config) {
		this.config = config;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getField())) {
			json.put("field", getField());
		} else {
			json.put("field", "");
		}
		if (StringUtils.isNotBlank(getLabel())) {
			json.put("label", getLabel());
		} else {
			json.put("label", "");
		}
		if (getPriority() != null) {
			json.put("priority", getPriority());
		} else {
			json.put("priority", "");
		}
		if (StringUtils.isNotBlank(getDefValue())) {
			json.put("defValue", getDefValue());
		} else {
			json.put("defValue", "");
		}
		if (StringUtils.isNotBlank(getOptValue())) {
			json.put("optValue", getOptValue());
		} else {
			json.put("optValue", "");
		}
		if (StringUtils.isNotBlank(getSize())) {
			json.put("size", getSize());
		} else {
			json.put("size", "");
		}
		if (StringUtils.isNotBlank(getRows())) {
			json.put("rows", getRows());
		} else {
			json.put("rows", "");
		}
		if (StringUtils.isNotBlank(getCols())) {
			json.put("cols", getCols());
		} else {
			json.put("cols", "");
		}
		if (StringUtils.isNotBlank(getHelp())) {
			json.put("help", getHelp());
		} else {
			json.put("help", "");
		}
		if (StringUtils.isNotBlank(getHelpPosition())) {
			json.put("helpPosition", getHelpPosition());
		} else {
			json.put("helpPosition", "");
		}
		if (getDataType() != null) {
			json.put("dataType", getDataType());
		} else {
			json.put("dataType", "");
		}
		json.put("required", getRequired());
		return json;
	}

	public boolean getRequired() {
		return isRequired();
	}

	public void init() {
		if (getPriority() == null) {
			setPriority(10);
		}
	}

	// 将字符串字段全部设置为非null，方便判断。
	public void emptyToNull() {
		if (StringUtils.isBlank(getDefValue())) {
			setDefValue(null);
		}
		if (StringUtils.isBlank(getOptValue())) {
			setOptValue(null);
		}
		if (StringUtils.isBlank(getSize())) {
			setSize(null);
		}
		if (StringUtils.isBlank(getRows())) {
			setRows(null);
		}
		if (StringUtils.isBlank(getCols())) {
			setCols(null);
		}
		if (StringUtils.isBlank(getHelp())) {
			setHelp(null);
		}
		if (StringUtils.isBlank(getHelpPosition())) {
			setHelpPosition(null);
		}
	}
}