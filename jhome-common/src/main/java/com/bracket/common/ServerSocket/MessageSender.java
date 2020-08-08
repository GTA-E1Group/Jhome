package com.bracket.common.ServerSocket;

import com.bracket.common.Bus.PushTypeBase;
import com.bracket.common.ToolKit.JSONUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class MessageSender {
	/** 
	 * one-one
	 * @author xu.da1
	 * @param msg 消息对象
	 * @param conn 监听对象
	 * 
	 */
	public void	toClient(PushTypeBase msg, Connection conn) throws IOException {
		System.out.println(String.format("当前客户端发送请求：%S", JSONUtils.beanToJson(msg)));
		conn.getWriter().writeUTF(JSONUtils.beanToJson(msg));
	}
	 
	/**one - many
	 * @author xu.da1
	 *
	 */
	public void toEveryClient(PushTypeBase msg) throws IOException {
		System.out.println("群发所有客户端  to toEveryClient Client \n" + JSONUtils.beanToJson(msg));
		Set<Map.Entry<String, Connection>> allOnLines = ConnectionManager.conns
				.entrySet();
		for (Map.Entry<String, Connection> entry : allOnLines) {
			entry.getValue().getWriter().writeUTF(JSONUtils.beanToJson(msg)); 
		}
	}
	 
	public void toOtherClient(PushTypeBase msg) throws IOException {
		System.out.println("群发所有其他客户端  to toEveryClient Client \n"
				+ JSONUtils.beanToJson(msg));
		// conn.writer.writeUTF(toClient.toXml());
		Set<Map.Entry<String, Connection>> allOnLines = ConnectionManager.conns
				.entrySet();
		for (Map.Entry<String, Connection> entry : allOnLines) {
			//if (entry.getValue().who.account != msg.from) {
				entry.getValue().getWriter().writeUTF(JSONUtils.beanToJson(msg));
			//}
		}
	}
  
}
