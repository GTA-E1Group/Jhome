package com.daxu.common.EventHandler.WaterHeater;

import java.util.Date;

/*
 * 热水实体类
 */ 
public class Entit {
   private String temperature;//温度
   private Date   timeDate;//烧水时间
   
   
public Entit() {
	super();
	// TODO Auto-generated constructor stub
}
public Entit(String temperature, Date timeDate) {
	super();
	this.temperature = temperature;
	this.timeDate = timeDate;
}
public String getTemperature() {
	return temperature;
}
public void setTemperature(String temperature) {
	this.temperature = temperature;
}
public Date getTimeDate() {
	return timeDate;
}
public void setTimeDate(Date timeDate) {
	this.timeDate = timeDate;
}
   
}
