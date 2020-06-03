package com.daxu.common.ServerSocket.ListenerObj;

import com.daxu.common.Bus.PushTypeBase;
import com.daxu.common.ServerSocket.*;

import java.io.IOException;
import java.util.HashMap;

//import org.eclipse.jdt.internal.compiler.ast.MessageSend;


public class LoginMsgListener extends MessageSender implements 	OnRecevieMsgListener {
    private Connection conn;
	public LoginMsgListener(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn=conn; 
	}
 
	public void onReceive(PushTypeBase msg) {
		if(msg.getPushType()==0)
		{ 
			System.out.println("--------------------"+msg.getPushType());
			//需要解析msg中的用户登录信息；
			Who who=new Who();
			who.setAccount("daxu");
			conn.setWho(who);
			ConnectionManager.addConns(conn); 
			// TODO Auto-generated method stub 
			try {
				Message message =new Message();
				String loginMessage="登陆成功！";
				message.setMessageBody(loginMessage); 
				HashMap<String, Connection> connMap=ConnectionManager.getConns();    
				toClient(message,connMap.get("daxu")); 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
	} 
	
	public void onSendToClient() throws IOException
	{
		Message message =new Message(); 
		message.setMessageBody("111111111111111111111111111");
		toClient(message, conn);	 
	}
	
	
 
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
