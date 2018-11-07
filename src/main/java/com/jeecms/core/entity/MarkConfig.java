package com.jeecms.core.entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class MarkConfig implements Serializable{
	
	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:11:11
	 */
	private static final long serialVersionUID = 8374328958472848015L;

	@Column(name="mark_on")
	private Boolean on;
	
	@Column(name="mark_width")
	private Integer minWidth;
	
	@Column(name="mark_height")
	private Integer minHeight;
	
	@Column(name="mark_image")
	private String imagePath;
	
	@Column(name="mark_content")
	private String content;
	
	@Column(name="mark_size")
	private Integer size;
	
	@Column(name="mark_color")
	private String color;
	
	@Column(name="mark_alpha")
	private Integer alpha;
	
	@Column(name="mark_position")
	private Integer pos;
	
	@Column(name="mark_offset_x")
	private Integer offsetX;
	
	@Column(name="mark_offset_y")
	private Integer offsetY;

	public Boolean getOn() {
		return on;
	}

	public void setOn(Boolean on) {
		this.on = on;
	}

	public Integer getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(Integer minWidth) {
		this.minWidth = minWidth;
	}

	public Integer getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(Integer minHeight) {
		this.minHeight = minHeight;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getAlpha() {
		return alpha;
	}

	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Integer getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(Integer offsetX) {
		this.offsetX = offsetX;
	}

	public Integer getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(Integer offsetY) {
		this.offsetY = offsetY;
	}

}