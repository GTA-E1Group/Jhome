package com.bracket.common.EventHandler.WaterHeater;

public class WaterMonitor implements ElectricAppliance{
 
	public void Power() {
		// TODO Auto-generated method stub
		System.out.println("显示屏在充电");
	}
	
	public void DisplayMassAge(Entit ojb) {
		Entit objEntit=(Entit)ojb; 
		System.out.println(String.format("显示器：温度：%s 时间：%tF,%tT", objEntit.getTemperature(),objEntit.getTimeDate(),objEntit.getTimeDate()));
	}

}
