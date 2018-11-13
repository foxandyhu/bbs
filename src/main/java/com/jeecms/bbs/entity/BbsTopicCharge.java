package com.jeecms.bbs.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;
import com.jeecms.common.util.DateUtils;

/**
 * 主题收费配置
 * @author andy_hulibo@163.com
 * 2018年10月30日下午4:12:24
 */
@Entity
@Table(name="bbs_topic_charge")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsTopicCharge implements Serializable{
	
	public static final Short MODEL_FREE=0;
	public static final Short MODEL_CHARGE=1;
	public static final Short MODEL_REWARD=2;
	
	public static final Boolean REWARD_RANDOM=false;
	public static final Boolean REWARD_FIXED=true;

	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午3:55:15
	 */
	private static final long serialVersionUID = 3845915293067492808L;

	@Column(name="charge_amount")
	private Double chargeAmount;
	
	@Column(name="total_amount")
	private Double totalAmount;
	
	@Column(name="year_amount")
	private Double yearAmount;
	
	@Column(name="month_amount")
	private Double monthAmount;
	
	@Column(name="day_amount")
	private Double dayAmount;
	
	@Column(name="last_buy_time")
	private java.util.Date lastBuyTime;
	
	@Column(name="charge_reward")
	private Short chargeReward;
	
	@Column(name="reward_random_min")
	private Double rewardRandomMin;
	
	@Column(name="reward_random_max")
	private Double rewardRandomMax;
	
	@Column(name="reward_pattern")
	private Boolean rewardPattern;

	@Id
	@JoinColumn(name="topic_id")
	@OneToOne(cascade=CascadeType.ALL)
	private BbsTopic topic;

	public Double getChargeAmount () {
		return chargeAmount;
	}

	public void setChargeAmount (Double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public Double getTotalAmount () {
		return totalAmount;
	}

	public void setTotalAmount (Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getYearAmount () {
		return yearAmount;
	}

	public void setYearAmount (Double yearAmount) {
		this.yearAmount = yearAmount;
	}

	public Double getMonthAmount () {
		return monthAmount;
	}

	public void setMonthAmount (Double monthAmount) {
		this.monthAmount = monthAmount;
	}

	public Double getDayAmount () {
		return dayAmount;
	}

	public void setDayAmount (Double dayAmount) {
		this.dayAmount = dayAmount;
	}

	public java.util.Date getLastBuyTime () {
		return lastBuyTime;
	}

	public void setLastBuyTime (java.util.Date lastBuyTime) {
		this.lastBuyTime = lastBuyTime;
	}

	public Short getChargeReward () {
		return chargeReward;
	}

	public void setChargeReward (Short chargeReward) {
		this.chargeReward = chargeReward;
	}

	public Double getRewardRandomMin () {
		return rewardRandomMin;
	}

	public void setRewardRandomMin (Double rewardRandomMin) {
		this.rewardRandomMin = rewardRandomMin;
	}

	public Double getRewardRandomMax () {
		return rewardRandomMax;
	}

	public void setRewardRandomMax (Double rewardRandomMax) {
		this.rewardRandomMax = rewardRandomMax;
	}

	public Boolean getRewardPattern () {
		return rewardPattern;
	}

	public void setRewardPattern (Boolean rewardPattern) {
		this.rewardPattern = rewardPattern;
	}

	public BbsTopic getTopic () {
		return topic;
	}

	public void setTopic (BbsTopic topic) {
		this.topic = topic;
	}
	
	public JSONObject convertToJson() 
			throws JSONException{
		JSONObject json=new JSONObject();
		json.put("id", getTopic().getId());
		if (getChargeAmount()!=null) {
			json.put("chargeAmount", getChargeAmount());
		}else{
			json.put("chargeAmount", "");
		}
		if (getTotalAmount()!=null) {
			json.put("totalAmount", getTotalAmount());
		}else{
			json.put("totalAmount", "");
		}
		if (getYearAmount()!=null) {
			json.put("yearAmount", getYearAmount());
		}else{
			json.put("yearAmount", "");
		}
		if (getMonthAmount()!=null) {
			json.put("monthAmount", getMonthAmount());
		}else{
			json.put("monthAmount", "");
		}
		if (getDayAmount()!=null) {
			json.put("dayAmount", getDayAmount());
		}else{
			json.put("dayAmount", "");
		}
		if (getChargeReward()!=null) {
			json.put("chargeReward", getChargeReward());
		}else{
			json.put("chargeReward", "");
		}
		if(getLastBuyTime()!=null){
			json.put("lastBuyTime", DateUtils.parseDateToTimeStr(getLastBuyTime()));
		}else{
			json.put("lastBuyTime","");
		}
		if (getTopic()!=null&&StringUtils.isNotBlank(getTopic().getTitle())) {
			json.put("topicTitle", getTopic().getTitle());
		}else{
			json.put("topicTitle", "");
		}
		return json;
	}
	
	public void init(){
		if(getChargeAmount()==null){
			setChargeAmount(0d);
		}
		if(getDayAmount()==null){
			setDayAmount(0d);
		}
		if(getMonthAmount()==null){
			setMonthAmount(0d);
		}
		if(getYearAmount()==null){
			setYearAmount(0d);
		}
		if(getTotalAmount()==null){
			setTotalAmount(0d);
		}
		if(getRewardRandomMax()==null){
			setRewardRandomMax(0d);
		}
		if(getRewardRandomMin()==null){
			setRewardRandomMin(0d);
		}
	}
}