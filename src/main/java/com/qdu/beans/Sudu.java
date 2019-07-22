package com.qdu.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Sudu {
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date gtime;
	private Integer i1;
	private Integer i2;
	private Integer i3;
	private Integer i4;
	public Date getGtime() {
		return gtime;
	}
	public void setGtime(Date gtime) {
		this.gtime = gtime;
	}
	public Integer getI1() {
		return i1;
	}
	public void setI1(Integer i1) {
		this.i1 = i1;
	}
	public Integer getI2() {
		return i2;
	}
	public void setI2(Integer i2) {
		this.i2 = i2;
	}
	public Integer getI3() {
		return i3;
	}
	public void setI3(Integer i3) {
		this.i3 = i3;
	}
	public Integer getI4() {
		return i4;
	}
	public void setI4(Integer i4) {
		this.i4 = i4;
	}
	
	
	}
