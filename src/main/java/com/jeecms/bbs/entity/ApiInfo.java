package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name="jb_api_info")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ApiInfo implements Serializable{

	
	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月30日上午10:52:10
	 */
	private static final long serialVersionUID = 1601575012698741020L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="api_name",nullable=false,length=255)
	private String name;
	
	@Column(name="api_url",nullable=false,length=255)
	private String url;
	
	@Column(name="api_code",nullable=false,length=50)
	private String code;
	
	@Column(name="disabled",nullable=false)
	private Boolean disabled;
	
	@Column(name="limit_call_day",nullable=false,length=10)
	private Integer limitCallDay;
	
	@Column(name="call_total_count",nullable=false,length=10)
	private Integer callTotalCount;
	
	@Column(name="call_month_count",nullable=false,length=10)
	private Integer callMonthCount;
	
	@Column(name="call_week_count",nullable=false,length=10)
	private Integer callWeekCount;
	
	@Column(name="call_day_count",nullable=false,length=10)
	private Integer callDayCount;
	
	@Column(name="last_call_time",length=50)
	private Date lastCallTime;

	@OneToMany(fetch=FetchType.LAZY,mappedBy="apiInfo")
	private Set<ApiRecord> callRecords;

	public Integer getId () {
		return id;
	}

	
	public void setId (Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getUrl () {
		return url;
	}


	public void setUrl (String url) {
		this.url = url;
	}


	
	public String getCode () {
		return code;
	}

	
	public void setCode (String code) {
		this.code = code;
	}


	
	public Boolean isDisabled () {
		return disabled;
	}

	
	public void setDisabled (Boolean disabled) {
		this.disabled = disabled;
	}

	public Integer getLimitCallDay() {
		return limitCallDay;
	}

	public void setLimitCallDay(Integer limitCallDay) {
		this.limitCallDay = limitCallDay;
	}

	
	public Integer getCallTotalCount () {
		return callTotalCount;
	}

	
	public void setCallTotalCount (Integer callTotalCount) {
		this.callTotalCount = callTotalCount;
	}


	public Integer getCallMonthCount () {
		return callMonthCount;
	}

	public void setCallMonthCount (Integer callMonthCount) {
		this.callMonthCount = callMonthCount;
	}

	public Integer getCallWeekCount () {
		return callWeekCount;
	}

	public void setCallWeekCount (Integer callWeekCount) {
		this.callWeekCount = callWeekCount;
	}

	public Integer getCallDayCount () {
		return callDayCount;
	}

	public void setCallDayCount (Integer callDayCount) {
		this.callDayCount = callDayCount;
	}

	public Date getLastCallTime() {
		return lastCallTime;
	}

	public void setLastCallTime(Date lastCallTime) {
		this.lastCallTime = lastCallTime;
	}

	public Set<com.jeecms.bbs.entity.ApiRecord> getCallRecords () {
		return callRecords;
	}

	public void setCallRecords (Set<com.jeecms.bbs.entity.ApiRecord> callRecords) {
		this.callRecords = callRecords;
	}

	public void init(){
		if (isDisabled()==null) {
			setDisabled(false);
		}
		if(getCallDayCount()==null){
			setCallDayCount(0);
		}
		if(getCallMonthCount()==null){
			setCallMonthCount(0);
		}
		if(getCallWeekCount()==null){
			setCallWeekCount(0);
		}
		if(getCallTotalCount()==null){
			setCallTotalCount(0);
		}
		if (getLimitCallDay()==null) {
			setLimitCallDay(0);
		}
	}
	
	public JSONObject convertToJson() throws JSONException{
		JSONObject json = new JSONObject();
		if (getId()!=null) {
			json.put("id", getId());
		}else{
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getName())) {
			json.put("name", getName());
		}else{
			json.put("name", "");
		}
		if (StringUtils.isNotBlank(getUrl())) {
			json.put("url", getUrl());
		}else{
			json.put("url", "");
		}
		if (StringUtils.isNotBlank(getCode())) {
			json.put("code", getCode());
		}else{
			json.put("code", "");
		}
		json.put("disabled", isDisabled());
		if (getCallTotalCount()!=null) {
			json.put("callTotalCount", getCallTotalCount());
		}else{
			json.put("callTotalCount", "");
		}
		if (getCallMonthCount()!=null) {
			json.put("callMonthCount", getCallMonthCount());
		}else{
			json.put("callMonthCount", "");
		}
		if (getCallWeekCount()!=null) {
			json.put("callWeekCount", getCallWeekCount());
		}else{
			json.put("callWeekCount", "");
		}
		if (getCallDayCount()!=null) {
			json.put("callDayCount", getCallDayCount());
		}else{
			json.put("callDayCount", "");
		}
		if (getLimitCallDay()!=null) {
			json.put("limitCallDay", getLimitCallDay());
		}else{
			json.put("limitCallDay", "");
		}
		return json;
	}

}