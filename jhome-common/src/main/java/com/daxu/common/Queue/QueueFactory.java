package com.daxu.common.Queue;

import com.daxu.common.Bus.PushTypeBase;


public abstract class QueueFactory {


	public Queue getQueueByConfig(Config config) {
		return new Queue(config);
	} 
	public abstract boolean AddProducerMQ(String queueName,
			PushTypeBase pushInfo);
	public abstract String GetQueues(String queueName,ReadQueueDao rQueueDao);
	 
}
