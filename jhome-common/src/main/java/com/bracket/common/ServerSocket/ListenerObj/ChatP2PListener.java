package com.bracket.common.ServerSocket.ListenerObj;

import com.bracket.common.Bus.PushTypeBase;
import com.bracket.common.Bus.PushTypeBase;
import com.bracket.common.ServerSocket.MessageSender;
import com.bracket.common.ServerSocket.OnRecevieMsgListener;
import com.bracket.common.ToolKit.JSONUtils;

//import org.eclipse.jdt.internal.compiler.ast.MessageSend; 

public class ChatP2PListener extends MessageSender  implements OnRecevieMsgListener {
 
	public void onReceive(PushTypeBase msg) {
		// TODO Auto-generated method stub
		System.out.println(String.format("我是onReceive，我收到消息了,消息为：%s", JSONUtils.beanToJson(msg)));

	}

 


}
