package com.umeng.ufp.publisher.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.umeng.core.utils.Page;
import com.umeng.core.utils.PageUtil;
import com.umeng.core.utils.RequestUtil;

public class ListRequestUtil {
    public static <T> Page<T> getListRequestPage(HttpServletRequest request){
    	//TODO: not use page now!!! 1000
    	int pageSize = RequestUtil.getIntParam("pageSize", request, 10);
        return getListRequestPage(request, "Today", pageSize);
    }
    public static <T> Page<T> getListRequestPage(HttpServletRequest request, int pageSize){
        return getListRequestPage(request, "Today", pageSize);
    }
    public static <T> Page<T> getListRequestPage(HttpServletRequest request, String defaultDateType, int pageSize){
        Page<T> page=PageUtil.newPageCriterion(request);
        page.setPageSize(Integer.valueOf(pageSize));
        Map<String, Object> params=getListRequestParams(request, defaultDateType);
        page.getParam().putAll(params);
        return page;
    }    
    public static Map<String, Object> getListRequestParams(HttpServletRequest request){
        return getListRequestParams(request, "Today");
    }
    public static Map<String, Object> getListRequestParams(HttpServletRequest request, String defaultDateType){
        Map<String, Object> params=ListRequestUtil.getDateRangeParams(request, defaultDateType);
        String[] status=request.getParameterValues("status");
        if (status != null && status.length > 0){
        	if (status[0].contains(",")) {
        		status=status[0].split(",");
        	}

        	params.put("status", status);
        }
        String[] platform = request.getParameterValues("platform");
        //当前只有两种类型的平台，如果两种平台都传入，就不需要这个过滤条件了
        if(platform!=null && platform.length == 1 ){
        	params.put("platform", platform[0]);
        }
        return params;
    }    
    public static Map<String, Object> getDateRangeParams(HttpServletRequest request){
        return getDateRangeParams(request, "Today");
    }
    public static Map<String, Object> getDateRangeParams(HttpServletRequest request, String defaultDateType){
        /*
         * 时间范围参数
         */
        String dateType=defaultDateType;
        if(request.getParameter("dateType")!=null){
            dateType=request.getParameter("dateType");
        }
        String startDate=request.getParameter("startDate");
        String endDate=request.getParameter("endDate");
        return com.umeng.core.utils.ListRequestUtil.getDateRangeParams(dateType, startDate, endDate);
    }
    
	public static List<Map<String, Object>> convertToAbsoluateURL(HttpServletRequest req, List<Map<String, Object>> list, String urlKeywords, String separator, String urlPrefix){

		if(list != null) {
			urlPrefix = (urlPrefix == null ?  RequestUtil.getServerURL(req) : urlPrefix);
			for (Map<String, Object> map : list){
				for (Entry<String, Object> mapEntry : map.entrySet()){
					if (mapEntry.getValue() instanceof String){
						String v = (String) mapEntry.getValue();
						
						if (v.startsWith(urlKeywords)) {
							if (v.indexOf(separator) == -1){
								mapEntry.setValue(urlPrefix + v);
							}else{
								List<String> list2 = new ArrayList<String>();
								for (String s : v.split(separator)){
									list2.add(urlPrefix + s);
								}
								
								mapEntry.setValue(StringUtils.join(list2, separator));
							}
							
						}
					}
				}
			}
		}
		
		return list;
	}
	    
//    public static List<Map<String, Object>> mediaShowDiscount(List<Map<String, Object>> list, Map<String, Object> media){
//        Integer discount=(Integer)media.get("discount");
//        Integer cost_per=(Integer)media.get("const_per");
//        if(discount!=null && discount<100){
//            for(Map<String, Object> record : list){
//                if(record.get("show")!=null){
//                    Long show=(Long)record.get("show")*discount/100;
//                    record.put("show", show);
//                }
//            }
//            if(cost_per!=null && cost_per.intValue()==2){
//                for(Map<String, Object> record : list){
//                    if(record.get("income")!=null){
//                        Long income=(Long)record.get("income")*discount/100;
//                        record.put("income", income);
//                    }
//                }
//            }
//        }
//
//        return list;
//    }
//    public static Map<String, Object> mediaShowDiscount(Map<String, Object> map, Map<String, Object> media){
//        List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
//        list.add(map);
//        list=mediaShowDiscount(list, media);
//        return list.get(0);
//    }
}
