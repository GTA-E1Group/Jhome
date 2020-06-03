package com.daxu.common.EventHandler.WaterHeater;

import com.daxu.common.EventHandler.Notifier;

import java.util.Date;


public class WaterBoiler implements ElectricAppliance{
    private Notifier cnf;
    private boolean falg=false;
    
    
	public boolean isFalg() {
		return falg;
	}
	public void setFalg(boolean falg) {
		this.falg = falg;
	}
 
	public Notifier getCnf() {
		return cnf;
	}
	public void setCnf(Notifier cnf) {
		this.cnf = cnf;
	} 
	
	public void Power() {
		// TODO Auto-generated method stub
		System.out.println("热水器在充电");
	}
    public void BoiledWater() {
    	int index=0; 
		do {
			if (!falg) {
				 System.out.println("取消烧水啦\n");
				break;
			}
			System.out.println(String.format("正在烧水 %s,%s 请稍等....", index,
					"daxu"));
			if (index == 800) {  
				this.cnf.notifyX(new Entit("1000",new Date()));
				System.out.println("水烧好了.000000000000000000000");
				break;
			}
			index++; 
		} while (index < 1000); 	 	
	}
    
    
    
}
