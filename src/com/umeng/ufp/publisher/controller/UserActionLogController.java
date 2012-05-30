package com.umeng.ufp.publisher.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.umeng.core.utils.DateUtil;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.core.domain.UserActionLog;

@Controller
@RequestMapping(value = "/history")
public class UserActionLogController extends BaseController<UserActionLog, Integer> {

    public static final String ALLOWED_LOGIN_ADDR = "adpm";
    
    private Logger log = Logger.getLogger(getClass());

    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
        return "history/list";
    }

    @RequestMapping(value = "log")
    @ResponseBody
    public Map<String,Object> totalList(HttpServletRequest request, 
    		HttpServletResponse response, 
    		@RequestParam(value="opType", required = false) String opType,
    		@RequestParam(value="objectType", required = false) Integer objectType,
    		@RequestParam(value="startDate", required = false) String startDate,
    		@RequestParam(value="endDate", required = false) String endDate,
    		@RequestParam(value="period", required = false) String period,
    		Model model) {
    	User user = getLoginUser(request);
    	Map<String, Object> resultParams = new HashMap<String, Object>();
    	if(user != null) {
	    	try {
	        	Map<String, Object> extraParam = new HashMap<String, Object>();
	        	extraParam.put("opType", opType);
	        	extraParam.put("objectType", objectType);
	        	Map<String, Object> date = getDate(startDate, endDate, period);
	        	startDate = (String)date.get("startDate");
				endDate = (String)date.get("endDate");
				
	        	List<UserActionLog> actionLogList = userActionLogService.findActionLog(user.getId(), startDate, endDate, extraParam);
	        	resultParams.put("actionLogList", actionLogList);
	        	resultParams.put("status", "ok");
    		} catch (Exception e) {
    			resultParams.put("status", "failed");
    			resultParams.put("message", e.getMessage());
    		}

    	}
    	return resultParams;
    }
    
    private Map<String, Object> getPeriodDate(String period) {
    	
		if(period == null) return null;
		String startDate = new String();
    	String endDate = new String();
    	
    	Map<String, Object> param = new HashMap<String, Object>();
		String today = DateUtil.today();
		
    	if(period.equals("today")) {
    		startDate = today;
    		endDate = today;
    	} else if (period.equals("yesterday")) {
    		endDate = DateUtil.dateplus(today, 1);
    		startDate = DateUtil.dateplus(today, 1);
    	} else if (period.equals("lastWeek")) {
    		startDate = DateUtil.getLastWeekDay(today, 1);	
    		endDate = DateUtil.getLastWeekDay(today, 7);
    	} else if (period.equals("lastMonth")) {
    		endDate = DateUtil.getLastMonthLastDate(today);
    		startDate = DateUtil.getLastMonthFirstDate(today);
    	} else if (period.equals("lastQuarter")) {
    		startDate = DateUtil.getLastQuarterFirstDate(today);
    		endDate = DateUtil.getLastQuarterLastDate(today);
    	} else {
    		return null;
    	}
    	param.put("startDate", startDate);
    	param.put("endDate", endDate);
		return param;
    	
    }
    private Integer getPeriodDay(String period) {

    	Integer day = null;
    	if(period == null)
    		return null;
    	
    	if (period.startsWith("day_")) {
    		day = Integer.parseInt(period.substring(4));
    	} else if (period.startsWith("month_")) {
    		day = Integer.parseInt(period.substring(6)) * 30;
    	}
    	
    	return day;
    	
    }
    
    private Map<String,Object> getDate(String startDate, String endDate, String period) {
    	Map<String, Object> param = new HashMap<String, Object>();
    		
    	Integer day = getPeriodDay(period);

    	if(startDate != null && endDate == null) {
    		if(day == null || day < 0)
    			endDate = DateUtil.dateplus(startDate, -6);
    		else
    			endDate = DateUtil.dateplus(startDate, -day + 1); 			
    	} else if(startDate == null && endDate != null) {
    		if(day == null || day > 0) 
    			startDate = DateUtil.dateplus(endDate, 6);
    		else
    			startDate = DateUtil.dateplus(endDate, -day - 1);	
    	} else if(startDate == null && endDate == null) {	
    		if(day == null) {
    			Map<String, Object> date = getPeriodDate(period);
    			if(date!= null) {
    				startDate = (String)date.get("startDate");
    				endDate = (String)date.get("endDate");
    			} else {
    				endDate = DateUtil.dateplus(DateUtil.getDateString(), 1);
    				startDate = DateUtil.dateplus(endDate, 6);
    			}
    		} else if(day > 0) {
	    		endDate = DateUtil.getDateString();
	    		startDate = DateUtil.dateplus(endDate, 6);
    		} else {
    			endDate = DateUtil.dateplus(DateUtil.getDateString(), 1);
    			startDate = DateUtil.dateplus(endDate, -day - 1);
    		}
    	} else if(startDate.compareTo(endDate) > 0) {
    		startDate = DateUtil.dateplus(endDate, 6);
    	}
    	param.put("startDate", startDate);
    	param.put("endDate", endDate);
		return param;
    }
 

}
