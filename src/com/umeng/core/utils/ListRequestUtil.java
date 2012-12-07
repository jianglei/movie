package com.umeng.core.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 各类列表页面请求处理工具类
 * @author dell
 *
 */
public class ListRequestUtil {
    private static SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    public static Map<String, Object> getDateRangeParams(HttpServletRequest request){
        /*
         * 时间范围参数
         */
        String dateType="7days";
        if(request.getParameter("dateType")!=null){
            dateType=request.getParameter("dateType");
        }
        String startDate=request.getParameter("startDate");
        String endDate=request.getParameter("endDate");
        return getDateRangeParams(dateType, startDate, endDate);
    }
    public static Map<String, Object> getDateRangeParams(String dateType, String startDate, String endDate){
        if(dateType==null){
            dateType="Yesterday";
        }
        Date start=null;
        Date end=null;
        GregorianCalendar cal = new GregorianCalendar();        
        if(dateType.equals("Custom")){
            start=df.parse(startDate, new ParsePosition(0));
            end=df.parse(endDate, new ParsePosition(0));
        }else{
            cal.setTimeInMillis(System.currentTimeMillis());
            if(dateType.equals("Yesterday")){
                cal.add(GregorianCalendar.DAY_OF_MONTH, -1);
                start=cal.getTime();
                end=cal.getTime();
            }else if(dateType.equals("Today")){
                start=cal.getTime();
                end=cal.getTime();
            }else if(dateType.equals("7days")){
                end=cal.getTime();
                cal.add(GregorianCalendar.DAY_OF_MONTH, -6);                
                start=cal.getTime();
            }else if(dateType.equals("Month")){
                end=cal.getTime();
                cal.add(GregorianCalendar.MONTH, -1);                
                start=cal.getTime();
            }
        }
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("dateType", dateType);
        params.put("startDate", df.format(start));
        params.put("endDate", df.format(end));
        return params;
    }
    /**
     * 合并项目数据（推广计划、广告组、广告创意）列表与统计信息列表
     * @param list
     * @param statistics
     * @return
     */
    public static List<Map<String, Object>> mergeStatisticDatas(List<Map<String, Object>> list, List<Map<String, Object>> statistics){
        return mergeStatisticDatas(list, statistics, "id");
    }
    /**
     * 合并项目数据（推广计划、广告组、广告创意）列表与统计信息列表
     * @param list
     * @param statistics
     * @param matchKey
     * @return
     */
    public static List<Map<String, Object>> mergeStatisticDatas(List<Map<String, Object>> list, List<Map<String, Object>> statistics,String matchKey){
        Map<Object, Map<String, Object>> workmap=new HashMap<Object, Map<String, Object>>();
        for(Map<String, Object> record : list){
            workmap.put(record.get(matchKey), record);
        }
        for(Map<String, Object> statistic : statistics){
            Map<String, Object> record=workmap.get(statistic.get(matchKey));
            if(record!=null){
                record.putAll(statistic);
            }
        }
        return list;
    }
}
