package com.bracket.common.Bus;

import com.bracket.common.BatchExcel.ExcelConfig;


public class VoBase<T> implements Cloneable {
 
	private ExcelConfig cxcelConfig;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub 
		T obj = null;
		try {
			obj = (T) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	} 
	public ExcelConfig getCxcelConfig() {
		return cxcelConfig;
	} 
	public void setCxcelConfig(ExcelConfig cxcelConfig) {
		this.cxcelConfig = cxcelConfig;
	}

}
