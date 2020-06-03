package com.daxu.common.ServerSocket;

import com.daxu.common.ServerSocket.ListenerObj.ChatP2PListener;
import com.daxu.common.ServerSocket.ListenerObj.ChatRoomListener;
import com.daxu.common.ServerSocket.ListenerObj.LoginMsgListener;
import com.daxu.common.ServerSocket.ListenerObj.LoginOutListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author xu.da1
 * 服务端
 * BIO阻塞网络编程  主要是因为 IO是阻塞的，而不是多个请求线程
 */
public class ChatServer {
	public static void main(String[] args) throws IOException {

		//Bio 无限创建线程模式
		ServerSocketByNewThread();
		//Bio 线程池模式 重复利用已有的线程
		//ServerSocketByNewExecutorService();
	}

	public static void ServerSocketByNewThread() throws IOException {
		// ①　创建一个线程 等其他客户端的连接
		final ServerSocket serverSocket  = new ServerSocket(8080);
		System.out.println("---服务器启动---" + new Date().toString());

		try {
			Thread thread=	new Thread(new Runnable() {
				public void run() {
					while (true) {
						Connection conn=null;
						try {
							Socket client =serverSocket.accept();
							conn=new Connection(client);
							conn.addOnRecevieMsgListener(new LoginMsgListener(conn));
							conn.addOnRecevieMsgListener(new ChatP2PListener());
							conn.addOnRecevieMsgListener(new ChatRoomListener());
							conn.addOnRecevieMsgListener(new LoginOutListener());
							conn.connect();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							conn.disconnect();
						}
					}
				}

			}, "123");

			thread.start();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void ServerSocketByNewExecutorService() throws IOException {
		// ①　创建一个线程 等其他客户端的连接
		final ServerSocket serverSocket  = new ServerSocket(8080);
		System.out.println("---服务器启动---" + new Date().toString());

		try {
			ExecutorService executorService= Executors.newFixedThreadPool(60);
			//executorService.execute();



		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
