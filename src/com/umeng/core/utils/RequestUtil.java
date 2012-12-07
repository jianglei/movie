package com.umeng.core.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.common.base.Joiner;

/**
 * RequestUtil
 */
public class RequestUtil {

    /**
     * get ip
     * TODO: x-forwarded-for maybe 123.342.89.23,23.34.23.12,9.8.8.8
     */
    public static String getIP(HttpServletRequest req) {
        String ip = req.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        return ip;
    }
	
	
	/**
	 * get URL of the request
	 */
	public static String getServerURL(HttpServletRequest request){
		return String.format("%s://%s:%s", request.getScheme(), request.getServerName(), request.getServerPort());
	}
	
	/**
	 * convert request.getParameterMap from a String[] value map to String value
	 */
	public static Map<String, String> getParamterMap(HttpServletRequest request){
		Map<String, String> params = new HashMap<String, String>();

		Map<String, String[]> paramArrayMap = request.getParameterMap();
    	for (String key : paramArrayMap.keySet()){
    		String value = Joiner.on(",").join(paramArrayMap.get(key));
    		params.put(key, value);
    	}
    	
    	return params;
	}
	
	

	public static int getIntParam(String name, HttpServletRequest request, int defaultValue) {
		String s = request.getParameter(name);
		if(s == null){
			return defaultValue;
		}
		return Integer.parseInt(s);
	}

	static long getLongParam(String name, HttpServletRequest request, long defaultValue) {
		String s = request.getParameter(name);
		if(s == null){
			return defaultValue;
		}
		return Long.parseLong(s);
	}

	static String getStringParam(String name, HttpServletRequest request, String defaultValue) {
		String s = (String) request.getParameter(name);
		if(s == null){
			return defaultValue;
		}
		return s;
	}

	static boolean getBooleanParam(String name, HttpServletRequest request, boolean defaultValue) {
		String s = (String) request.getParameter(name);
		if(s == null){
			return defaultValue;
		}
		return Boolean.parseBoolean(s);
	}

	static Integer getIntParam(String name, HttpServletRequest request) {
		String s = request.getParameter(name);
		if(s == null){
			return null;
		}
		return Integer.parseInt(s);
	}

	static Long getLongParam(String name, HttpServletRequest request) {
		String s = request.getParameter(name);
		if(s == null){
			return null;
		}
		return Long.parseLong(s);
	}

	static String getStringParam(String name, HttpServletRequest request) {
		String s = (String) request.getParameter(name);
		if(s == null){
			return null;
		}
		return s;
	}

	static Boolean getBooleanParam(String name, HttpServletRequest request) {
		String s = (String) request.getParameter(name);
		if(s == null){
			return null;
		}
		return Boolean.parseBoolean(s);
	}

	static int getIntAttr(String name, HttpServletRequest request, int defaultValue) {
		Integer val = (Integer) request.getAttribute(name);
		return val == null ? defaultValue : val;
	}

	static long getLongAttr(String name, HttpServletRequest request, long defaultValue) {
		Long val = (Long) request.getAttribute(name);
		return val == null ? defaultValue : val;
	}

	static String getStringAttr(String name, HttpServletRequest request, String defaultValue) {
		String val = (String) request.getAttribute(name);
		return val == null ? defaultValue : val;
	}

	static boolean getBooleanAttr(String name, HttpServletRequest request, boolean defaultValue) {
		Boolean val = (Boolean) request.getAttribute(name);
		return val == null ? defaultValue : val;
	}

	static Object getObjectAttr(String name, HttpServletRequest request, Object defaultValue){
		Object val = request.getAttribute(name);
		return val == null? defaultValue:val;
	}

	static Integer getIntAttr(String name, HttpServletRequest request) {
		return (Integer) request.getAttribute(name);
	}

	static Long getLongAttr(String name, HttpServletRequest request) {
		return (Long) request.getAttribute(name);
	}

	static String getStringAttr(String name, HttpServletRequest request) {
		return (String) request.getAttribute(name);
	}

	static Boolean getBooleanAttr(String name, HttpServletRequest request) {
		return (Boolean) request.getAttribute(name);
	}

	static Object getObjectAttr(String name, HttpServletRequest request){
		return request.getAttribute(name);
	}


	static void setIntAttr(String name, int value, HttpServletRequest request) {
		request.setAttribute(name, value);
	}

	static void setLongAttr(String name, long value, HttpServletRequest request) {
		request.setAttribute(name, value);
	}

	static void setStringAttr(String name, String value, HttpServletRequest request) {
		request.setAttribute(name, value);
	}

	static void setBooleanAttr(String name, boolean value, HttpServletRequest request) {
		request.setAttribute(name, value);
	}

	static void setObjectAttr(String name, Object value, HttpServletRequest request){
		request.setAttribute(name, value);
	}


	public static int getIntRequest(String name, HttpServletRequest request, int defaultValue) {
		Integer val = null;
		if((val = getIntAttr(name, request)) != null){
			return val;
		}
		return getIntParam(name, request, defaultValue);
	}

