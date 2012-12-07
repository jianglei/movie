package com.umeng.core.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.map.LRUMap;

/**
 * 功能：
 * 创建者：江磊
 * 修改者：wangke
 */
public class IpParser {

	static int MAP_MAX_SIZE = 10000;
    // ip的正则表达式
    private static Pattern pattern = Pattern
            .compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");

    // ip缓存
    public static Map<String, String> ipCache = Collections.synchronizedMap(new LRUMap(MAP_MAX_SIZE));

    // ipMap缓存
    public static Map<String, Map<String, String>> ipMapCache = Collections.synchronizedMap(new LRUMap(MAP_MAX_SIZE));

    /**
     * 根据传入的ip地址解析用户所在地
     * 
     * @param ip
     * @param areaList
     * @param DBpath
     * @return
     * @throws Exception
     */
    public static String parseCity(String ip) throws Exception {
//            ,List<Map<String, Object>> areaList, String DBpath) throws Exception {
        String city = "0";

        Matcher matcher = pattern.matcher(ip);
        if (!matcher.matches()) {
            return "0";
        }

        if (StringUtil.isEmpty(ip) || ip.indexOf('.') == -1) {
            return "0";
        }
        String prefix = ip.substring(0, ip.lastIndexOf('.'));
        if (ipCache.containsKey(prefix)) {
            return ipCache.get(prefix);
        }
        QQWryIpParser w = new QQWryIpParser();
        w.seek(ip);
        String cityh = w.getCountry();
        
        System.out.println(cityh);

//        for (Map<String, Object> areaMap : areaList) {
//            if (cityh.contains(areaMap.get("name").toString())) {
//                city = areaMap.get("id").toString();// 这样子上海市北京路就变成北京了
//                break;
//            }
//        }
        if (!StringUtil.isEmpty(city)) {
            ipCache.put(prefix, city);
        } else {
            return "0";
        }
        return city;
    }

//	public static String getCity(String ip){
//	  	// only need city
//		return "";
//	}
    /**
     * 根据传入的ip地址解析用户所在地
     * 
     * @param ip
     * @param areaList
     * @param DBpath
     * @return
     * @throws Exception
     */
    public static String getCity(String ip, String path){
//    		, List<Map<String, Object>> areaList) {
    	String cityh = "";
    	
    	try {
	        Map<String, String> cityMap = new HashMap<String, String>();
	        if (StringUtil.isEmpty(ip)) {
	            cityMap.put("area", "");
	            cityMap.put("id", "0");
	            return "";
	        }
	        Matcher matcher = pattern.matcher(ip);
	        if (!matcher.matches()) {
	            cityMap.put("area", "");
	            cityMap.put("id", "0");
	            return "";
	        }
	
	        String prefix = ip.substring(0, ip.lastIndexOf('.'));
	//        if (ipMapCache.containsKey(prefix)) {
	//            return ipMapCache.get(prefix);
	//        }
	
	        QQWryIpParser w = new QQWryIpParser();
	        w.seek(ip, path);
	        cityh = w.getCountry();
	        /*for (Map<String, Object> areaMap : areaList) {
	            if (cityh.contains(areaMap.get("name").toString())) {
	                // 这样子上海市北京路就变成北京了
	                cityMap.put("area", areaMap.get("name").toString());
	                cityMap.put("id", areaMap.get("id").toString());
	                break;
	            }
	        }*/
	//        if (cityMap.size() != 0) {
	//            ipMapCache.put(prefix, cityMap);
	//        } else {
	//            cityMap.put("area", "");
	//            cityMap.put("id", "0");
	//            return "";
	//        }
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        return cityh;
    }
    
    public static void main(String[] args) {
		try {
			IpParser.parseCity("117.136.8.166");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
