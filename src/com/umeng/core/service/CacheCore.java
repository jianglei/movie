package com.umeng.core.service;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CacheCore<T>  {
	public T result;
	
	public void process(Jedis jedis){
	}
	
	public void handle(JedisPool jedisPool, int dbIndex){
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();  
			jedis.select(dbIndex);
			process(jedis);
		} catch (Exception e) {
			e.printStackTrace();
			if (jedis != null){
				jedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
		} finally {
			if (jedis != null){
				jedisPool.returnResource(jedis);
				jedis = null;
			}
		}
	}
}
