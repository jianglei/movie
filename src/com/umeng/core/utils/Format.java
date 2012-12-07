package com.umeng.core.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class Format {
	public static HashMap<String, Double> formatToDouble(
			Map<String, Object> target) {
		HashMap<String, Double> map = new HashMap<String, Double>();

		if (target != null) {
			for (Map.Entry<String, Object> m : target.entrySet()) {
				map.put(m.getKey(), Double.parseDouble(m.getValue().toString()));
			}
		}

		return map;
	}

	public static HashMap<Integer, String> formatToInteger(
			Map<String, Object> target) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		if (target != null) {
			for (Map.Entry<String, Object> m : target.entrySet()) {
				map.put(Integer.parseInt(m.getKey()), (String) m.getValue());
			}
		}

		return map;
	}

	public static String[] formatToString(Object[] target) {
		if (target != null) {
			String[] wanted = new String[target.length];
			int index = 0;
			for (Object s : target) {
				wanted[index] = (String) s;
				index++;
			}

			return wanted;
		} else {
			return new String[] {};
		}

	}
	
	public static Map<String, String> convertToMap(String result) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject output = JSONObject.fromObject(result);

		for (Object elem : output.keySet()) {
			map.put(elem.toString(), output.get(elem).toString());
		}

		return map;
	}

}
