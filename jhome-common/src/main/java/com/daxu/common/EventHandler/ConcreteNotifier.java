package com.daxu.common.EventHandler;

/**
 * 
 * @author xu.da
 * ʵ�ֳ�����
 */
public class ConcreteNotifier extends  Notifier {
    @Override
	public  void addEventHandler(Object object,String methodName,Object ...args)
	{ 
		this.getEventHandler().addEventHandler(object, methodName, args);
	}
    @Override
    public void removeEventHandler()
	{
	    this.getEventHandler().removeEventHandler();	
	}
	@Override
	public  void notifyX(Object object)  
	{ 
		   try {  
	            this.getEventHandler().notifyX(object);  
	        } catch (Exception e) {  
	            // TODO: handle exception  
	            e.printStackTrace();  
	        }  
		
	}
}
