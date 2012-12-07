package com.umeng.core.utils;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;

/**
 * 提供一些公用的方法
 * remove to StringUtil, RequestUtil...
 * 
 */
@Deprecated
public class BaseUtils {

    public static final int DEFAULT_COOKIE_TIME = 30 * 24 * 60 * 60;

    public static boolean containElement(List<String> strList, String str) {
        for (String item : strList) {
            if (item.equals(str)) {
                return true;
            }
        }
        return false;
    }


    public static Map<String, Object> createQueryParam(String keys, Object... values) {
        Map<String, Object> param = new HashMap<String, Object>();
        Assert.hasText(keys);
        String[] keySet = keys.split(",");
        int valueIndex = 0;
        for (String key : keySet) {
            param.put(key.trim(), values[valueIndex]);
            valueIndex++;
        }
        return param;
    }

    /**
     * 中文参数解码
     * 
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    @Deprecated
    public static String decode(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Deprecated
    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定的cookie内容
     * 
     * @param cookies
     * @param name
     * @return
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return "";
    }

    public static Date getDateFromUnix(long stmp) {
        return new Date(stmp * 1000);
    }

    public static Date getDateFromUnix(String stmp) {
        return getDateFromUnix(Long.parseLong(stmp));
    }

    /**
     * 获取ip
     * 
     * @param req
     * @return
     */
    @Deprecated
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

    public static Map<String, Object> getMapParam(String key, Object value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(key, value);
        return param;
    }

    public static boolean isEmpty(Collection<?> co) {
        if (co != null && !co.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isEmpty(Map<?, ?> map) {
        if (map != null && map.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断1个char是否是数字或者小数点
     * 
     * @param c
     * @return
     */
    public static boolean isNumberOrPoint(char c) {
        if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6'
                || c == '7' || c == '8' || c == '9' || c == '.') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断1个char是否是标点符号
     * 
     * @param c
     * @return
     */
    public static boolean isSymbol(char c) {
        if (c == ',' || c == '.' || c == '?' || c == '!' || c == '"' || c == ';' || c == '，'
                || c == '。' || c == '？' || c == '！' || c == '“' || c == '；') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean notEmpty(Collection<?> co) {
        return !isEmpty(co);
    }

    public static boolean notEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static void responsewrite(HttpServletResponse res, String content) throws IOException {
        responsewrite(res, content, "utf-8");
    }

    /**
     * json输出
     * 
     * @param res
     * @param content
     * @param charset
     * @throws IOException
     */
    public static void responsewrite(HttpServletResponse res, String content, String charset)
            throws IOException {
        if (null == charset || charset.trim().length() == 0) {
            charset = "utf-8";
        }
        res.setContentType("text/html;charset=" + charset);
        PrintWriter pw = res.getWriter();
        pw.write(content);
        pw.flush();
        pw.close();
    }

    public static void setCookie(HttpServletResponse response, String name, String value) {
        Integer expireTime = DEFAULT_COOKIE_TIME;
        setCookie(response, name, value, expireTime);
    }

    public static void setCookie(HttpServletResponse response, String name, String value,
            Integer expireTime) {
        if (isBlank(name) || null == expireTime) {
            return;
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(expireTime);
        response.addCookie(cookie);
    }

    public static List<Integer> str2list(String str) {
        String s_str = str;
        if (str.endsWith(",")) {
            s_str = s_str.substring(0, s_str.length() - 1);
        }
        if (str.startsWith(",")) {
            s_str = s_str.substring(1, s_str.length());
        }
        String[] str_ids = s_str.split(",");
        List<Integer> listids = new ArrayList<Integer>(str_ids.length);
        for (String id : str_ids) {
            listids.add(Integer.parseInt(id));
        }
        return listids;
    }

    public static String toEscapeSign(String str) {
        return str.replace("+", "{plus}").replace("&", "{and}");
    }

    public static String toOriginalSign(String str) {
        return str.replace("{plus}", "+").replace("{and}", "&");
    }

    public static boolean isNULL(Object obj) {

		if (obj == null) {
			return true;
		}
		return false;
	}

}
