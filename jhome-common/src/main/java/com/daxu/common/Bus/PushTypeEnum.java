package com.daxu.common.Bus;

public enum PushTypeEnum {
	LoginType("登陆"),	
	MessAge("消息");  
    private String pushTypeStr;
    //枚举类型的构造函数默认为private，因为枚举类型的初始化要在当前枚举类中完成。
    PushTypeEnum (String pushTypeStr){
        this.pushTypeStr= pushTypeStr;
    } 
    
    public String getPushTypeStr() {
		return pushTypeStr;
	}
 

}
