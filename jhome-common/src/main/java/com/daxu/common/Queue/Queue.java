package com.daxu.common.Queue;

import com.daxu.common.Bus.PushTypeBase;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
 

/*
 * 队列请求处理
 */

public class Queue {
	private RabbitMqUtil raMqUtil;
	
	public RabbitMqUtil getRaMqUtil() {
		return raMqUtil;
	}
	public Queue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Queue(Config config) {
		super();
		this.raMqUtil = new RabbitMqUtil(config);
	}
	public boolean AddProducerMQ(String queueName, PushTypeBase pushInfo)
			throws IOException, TimeoutException {
		return raMqUtil.<PushTypeBase> AddProducerMQ(queueName, pushInfo);
	}
	public String GetQueues(String queueName,ReadQueueDao rQueueDao) throws IOException,
			TimeoutException {
		return raMqUtil.GetQueues(queueName, false,rQueueDao);
	}
	public boolean SendQueue() {
		System.out.println("发送成功");
		return true;
	}

}
