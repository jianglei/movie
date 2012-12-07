package com.umeng.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DateUtil {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfCompact = new SimpleDateFormat("yyyyMMddHHmmss");
	public static SimpleDateFormat sdfDateCompact = new SimpleDateFormat("yyyyMMdd");
	
	public static synchronized long getDateLong(String in){
		long a = 0;
		try {
			a = sdf.parse(in).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return a;
	}
	public static synchronized String getDateString(long in){
		return sdf.format(new Date(in));
	}
	public static synchronized String getDateString(){
		return sdfDate.format(new Date());
	}
	public static synchronized String getDatetimeString(){
		return sdf.format(new Date());
	}
	public static synchronized String getDatetimeCampactStr(){
		return sdfCompact.format(new Date());
	}
	public static synchronized String getDateCampactStr(){
		return sdfDateCompact.format(new Date());
	}
	
	public static String StrDate(String format, Date date){
		java.text.SimpleDateFormat ff = new java.text.SimpleDateFormat();
		ff.applyPattern(format);
		return ff.format(date);
	}
	
	/**
	 * 获得某一日期的前/后n年
	 * @param date
	 * @return Date
	 */
	public static Date getNextNYear(Date date,int n){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int year=calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR,year+n);
		return calendar.getTime();
	}	
	/**
	 * 获得某一日期的后n天
	 * @param date
	 * @return Date
	 */
	public static Date getNextNDate(Date date,int n){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int day=calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE,day+n);
		return calendar.getTime();
	}	
	/**
	 * 获得某一日期的前n天
	 * @param date
	 * @return Date
	 */
	public static Date getPreviousNDate(Date date,int n){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int day=calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE,day-n);
		return calendar.getTime();
	}
	public static Date getFirstDate(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int minDate = calendar.getActualMinimum(Calendar.DATE);
		calendar.set(Calendar.DATE,minDate);
		return calendar.getTime();
	}
	public static Date getLastDate(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int maxDate = calendar.getActualMaximum(Calendar.DATE);
		calendar.set(Calendar.DATE,maxDate);
		return calendar.getTime();
	}
	public static Date getPreviousNMonth(Date date,int n){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH,month-n);
		return calendar.getTime();
	}

	public static Date getPreviousNHour(Date date,int n){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int hour=calendar.get(Calendar.HOUR);
		calendar.set(Calendar.HOUR,hour-n);
		return calendar.getTime();
	}

	public static Date getPreviousNMinute(Date date,int n){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int minute=calendar.get(Calendar.MINUTE);
		calendar.set(Calendar.MINUTE,minute-n);
		return calendar.getTime();
	}

	public static Date getPreviousNSecond(Date date,int n){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int second=calendar.get(Calendar.SECOND);
		calendar.set(Calendar.SECOND,second-n);
		return calendar.getTime();
	}
	public static Date getAppointDate(Date date,int day){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE,day);
		return calendar.getTime();
	}
	public static String date2String(String pattern,Date date){
		String retStr = "";
		java.text.SimpleDateFormat ff = new java.text.SimpleDateFormat();
		ff.applyPattern(pattern);
		retStr = ff.format(date);
		return retStr;
	}
	public static Date string2Date(String pattern,String str){
		Date date =new Date();
		if(StringUtil.isEmpty(str)){
			return date;
		}
		java.text.SimpleDateFormat ff = new java.text.SimpleDateFormat();
		ff.applyPattern(pattern);
		try {
			date = ff.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;		
	}
	
	public static String dateplus(String from,int n){
		Date date = string2Date("yyyy-MM-dd", from);
		Date d = getPreviousNDate(date,n);
		return date2String("yyyy-MM-dd", d);
	}
	public static String date2SimpleStr(java.util.Date date) {
        
        if (date == null) {
            return "";
        } 
        return date2String("yyyy-MM-dd", date);
    }
	public static String dateplus(String from,int n,int type){
		if(from.length()==10){
			from +=" 00:00:00";
		}
		Date date = string2Date("yyyy-MM-dd HH:mm:ss", from);
		Date d = new Date();
		if(type==Calendar.MONTH){
			d = getPreviousNMonth(date,n);
		}else if(type==Calendar.HOUR){
			d = getPreviousNHour(date,n);
		}else if(type==Calendar.MINUTE){
			d = getPreviousNMinute(date,n);
		}else if(type==Calendar.SECOND){
			d = getPreviousNSecond(date,n);
		}else{
			d = getPreviousNDate(date,n);
		}
		return date2String("yyyy-MM-dd HH:mm:ss", d);
	}
	
	/**
	 * 第二个参数减第一个参数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int diff(String date1,String date2){
		if(StringUtil.isEmpty(date1)|| StringUtil.isEmpty(date2)){
			return 0;
		}
		Date d1 = string2Date("yyyy-MM-dd", date1);
		Date d2 = string2Date("yyyy-MM-dd", date2);
		int n=0;
		while(d1.getTime()!=d2.getTime()){
			if(d1.getTime()<d2.getTime()){
				d1 = getNextNDate(d1,1);
				n++;
			}else{
				d1 = getNextNDate(d1,-1);
				n--;
			}
		}
		return n;
	}
	public static Map<String,Integer> getDateDetail(String d){
		Map<String,Integer> ret = new HashMap<String,Integer>();
		if(d.length()==10){
			d +=" 00:00:00";
		}
		Date dd = string2Date("yyyy-MM-dd HH:mm:ss", d);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(dd);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second=calendar.get(Calendar.SECOND);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		ret.put("year", year);
		ret.put("month", month);
		ret.put("date", date);
		ret.put("hour", hour);		
		ret.put("minute", minute);
		ret.put("second", second);
		ret.put("week", week);
		return ret;
	}
	
	/**
	 * 返回有序的连续时间LinkedHashMap，key是时间，value为null
	 * @param from
	 * @param to
	 * @return
	 */
	public static Map dateScopeMap(String from,String to){
		Map map = new LinkedHashMap();
		map.put(from,null);
		if(from.length()==4){
			int i=1;
			Date d = string2Date("yyyy", from);
			while(d.getTime()<string2Date("yyyy", to).getTime()){
				d = getNextNYear(string2Date("yyyy", from), i);
				map.put(date2String("yyyy", d),null);
				i++;
			}
		}else if(from.length()==7){
			int i=1;
			Date d = string2Date("yyyy-MM", from);
			while(d.getTime()<string2Date("yyyy-MM", to).getTime()){
				d = getPreviousNMonth(string2Date("yyyy-MM", from), -i);
				map.put(date2String("yyyy-MM", d),null);
				i++;
			}
		}else{
			int i=1;
			Date d = string2Date("yyyy-MM-dd", from);
			while(d.getTime()<string2Date("yyyy-MM-dd", to).getTime()){
				d = getNextNDate(string2Date("yyyy-MM-dd", from), i);
				map.put(date2String("yyyy-MM-dd", d),null);
				i++;
			}
		}
		return map;
	}
	
	public static List<String> dateScope(String from,String to){
		List<String> list = new ArrayList<String>();
		list.add(from);
		if(from.length()==4){
			int i=1;
			Date d = string2Date("yyyy", from);
			while(d.getTime()<string2Date("yyyy", to).getTime()){
				d = getNextNYear(string2Date("yyyy", from), i);
				list.add(date2String("yyyy", d));
				i++;
			}
		}else if(from.length()==7){
			int i=1;
			Date d = string2Date("yyyy-MM", from);
			while(d.getTime()<string2Date("yyyy-MM", to).getTime()){
				d = getPreviousNMonth(string2Date("yyyy-MM", from), -i);
				list.add(date2String("yyyy-MM", d));
				i++;
			}
		}else{
			int i=1;
			Date d = string2Date("yyyy-MM-dd", from);
			while(d.getTime()<string2Date("yyyy-MM-dd", to).getTime()){
				d = getNextNDate(string2Date("yyyy-MM-dd", from), i);
				list.add(date2String("yyyy-MM-dd", d));
				i++;
			}
		}
		return list;
	}
	
	public static String today(){
		Date date = new Date();
		return date2String("yyyy-MM-dd",date);
	}
	
	public static String getLastWeekDay(String from, int n) {
		Date date = string2Date("yyyy-MM-dd", from);
		Date d = getWeekDayOfDate(date, -1, n);
		return date2String("yyyy-MM-dd", d);	
	}
	public static Date getWeekDayOfDate(Date date, int w, int n) {	
		if(n > 7) n = 7;
		if(n < 0) n = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int x = calendar.get(Calendar.DAY_OF_WEEK);

		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day - x + 1 + w * 7 + n);
		
		return calendar.getTime();	
	}
	
	public static String getLastMonthLastDate(String from) {
		Date date = string2Date("yyyy-MM-dd", from);
	    date = getPreMonthLastDate(date, -1);
		return date2String("yyyy-MM-dd", date);	
	}
	
	public static String getLastMonthFirstDate(String from) {
		Date date = string2Date("yyyy-MM-dd", from);
	    date = getPreMonthFirstDate(date, -1);
		return date2String("yyyy-MM-dd", date);	
	}
	
	public static String getLastQuarterFirstDate(String from) {
		Date date = string2Date("yyyy-MM-dd", from);
	    date = getPreMonthFirstDate(date, -(2 + date.getMonth() % 3 + 1));
		return date2String("yyyy-MM-dd", date);	
	}
	public static String getLastQuarterLastDate(String from) {
		Date date = string2Date("yyyy-MM-dd", from);
	    date = getPreMonthLastDate(date, -(date.getMonth() % 3 + 1));
		return date2String("yyyy-MM-dd", date);	
	}
	
	public static Date getPreMonthFirstDate(Date date, int month) {
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date); 
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DATE, -calendar.get(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
	}
	public static Date getPreMonthLastDate(Date date, int month) {
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(getPreMonthFirstDate(date, month + 1)); 
		calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
	}
	


	public static void main(String [] args){
		/*long time = 1177120564000l;//getDateLong("Sat, 21 Apr 2007 02:43:31 GMT");
		System.out.println(getDateString(time));
		System.out.println(diff("2008-10-01","2008-10-30"));
		System.out.println(diff("2008-10-01","2008-10-01"));*/
		
		
		System.out.println(DateUtil.getDatetimeCampactStr());
	}
}