	public static long getLongRequest(String name, HttpServletRequest request, long defaultValue) {
		Long val = null;
		if((val = getLongAttr(name, request)) != null){
			return val;
		}
		return getLongParam(name, request, defaultValue);
	}

	/**
	 * 加入过滤字符
	 * @param name
	 * @param request
	 * @param defaultValue
	 * @return
	 */
	public static String getStringRequest(String name, HttpServletRequest request, String defaultValue) {
		String val = null;
		if((val = getStringAttr(name, request)) != null){
			return val;
		}
		val = getStringParam(name, request, defaultValue);
		return val;
		//暂时不用filter类防止xss攻击，页面显示层使用c:out来保证防止攻击。
		//return StringUtil.filterInput(val, false);
	}

	public static boolean getBooleanRequest(String name, HttpServletRequest request, boolean defaultValue) {
		Boolean val = null;
		if((val = getBooleanAttr(name, request)) != null){
			return val;
		}
		return getBooleanParam(name, request, defaultValue);
	}

	public static Object getObjectRequest(String name, HttpServletRequest request, Object defaultValue){
		return getObjectAttr(name, request, defaultValue);
	}

	public static int getIntRequest(String name, HttpServletRequest request) {
		return getIntRequest(name, request, false);
	}

	public static int getIntRequest(String name, HttpServletRequest request, boolean failOnNull) {
		Integer val = null;
		if((val = getIntAttr(name, request)) != null){
			return val;
		}
		val = getIntParam(name, request);
		if(val == null && failOnNull){
//			throw new RequestDataMissingException("Integer data["+name+"]missing!");
		}
		return val;
	}

	public static long getLongRequest(String name, HttpServletRequest request) {
		return getLongRequest(name, request, false);
	}

	public static long getLongRequest(String name, HttpServletRequest request, boolean failOnNull) {
		Long val = null;
		if((val = getLongAttr(name, request)) != null){
			return val;
		}
		val = getLongParam(name, request);
		if(val == null && failOnNull){
//			throw new RequestDataMissingException("Long data["+name+"]missing!");
		}
		return val;
	}

	public static String getStringRequest(String name, HttpServletRequest request) {
		return getStringRequest(name, request, false);
	}

	public static String getStringRequest(String name, HttpServletRequest request, boolean failOnNull) {
		String val = null;
		if((val = getStringAttr(name, request)) != null){
			return val;
		}
		val = getStringParam(name, request);
		if(val == null && failOnNull){
//			throw new RequestDataMissingException("String data["+name+"]missing!");
		}
		return val;
	}

	public static boolean getBooleanRequest(String name, HttpServletRequest request) {
		return getBooleanRequest(name, request, 0);
	}

	public static boolean getBooleanRequest(String name, HttpServletRequest request, int failOnNull) {
		Boolean val = null;
		if((val = getBooleanAttr(name, request)) != null){
			return val;
		}
		val = getBooleanParam(name, request);
		if(val == null && failOnNull == 1){
//			throw new RequestDataMissingException("Boolean data["+name+"]missing!");
		}
		return val;
	}

	public static Object getObjectRequest(String name, HttpServletRequest request){
		return getObjectRequest(name, request, false);
	}

	public static Object getObjectRequest(String name, HttpServletRequest request, boolean failOnNull){
		Object val = getObjectAttr(name, request);
		if(val == null && failOnNull){
//			throw new RequestDataMissingException("Object data["+name+"]missing!");
		}
		return val;
	}

	public static void setIntRequest(String name, int value, HttpServletRequest request) {
		request.setAttribute(name, value);
	}

	public static void setLongRequest(String name, long value, HttpServletRequest request) {
		request.setAttribute(name, value);
	}

	public static void setBooleanRequest(String name, boolean value, HttpServletRequest request) {
		request.setAttribute(name, value);
	}

	public static void setStringRequest(String name, String value, HttpServletRequest request) {
		request.setAttribute(name, value);
	}

	public static void setObjectRequest(String name, Object value, HttpServletRequest request){
		request.setAttribute(name, value);
	}


	protected static int getIntSession(String name, HttpSession session, int defaultValue) {
		Integer val = (Integer) session.getAttribute(name);
		return val == null ? defaultValue : val;
	}

	protected static long getLongSession(String name, HttpSession session, long defaultValue) {
		Long val = (Long) session.getAttribute(name);
		return val == null ? defaultValue : val;
	}

	protected static String getStringSession(String name, HttpSession session, String defaultValue) {
		String val = (String) session.getAttribute(name);
		return val == null ? defaultValue : val;
	}

	protected static Object getObjectSession(String name, HttpSession session, Object defaultValue){
		Object val = session.getAttribute(name);
		return val == null? defaultValue:val;
	}

	protected static void setIntSession(String name, int value, HttpSession session) {
		session.setAttribute(name, value);
	}

	protected static void setLongSession(String name, long value, HttpSession session) {
		session.setAttribute(name, value);
	}

	protected static void setStringSession(String name, String value, HttpSession session) {
		session.setAttribute(name, value);
	}

	protected static void setObjectSession(String name, Object value, HttpSession session){
		session.setAttribute(name, value);
	}

	
}
