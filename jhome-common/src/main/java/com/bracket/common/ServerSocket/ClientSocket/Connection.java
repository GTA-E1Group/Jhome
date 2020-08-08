package com.bracket.common.ServerSocket.ClientSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection extends Thread {
	private DataInputStream reader;
	private DataOutputStream writer;
	private String host;
	private int port;
	private Socket client;
	/**
	 * new出QQConnection对象的时候初始化IP地址和端口
	 * 
	 * @param host
	 * @param port
	 */
	public Connection(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}
 
	/**
	 * 创建与服务器之间的连接
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public void connect() throws UnknownHostException, IOException {
		// 创建连接
		client = new Socket(host, port);
		reader = new DataInputStream(client.getInputStream());
		writer = new DataOutputStream(client.getOutputStream());
	}
 
}
