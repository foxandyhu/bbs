package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.MetaValue;

import com.jeecms.core.entity.CmsSite;

/**
 * 主题、帖子操作记录
 * @author andy_hulibo@163.com
 * 2018年11月2日下午3:38:15
 */
@Entity
@Table(name="bbs_operation")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsOperation implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:57:23
	 */
	private static final long serialVersionUID = 4299420044332769070L;

	public static final String TOPIC = "TOPI";
	public static final String POST = "POST";
	public static final String MEMBER = "MEMB";

	@Id
	@Column(name = "OPERATOR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="OPT_NAME")
	private String optName;
	
	@Column(name="OPT_REASON")
	private String optReason;
	
	@Column(name="OPT_TIME")
	private Date optTime;

	@ManyToOne
	@JoinColumn(name="site_id")
	private CmsSite site;

	@ManyToOne
	@JoinColumn(name="operater_id")
	private BbsUser operater;
	
	@Any(metaColumn=@Column(name="REF_TYPE"))
	@AnyMetaDef(
            idType = "integer",
            metaType = "string",
            metaValues = {
                    @MetaValue( value="TOPI", targetEntity=BbsTopic.class ),
                    @MetaValue( value="VOTO", targetEntity=BbsVoteTopic.class ),
                    @MetaValue( value="POST", targetEntity=BbsPost.class ),
                    @MetaValue( value="MEMB", targetEntity=BbsUser.class )
            }
    )
    @JoinColumn(name="REF_ID")
	private Object target;

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getOptReason() {
		return optReason;
	}

	public void setOptReason(String optReason) {
		this.optReason = optReason;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public CmsSite getSite() {
		return site;
	}

	public void setSite(CmsSite site) {
		this.site = site;
	}

	public BbsUser getOperater() {
		return operater;
	}

	public void setOperater(BbsUser operater) {
		this.operater = operater;
	}
}