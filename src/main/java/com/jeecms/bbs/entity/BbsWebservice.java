package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 接口
 * @author andy_hulibo@163.com
 * 2018年11月6日下午2:52:41
 */
@Entity
@Table(name="bbs_webservice")
public class BbsWebservice implements Serializable {
	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:53:03
	 */
	private static final long serialVersionUID = -7942323421359469698L;
	public static final String SERVICE_TYPE_ADD_USER = "addUser";
	public static final String SERVICE_TYPE_UPDATE_USER = "updateUser";
	public static final String SERVICE_TYPE_DELETE_USER = "deleteUser";

	public void addToParams(String name, String defaultValue) {
		List<BbsWebserviceParam> list = getParams();
		if (list == null) {
			list = new ArrayList<BbsWebserviceParam>();
			setParams(list);
		}
		BbsWebserviceParam param = new BbsWebserviceParam();
		param.setParamName(name);
		param.setDefaultValue(defaultValue);
		list.add(param);
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getAddress())) {
			json.put("address", getAddress());
		} else {
			json.put("address", "");
		}
		if (StringUtils.isNotBlank(getTargetNamespace())) {
			json.put("targetNamespace", getTargetNamespace());
		} else {
			json.put("targetNamespace", "");
		}
		if (StringUtils.isNotBlank(getSuccessResult())) {
			json.put("successResult", getSuccessResult());
		} else {
			json.put("successResult", "");
		}
		if (StringUtils.isNotBlank(getType())) {
			json.put("type", getType());
		} else {
			json.put("type", "");
		}
		if (StringUtils.isNotBlank(getOperate())) {
			json.put("operate", getOperate());
		} else {
			json.put("operate", "");
		}
		if (getParams() != null) {
			List<BbsWebserviceParam> params = getParams();
			String paramNames = "";
			String defaultValues = "";
			for (int i = 0; i < params.size(); i++) {
				if (StringUtils.isNotBlank(params.get(i).getParamName())) {
					paramNames += params.get(i).getParamName() + ",";
				}
				if (StringUtils.isNotBlank(params.get(i).getDefaultValue())) {
					defaultValues += params.get(i).getDefaultValue() + ",";
				}
			}
			if (paramNames.length() > 0) {
				paramNames = paramNames.substring(0, paramNames.length() - 1);
			}
			if (defaultValues.length() > 0) {
				defaultValues = defaultValues.substring(0, defaultValues.length() - 1);
			}
			json.put("paramNames", paramNames);
			json.put("defaultValues", defaultValues);
		} else {
			json.put("paramNames", "");
			json.put("defaultValues", "");
		}
		return json;
	}

	@Id
	@Column(name="service_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="service_address")
	private String address;
	
	@Column(name="target_namespace")
	private String targetNamespace;
	
	@Column(name="success_result")
	private String successResult;
	
	@Column(name="service_type")
	private String type;
	
	@Column(name="service_operate")
	private String operate;

	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@OrderBy("priority")
    @ElementCollection
	@CollectionTable(name="bbs_webservice_param",joinColumns=@JoinColumn(name="service_id"))
	private List<BbsWebserviceParam> params;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTargetNamespace() {
		return targetNamespace;
	}

	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}

	public String getSuccessResult() {
		return successResult;
	}

	public void setSuccessResult(String successResult) {
		this.successResult = successResult;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public List<BbsWebserviceParam> getParams() {
		return params;
	}

	public void setParams(List<BbsWebserviceParam> params) {
		this.params = params;
	}

}