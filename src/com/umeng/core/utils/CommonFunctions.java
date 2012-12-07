package com.umeng.core.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class CommonFunctions {
	public static Map<String, Object> dateTypeFormat(int dateType, Map<String, Object> param) {
        String endDate = DateUtil.today();
        String startDate = endDate;
        switch (dateType) {
        case 2: // 昨天
            startDate = DateUtil.dateplus(endDate, -1);
            endDate = startDate;
            break;
        case 7: // 最近7天
            startDate = DateUtil.dateplus(endDate, -6);
            break;
        case 0: // 全部，就是没有时间条件
            param.remove("startDate");
            param.remove("endDate");
            break;
        case 100: // 自定义时间段
            startDate = param.get("startDate").toString();
            endDate = param.get("endDate").toString();
            break;
        }
        if (dateType != 0) {
        	
            if (StringUtils.isEmpty(startDate)) {
                param.put("startDate", DateUtil.dateplus(DateUtil.today(), 36500)
                        + " 00:00:00");// 往前推100年
            } else {
                param.put("startDate", startDate + " 00:00:00");
            }
            if (StringUtils.isEmpty(endDate)) {
                param.put("endDate", DateUtil.dateplus(DateUtil.today(), -36500) + " 23:59:59");// 往后推100年
            } else {
                param.put("endDate", endDate + " 23:59:59");
            }
        }
        param.put("dateType", dateType); // 页面用
        return param;
    }

}
