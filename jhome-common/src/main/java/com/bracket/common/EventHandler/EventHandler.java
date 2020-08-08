package com.bracket.common.EventHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xu.da
 * �¼����й�����
 */
public class EventHandler {
	
	private List<Event> objects;
	public EventHandler()
	{
		objects=new ArrayList<Event>();
		
	}
	
	public void addEventHandler(Object object ,String mentodNmae, Object ...args) {
		
		objects.add(new Event(object, mentodNmae, args));
	}
	public void removeEventHandler() {
		if(objects.size()>0)
		{ 
			objects.clear();
		}
	}
	
     public void notifyX(Object object) throws Exception 
     {
		 for (Event event : objects) {
			event.invoke(object);
		}   
	}

}