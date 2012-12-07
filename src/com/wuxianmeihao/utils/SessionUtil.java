package com.wuxianmeihao.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

public class SessionUtil {
	public static Object getSessionAttribute(HttpServletRequest request,String name){
		return WebUtils.getSessionAttribute(request, name);
	}
	public static void setSessionAttribute(HttpServletRequest request,String name,Object value){
		WebUtils.setSessionAttribute(request, name, value);
	}
	
	public static void clearSession(HttpServletRequest request){
		request.getSession().invalidate();
	}
	
	public static boolean containsKey(HttpServletRequest request,String name){
		Object obj = getSessionAttribute(request,name);
		if(null==obj) return false;
		return true;
	}

}
