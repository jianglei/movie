package com.umeng.ufp.publisher.aop;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.umeng.core.utils.StringUtil;
import com.umeng.ufp.publisher.utils.SessionConstant;
import com.umeng.ufp.publisher.utils.SessionUtil;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
	private Logger log = Logger.getLogger(SecurityInterceptor.class);
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Object o = SessionUtil.getSessionAttribute(request, SessionConstant.SESSION_USER);
		String uri = request.getRequestURI();
		String querystr = request.getQueryString();
		log.debug(uri);
		if(null == o && needLogin(uri)){
			String returl = uri;
			if(StringUtil.isNotEmpty(querystr)){
				returl +="?"+querystr;
			}
			if(request.getHeader("X-Requested-With") == null){
				response.sendRedirect("/login?returl=" + URLEncoder.encode(returl, "UTF-8"));
				return false;
			}else{

				String data = "{\"status\",\"login\"}";
		    	response.setStatus(200);
		    	OutputStream out = response.getOutputStream();
		    	out.write(data.getBytes("UTF-8"));
				return false;
			}
			
		}
		return super.preHandle(request, response, handler);
	}
	
	public boolean needLogin(String uri){
		if(uri.startsWith("/favicon.ico") || uri.startsWith("/public/") || uri.startsWith("/api/") ||
		   uri.startsWith("/login") || uri.startsWith("/creativeMove") 
		        || uri.startsWith("/js") || uri.startsWith("/images")  || uri.startsWith("/imgs") 
		        || uri.startsWith("/css") || uri.startsWith("/prod") 
		        || uri.startsWith("/contact") || uri.startsWith("/help")
		        || uri.startsWith("/news") || uri.startsWith("/randImg") || "/".equals(uri)){
			return false;
		}
		//TODO: for test
		return true;
	}

}
