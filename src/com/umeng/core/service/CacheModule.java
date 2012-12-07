package com.umeng.core.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.umeng.core.utils.cache.CacheUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CacheModule {
	private JedisPool jedisPool;
	private int dbIndex;

	public CacheModule(JedisPool jedisPool, int dbIndex) {
		this.jedisPool = jedisPool;
		this.dbIndex = dbIndex;
	}

	public Set<String> getSet(final String key) {
		if (key == null)
			return null;

		CacheCore<Set<String>> service = new CacheCore<Set<String>>() {
			public void process(Jedis jedis) {
				result = jedis.smembers(key);
				if (result == null) {
					result = new HashSet<String>(0);
				}
			};
		};

		service.handle(jedisPool, dbIndex);
		return service.result;
	}

	public void setSet(final String key, final Set<String> values,
			final Integer expiredTime) {
		CacheCore service = new CacheCore() {
			public void process(Jedis jedis) {
				CacheUtils.sadd(jedis, key, values);
				if (expiredTime != null)
					jedis.expire(key, expiredTime);
			};
		};

		service.handle(jedisPool, dbIndex);
	}

	public boolean existKey(final String key) {
		CacheCore<Boolean> service = new CacheCore<Boolean>() {

			public void process(Jedis jedis) {
				result = jedis.exists(key);
			};
		};

		service.handle(jedisPool, dbIndex);
		return service.result;
	}

	public String getString(final String key) {
		if (key == null)
			return null;
		CacheCore<String> service = new CacheCore<String>() {
			
			public void process(Jedis jedis) {
				result = "";
				result = jedis.get(key);
			};
		};

		service.handle(jedisPool, dbIndex);
		return service.result;
	}

	public void setString(final String key, final String value,
			final Integer expiredTime) {
		CacheCore service = new CacheCore() {
			public void process(Jedis jedis) {
				jedis.set(key, value); // just set key, not actual value
				if (expiredTime != null)
					jedis.expire(key, expiredTime);
			};
		};

		service.handle(jedisPool, dbIndex);
	}
	
	public void set(final String key, final String value) {
		CacheCore service = new CacheCore() {
			public void process(Jedis jedis) {
				jedis.set(key, value); // just set key, not actual value
			};
		};

		service.handle(jedisPool, dbIndex);
	}

	public Map<String, Object> getHash(final String key) {
		if (key == null)
			return null;

		CacheCore<Map<String, Object>> service = new CacheCore<Map<String, Object>>() {
			public void process(Jedis jedis) {
				result = new HashMap<String, Object>();
				result = CacheUtils.hgetAll(jedis, key);
			};
		};

		service.handle(jedisPool, dbIndex);
		return service.result;
	}

	public void setHash(final String key, final Map<String, Object> values,
			final Integer expiredTime) {
		CacheCore service = new CacheCore() {

			public void process(Jedis jedis) {
				CacheUtils.hmset(jedis, key, values, expiredTime);
			};
		};

		service.handle(jedisPool, dbIndex);
	}
	
	
	
	public void setSHash(final String key, final Map<String, String> values,
			final Integer expiredTime) {
		CacheCore service = new CacheCore() {

			public void process(Jedis jedis) {
				jedis.hmset(key, values);
				if(expiredTime != null){
					jedis.expire(key, expiredTime);
				}
			};
		};

		service.handle(jedisPool, dbIndex);
	}
	
	public void hmset(final String key, final Map<String, String> values) {
		CacheCore service = new CacheCore() {

			public void process(Jedis jedis) {
				jedis.hmset(key, values);
			};
		};

		service.handle(jedisPool, dbIndex);
	}

	public List<Map<String, Object>> getHashList(final String keyPrefix) {
		if (keyPrefix == null)
			return null;

		CacheCore<List<Map<String, Object>>> service = new CacheCore<List<Map<String, Object>>>() {
			public void process(Jedis jedis) {
				result = new LinkedList<Map<String, Object>>();
				Set<String> keySet = jedis.keys(keyPrefix + "*");
				for (String key : keySet) {
					result.add(CacheUtils.hgetAll(jedis, key));
				}
			};
		};

		service.handle(jedisPool, dbIndex);
		return service.result;
	}

	public void setHashList(final String keyPrefix, final String keybase,
			final List<Map<String, Object>> mapList, final Integer expiredTime) {
		CacheCore service = new CacheCore() {
			public void process(Jedis jedis) {
				for (Map<String, Object> map : mapList) {
					String key = keyPrefix + map.get(keybase);
					CacheUtils.hmset(jedis, key, map, expiredTime);
				}
			};
		};

		service.handle(jedisPool, dbIndex);
	}

	public void deleteOne(final String keys) {
		if (keys == null) {
			return;
		}

		CacheCore service = new CacheCore() {
			public void process(Jedis jedis) {
				jedis.del(keys);
			};
		};

		service.handle(jedisPool, dbIndex);
	}

	public void deleteMany(final String keyPrefix) {
		if (keyPrefix == null) {
			return;
		}

		CacheCore service = new CacheCore() {
			public void process(Jedis jedis) {
				Set<String> keySet = jedis.keys(keyPrefix + "*");
				for (String key : keySet) {
					jedis.del(key);
				}
			};
		};

		service.handle(jedisPool, dbIndex);
	}
	
	public void rpush(final String list, final String item){
		if (StringUtils.isBlank(list) && StringUtils.isBlank(item)) {
			return;
		}
		
		CacheCore service = new CacheCore() {
			public void process(Jedis jedis) {
				jedis.rpush(list, item);
			};
		};
		
		service.handle(jedisPool, dbIndex);
	}
	
	public void sadd(final String set, final String item){
		if (StringUtils.isBlank(set) && StringUtils.isBlank(item)) {
			return;
		}
		
		CacheCore service = new CacheCore() {
			public void process(Jedis jedis) {
				jedis.sadd(set, item);
			};
		};
		
		service.handle(jedisPool, dbIndex);
	}

}
