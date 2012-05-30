/**
 * 
 */
package com.umeng.ufp.publisher.service;

import java.io.IOException;

import com.google.protobuf.ByteString;


/**
 * @author ke
 *
 */
public interface MessageQueueService {
	
	public void sendMessage(String exchangeName, String queueName, String routingKey, String message) throws IOException;
	public void sendMessage(String exchangeName, String queueName, String routingKey, ByteString byteString) throws IOException;
	public void sendMessage(String exchangeName, String queueName, String routingKey, byte[] bytes) throws IOException;
}
