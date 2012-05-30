package com.umeng.ufp.publisher.service.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.protobuf.ByteString;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.umeng.ufp.publisher.service.MessageQueueService;

@Service
public class MessageQueueServiceImpl implements MessageQueueService {
	@Resource
	private Connection rabbitConnection;
	
	public void sendMessage(String exchangeName, String queueName, String routingKey, String message) throws IOException {

		sendMessage(exchangeName, queueName, routingKey, message.getBytes());
	}
	
	public void sendMessage(String exchangeName, String queueName, String routingKey, ByteString message) throws IOException {
		
		sendMessage(exchangeName, queueName, routingKey, message.toByteArray());
	}
	
public void sendMessage(String exchangeName, String queueName, String routingKey, byte[] message) throws IOException {
		
		Channel channel = rabbitConnection.createChannel();
		
        channel.exchangeDeclare(exchangeName, "direct", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
        
        channel.basicPublish(exchangeName, routingKey, null, message);
        
	    channel.close();
	}


}
