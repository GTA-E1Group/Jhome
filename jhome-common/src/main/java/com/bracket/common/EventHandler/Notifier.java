package com.bracket.common.EventHandler;

/**
 * ������
 * @author xu.da
 *
 */
public abstract  class Notifier {

	private EventHandler eventHandler=new EventHandler();

	public EventHandler getEventHandler() {
		return eventHandler;
	} 
	
	public void setEventHandler(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}
	public abstract void addEventHandler(Object object,String methodName,Object ...args);
	public abstract void removeEventHandler(); 
	public abstract void notifyX(Object object);
}
