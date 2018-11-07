package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;

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

import com.jeecms.common.util.DateUtils;

@Entity
@Table(name="bbs_grade")
public class BbsGrade implements Serializable{

	/**
	 * @author andy_hulibo@163.com
	 * 2018年10月26日下午3:58:07
	 */
	private static final long serialVersionUID = 6428059156462419066L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GRADE_ID")
	private Integer id;

	@Column(name="SCORE")
	private Integer score;
	
	@Column(name="REASON")
	private String reason;
	
	@Column(name="GRADE_TIME")
	private Date gradeTime;

	@ManyToOne
	@JoinColumn(name="POST_ID")
	private BbsPost post;

	@ManyToOne
	@JoinColumn(name="user_id")
	private BbsUser grader;

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public Integer getScore () {
		return score;
	}

	public void setScore (Integer score) {
		this.score = score;
	}

	public String getReason () {
		return reason;
	}

	public void setReason (String reason) {
		this.reason = reason;
	}

	public Date getGradeTime () {
		return gradeTime;
	}

	public void setGradeTime (Date gradeTime) {
		this.gradeTime = gradeTime;
	}

	public BbsPost getPost () {
		return post;
	}

	public void setPost (BbsPost post) {
		this.post = post;
	}

	public BbsUser getGrader () {
		return grader;
	}

	public void setGrader (BbsUser grader) {
		this.grader = grader;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json=new JSONObject();
		json.put("id", getId());
		json.put("score", getScore());
		json.put("gradeTime", DateUtils.parseDateToTimeStr(getGradeTime()));
		json.put("graderUser", getGrader().getUsername());
		if(StringUtils.isNotBlank(getReason())){
			json.put("reason", getReason());
		}else{
			json.put("reason","");
		}
		return json;
	}
}