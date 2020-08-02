package com.Spider.pojo;

public class ymxModel {
	public String titleName;
	public String imgUrlStr;
	public String timeStr;
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getImgUrlStr() {
		return imgUrlStr;
	}
	public void setImgUrlStr(String imgUrlStr) {
		this.imgUrlStr = imgUrlStr;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	@Override
	public String toString() {
		return "ymxModel [titleName=" + titleName + ", imgUrlStr=" + imgUrlStr
				+ ", timeStr=" + timeStr + "]";
	}
	
	
	

}
