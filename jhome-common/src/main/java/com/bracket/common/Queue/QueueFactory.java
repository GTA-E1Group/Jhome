package com.bracket.common.Queue;

import com.bracket.common.Bus.PushTypeBase;


public abstract class QueueFactory {


	public Queue getQueueByConfig(Config config) {
		return new Queue(config);
	} 
	public abstract boolean AddProducerMQ(String queueName,
			PushTypeBase pushInfo);
	public abstract String GetQueues(String queueName,ReadQueueDao rQueueDao);
	 
}
