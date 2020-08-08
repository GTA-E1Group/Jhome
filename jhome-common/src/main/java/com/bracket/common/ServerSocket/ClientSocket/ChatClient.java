package com.bracket.common.ServerSocket.ClientSocket;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	
	public static void main(String[] args) {

		System.out.println("客户端启动...");

		Thread thread = new Thread() {
			public void run() {
				try {
					Socket socket = new Socket("127.0.0.1", 8080);
				
					DataInputStream inputStream = new DataInputStream(
							socket.getInputStream());
					DataOutputStream outputStream = new DataOutputStream(
							socket.getOutputStream());
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(System.in));
				 
					while (true) { 
			 
						String aString=bufferedReader.readLine();
						if(aString.equals("bye"))break;
						outputStream.writeUTF(aString);
						outputStream.flush();  
						
						String iString=inputStream.readUTF();
						System.out.println(iString);
						 
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				} catch (ConnectException e) {
					System.out.println("服务器未启动！");
				} catch (IOException e) {// 必须放在所有的catch的最后
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("客户端停止");
			}
		};
		thread.start();

	}
}
