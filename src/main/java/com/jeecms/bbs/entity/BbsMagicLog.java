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

/**
 * 道具记录表
 * @author andy_hulibo@163.com
 * 2018年11月2日下午2:33:48
 */
@Entity
@Table(name = "bbs_magic_log")
public class BbsMagicLog implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:57:39
	 */
	private static final long serialVersionUID = 243697796493940156L;

	@Id
	@Column(name="log_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="log_time")
	private Date logTime;
	
	@Column(name="operator")
	private Byte operator;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="num")
	private Integer num;

	@ManyToOne
	@JoinColumn(name="targetuid")
	private BbsUser targetUser;
	
	@ManyToOne
	@JoinColumn(name="magic_id")	
	private BbsCommonMagic magic;
	
	@ManyToOne
	@JoinColumn(name="user_id")	
	private BbsUser user;

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	
	public Date getLogTime() {
		return logTime;
	}

	
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	
	public Byte getOperator() {
		return operator;
	}

	public void setOperator(Byte operator) {
		this.operator = operator;
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

	
	public BbsUser getTargetUser() {
		return targetUser;
	}


	public void setTargetUser(BbsUser targetUser) {
		this.targetUser = targetUser;
	}

	public BbsCommonMagic getMagic() {
		return magic;
	}


	public void setMagic(BbsCommonMagic magic) {
		this.magic = magic;
	}

	
	public BbsUser getUser() {
		return user;
	}

	
	public void setUser(BbsUser user) {
		this.user = user;
	}

}