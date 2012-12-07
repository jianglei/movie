package com.umeng.core.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;

public class MapUtil {
	public static Map<String, String> slice(Map<String, String> params, Set<String> wanted) {
		Map<String, String> result = new HashMap<String, String>();
		for(String key : params.keySet()) {
			if(wanted.contains(key))
				result.put(key, params.get(key));
		}
		return result;
	}
	
	public static Map<String, String> sliceO(Map<String, Object> params, Set<String> wanted) {
		Map<String, String> result = new HashMap<String, String>();
		for(String key : params.keySet()) {
			if(wanted.contains(key))
				result.put(key, MapUtils.getString(params, key));
		}
		return result;
	}

}
