package com.daxu.common.Queue;

import com.daxu.common.Bus.PushTypeBase;


public class QueueHandler extends QueueFactory {
	private Queue queue; 
	public Config config;
	public QueueHandler() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Queue getQueue() {
		return queue;
	} 
	public QueueHandler(Config configInfo) {
		super();
		config = configInfo;
		queue= this.getQueueByConfig(config);
	} 
	/**
	 * 添加到队列
	 */
	@Override
	public boolean AddProducerMQ(String queueName, PushTypeBase pushInfo) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			System.out.println("b1");
			flag =queue.AddProducerMQ(queueName,
					pushInfo);
			System.out.println("b2");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("b3");
			System.out.println(e.getMessage());
		}
		return flag;
	}
	/**
	 * 获取队列       **异步**
	 */
	@Override
	public String GetQueues(String queueName,ReadQueueDao rQueueDao) {
		String jsonStr = null;
		try {
			jsonStr = queue.GetQueues(queueName,rQueueDao);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonStr;
	}

 
}
