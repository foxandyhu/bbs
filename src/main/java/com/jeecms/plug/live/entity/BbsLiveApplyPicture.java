package com.jeecms.plug.live.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class BbsLiveApplyPicture implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:40:37
	 */
	private static final long serialVersionUID = -3411415402208459001L;

	@Column(name = "img_path")
	private String imgPath;
	
	@Column(name = "description")
	private String description;

	
	public String getImgPath() {
		return imgPath;
	}

	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

}