package com.daxu.common.EventHandler;
import java.lang.reflect.Method;


/**
 *  OBServer���ģʽ
 * @author xu.da
 *
 */
public class Event {
  private Object object;
  private String methodName;
  private Object[] params;
  private Class[] paramTypes;
  
  public Event(Object object,String methodNameString,Object ...args )
  {
	  this.object=object;
	  this.methodName=methodNameString;
	  this.params=args;
	  contractParamTypes(args);
	  
  }
  /**
   * ������������
   */
  public void contractParamTypes(Object[] parsms) {

	  if(parsms!=null&&parsms.length>0)
	  {
		  this.paramTypes=new Class[parsms.length];
		  for (int i = 0; i < parsms.length; i++) {
			  Object object=parsms[i].getClass();
			  this.paramTypes[i]=parsms[i].getClass();
		  }   
	  } 
}
  
  /**
   * ��������
   */
  public void invoke()throws Exception 
  { 
	  Method method ;
	  if(this.params==null||this.params.length==0)
	  {
		method=object.getClass().getMethods()[0];  
	  }
	  else
	  {  
		  method = object.getClass().getMethod(this.methodName, this.paramTypes);//�ж��Ƿ�����������  
	  }  
     if(method==null)
     {
    	 return;  
     }
     method.invoke(this.object,this.params); 
}
  /**
   * �������� ���ط���һ
   */
  public void invoke(Object obj)throws Exception 
  { 
	  Method method ;    
      method = object.getClass().getMethod(this.methodName, obj.getClass());//�ж��Ƿ����������� 
	  
     if(method==null)
     {
    	 return;  
     }
     method.invoke(this.object,obj); 
}
}
