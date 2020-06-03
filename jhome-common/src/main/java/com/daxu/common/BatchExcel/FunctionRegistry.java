package com.daxu.common.BatchExcel;

import com.daxu.common.BatchExcel.ConvertFunctionDao.ConverterDao;

import java.util.HashMap;

/**
 * @author xu.da1
 *  功能注册器
 */
public class FunctionRegistry {
	private HashMap<String, ConverterDao> eventHandler;
 
	public FunctionRegistry() {
		super();
		// TODO Auto-generated constructor stub
		eventHandler=new HashMap<String, ConverterDao>();
	}
	/**
	 * 注册
	 * @param name
	 * @param conDao
	 * @return
	 */
	public boolean bind(String name,ConverterDao conDao) {
		eventHandler.put(name, conDao);
		return true;		
	}
	
	public HashMap<String, ConverterDao> getEventHandler() {
		return eventHandler;
	} 
	public void setEventHandler(HashMap<String, ConverterDao> eventHandler) {
		this.eventHandler = eventHandler;
	} 

}
