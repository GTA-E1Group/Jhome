package com.test.spider;

import com.bracket.common.Bus.IBus;
import com.bracket.common.Bus.PushInfo;
import com.bracket.common.Queue.Bus;
import com.bracket.common.Queue.Config;
import com.bracket.common.Queue.QueueHandler;

import java.util.Date;

public class testSendMq {
public static void main(String[] args) {
	new Bus(new Config("192.168.43.93", "daxu", "13528764027", "dxhost"), new IBus() {	
		 
		public void doQueueHandle(QueueHandler queueManager) {
			try {
				for (int i = 0; i < 10000; i++) {
					PushInfo info = new PushInfo();
					info.setMessageBody("123123");
					info.setJpushTime(new Date());
					if (queueManager.AddProducerMQ("daxu", info)) {
						System.out.println("发送成功");
						//Thread.sleep(1000);
					}else {
						System.out.println("发送失败了");
					}
				}
				
			} catch (Exception e) {
			}
		}
	}).Send();
}
}
