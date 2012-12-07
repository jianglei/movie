/**
 * 
 */
package com.umeng.core.utils.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * @author ke
 * user redis now, maybe replace by other cache server
 */
public class CacheUtils {
	
	//1.Set
	public static void sadd(Jedis cacheServer, String key, Set<String> values){
		for (String v : values){
			cacheServer.sadd(key, v);
		}
	}
	
	public static void hmset(Jedis cacheInstance, String key, Map<String, Object> map) {
		Map<String, String> hash = new HashMap<String, String>();

		for(String k : map.keySet()) {
			hash.put(k, map.get(k) == null ? "" : map.get(k).toString());
		}
		cacheInstance.hmset(key, hash);
	}
	
	public static void hmset(Jedis cacheInstance, String key, Map<String, Object> map, Integer expiredTime) {
		Map<String, String> hash = new HashMap<String, String>();

		for(String k : map.keySet()) {
			hash.put(k, map.get(k) == null ? "" : map.get(k).toString());	
		}
		cacheInstance.hmset(key, hash);
		if(expiredTime != null)
			cacheInstance.expire(key, expiredTime);
	}
	
	public static Map<String, Object> hgetAll(Jedis jedis, String key) {
		Map<String, Object> result = new HashMap<String, Object>();

		result.putAll(jedis.hgetAll(key));
		return result;
	}
		
}
