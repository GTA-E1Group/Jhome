package com.daxu.common.ServerSocket.ListenerObj;

import com.daxu.common.Bus.PushTypeBase;
import com.daxu.common.ServerSocket.MessageSender;
import com.daxu.common.ServerSocket.OnRecevieMsgListener;
import com.daxu.common.ToolKit.JSONUtils;

//import org.eclipse.jdt.internal.compiler.ast.MessageSend;


public class ChatRoomListener  extends MessageSender implements OnRecevieMsgListener {

	 
	public void onReceive(PushTypeBase msg) {
		// TODO Auto-generated method stub
		System.out.println(String.format("我是ChatRoomListener，我收到消息了,消息为：%s", JSONUtils.beanToJson(msg)));
	}

}
