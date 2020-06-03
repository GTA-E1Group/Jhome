package com.daxu.common.Queue;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyConsumer extends DefaultConsumer  {
	 private Channel ch;
	 private Connection con;
	   public MyConsumer(Channel channelCurrent,Connection conCurrent){
	        super(channelCurrent);
	        ch=channelCurrent;
	        con=conCurrent;
	    }

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope,
			BasicProperties properties, byte[] body) throws IOException {
		// TODO Auto-generated method stub
		//super.handleDelivery(consumerTag, envelope, properties, body);
		//System.err.println("body: " + new String(body));
		
		try {
			System.out.println("body: " + new String(body)); 
			ch.basicAck(envelope.getDeliveryTag(), false); 
			ch.close();
			con.close();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	   
	   
}
