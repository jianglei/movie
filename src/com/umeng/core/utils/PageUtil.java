package com.umeng.core.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class PageUtil {
	/**
	 * 提取request中的分页数据，构造用于查询的Page对象。
	 * @param <T>
	 * @param request
	 * @return
	 */
	public static <T> Page<T> newPageCriterion(HttpServletRequest request){
    	String pageNoStr=request.getParameter("pageNo");
    	int pageNo=pageNoStr==null ? 1 : Integer.valueOf(pageNoStr);
    	String pageSizeStr=request.getParameter("pageSize");
    	int pageSize=pageSizeStr==null ? Page.defaultPageSize : Integer.valueOf(pageSizeStr);
    	
    	Page<T> page=new Page<T>(pageNo, pageSize);
    	page.setRequestUrl(request.getRequestURL().toString());
    	return page;
	}
	/**
	 * 从page中获取查询参数，用于构造查询代码 
	 * @param <T>
	 * @param p
	 * @return
	 */
    public static <T> Map<String, Object> toParameterMap(Page<T> p) {
        Map<String, Object> map = BaseUtils.createQueryParam("offset, limit", p.getOffset(), p.getPageSize());
        if (StringUtils.isNotBlank(p.getOrderBy())) {
            map.put("orderBy", p.getOrderBy());
        }
        if (StringUtils.isNotBlank(p.getOrder())) {
            map.put("order", p.getOrder());
        }
        if (BaseUtils.notEmpty(p.getParam())) {
            map.putAll(p.getParam());
        }
        return map;
    }
    public static Map<String, Object> getRequestParamMap(HttpServletRequest request, String[] names){
        Map<String, Object> params=new HashMap<String, Object>();
        for(String name : names){
            if(request.getParameter(name)!=null){
                params.put(name, request.getParameter(name));
            }
        }
        return params;
    }
}
