package com.bracket.common.Vo;

import com.bracket.common.Bus.VoBase;


public class professionEntity  extends VoBase<professionEntity>{
	public String majorName;
	public String majorCode;
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getMajorCode() {
		return majorCode;
	}
	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}
	public professionEntity(String majorName, String majorCode) {
		super();
		this.majorName = majorName;
		this.majorCode = majorCode;
	}
	public professionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
