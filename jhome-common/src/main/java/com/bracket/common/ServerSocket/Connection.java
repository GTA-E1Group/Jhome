package com.bracket.common.ServerSocket;

import com.bracket.common.ToolKit.JSONUtils;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.csdn.net/qq_37465638/article/details/82563481
 * https://blog.csdn.net/analogous_love/article/details/77450679
 * 
 * @author xu.da1 连接对象
 */
public class Connection extends Thread {
	private Socket socket = null;
	private DataInputStream reader = null;
	private DataOutputStream writer = null;
	private BufferedReader bufferedReader = null;
	private boolean flag;
	private Who who;
	private List<OnRecevieMsgListener> listeners = new ArrayList<OnRecevieMsgListener>();

	public Connection() {
	}

	public Connection(Socket socket) {
		super();
		try {
			this.socket = socket;
			writer = new DataOutputStream(this.socket.getOutputStream());
			reader = new DataInputStream(this.socket.getInputStream());
			bufferedReader = new BufferedReader(
					new InputStreamReader(System.in));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void connect() {
		flag = true;
		start();
	}

	public void disconnect() {
		flag = false;
		if (!this.isInterrupted()) {
			this.interrupt();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (this.flag) {
			try {
		 
				// 读取信息要放在最前面，以读取客户端发来的信息
				String jsonStr = reader.readUTF(); 
				System.out.println(jsonStr);

				if (jsonStr != null && !"".equals(jsonStr)) {
					Message msg = new Message();
					msg = (Message) JSONUtils.jsonToBean(jsonStr, msg);
					for (OnRecevieMsgListener l : listeners) {
						l.onReceive(msg);   
					}
				}
 	        
				// 从控制台读入信息
				String aString = bufferedReader.readLine();
				if (aString.equals("bye"))
				{
					System.out.println(aString);
					break;
				}
					 
				// 写到输出流中，由于有了连接，客户端的读入流可以读到输出流中的内容
				writer.writeUTF(aString);
				writer.flush();
				

			} catch (EOFException e) {
				// TODO: handle exception
				e.printStackTrace();
				if (who != null) {
					ConnectionManager.remove(who.getAccount());
				}
				disconnect();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				if (who != null) {
					ConnectionManager.remove(who.getAccount());
				}
				disconnect();
			}
		}
	}

	public void addOnRecevieMsgListener(OnRecevieMsgListener ojb) {
		// TODO Auto-generated method stub
		this.listeners.add(ojb);
	}

	public DataInputStream getReader() {
		return reader;
	}

	public void setReader(DataInputStream reader) {
		this.reader = reader;
	}

	public DataOutputStream getWriter() {
		return writer;
	}

	public void setWriter(DataOutputStream writer) {
		this.writer = writer;
	}

	public List<OnRecevieMsgListener> getListeners() {
		return listeners;
	}

	public void setListeners(List<OnRecevieMsgListener> listeners) {
		this.listeners = listeners;
	}

	public Who getWho() {
		return who;
	}

	public void setWho(Who who) {
		this.who = who;
	}

}
