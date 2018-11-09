package com.jeecms.bbs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name="bbs_magic_config")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region = "beanCache")
public class BbsMagicConfig implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午3:56:51
	 */
	private static final long serialVersionUID = 5912638278150241531L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="magic_switch")
	private boolean magicSwitch;
	
	@Column(name="magic_discount")
	private Integer magicDiscount;
	
	@Column(name="magic_sofa_lines")
	private String magicSofaLines;

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public boolean isMagicSwitch() {
		return magicSwitch;
	}

	public boolean getMagicSwitch() {
		return magicSwitch;
	}

	
	public void setMagicSwitch(boolean magicSwitch) {
		this.magicSwitch = magicSwitch;
	}

	
	public Integer getMagicDiscount() {
		return magicDiscount;
	}

	
	public void setMagicDiscount(Integer magicDiscount) {
		this.magicDiscount = magicDiscount;
	}

	public String getMagicSofaLines() {
		return magicSofaLines;
	}

	
	public void setMagicSofaLines(String magicSofaLines) {
		this.magicSofaLines = magicSofaLines;
	}

	public JSONObject convertToJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", getId());
		json.put("magicSwitch", getMagicSwitch());
		json.put("magicDiscount", getMagicDiscount());
		json.put("magicSofaLines", getMagicSofaLines());
		return json;
	}
}