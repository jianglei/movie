package com.wuxianmeihao.utils;


import java.util.Date;
import java.util.Random;
import java.util.regex.*;

public class StringUtil {

    /**
     * 处理字符串
     * 
     * @param str
     * @return "" || str
     */
    public static String delSpecialChar(String str) {
        if (str == null) {
            return "";
        } else {
            return str.trim().replaceAll("[%&'',，；’。‘、;=?]*", "");
        }
    }

    public static boolean isEmpty(String s) {

        if (s == null || s.trim().equals("")) {
            return true;
        }
        return false;
    }
    
    public static boolean isEmpty(Object s) {

        if (s == null || s.toString().trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String s) {

        if (s == null || s.trim().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 清除掉所有特殊字符
     * 
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        String regEx = "[`~!@$%^&*()+=|{}':;',\\[\\].<>《》/?~！@￥%……&*（）——+|{}【】‘；：\"”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    public static String truncate(String str, int maxLen){
        str=(str==null) ? "" : str;
        str=str.trim();
        if(str.length()<=maxLen){
            return str;
        }else{
            return str.substring(0, maxLen+1)+"...";
        }
    }

    public static String uuid(){
    	StringBuffer sb = new StringBuffer();
    	Date date = new Date();
		String timestr = Long.toHexString(date.getTime());
		timestr = timestr.substring(timestr.length() - 8);
		sb.append(Integer
				.toHexString((-1 - new Random().nextInt(0xEfff)) & 0xffff));
		sb.append(timestr);
		sb.append(Integer
				.toHexString((-1 - new Random().nextInt(0xEfff)) & 0xffff));
    	return sb.toString();
    }
    public static void main(String[] args){
    	System.out.println(uuid());
    }
}

