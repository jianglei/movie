package com.umeng.ufp.publisher.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.umeng.core.utils.StringUtil;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.core.domain.UserActionLog;
import com.umeng.ufp.core.domain.UserOpLog;
import com.umeng.ufp.publisher.utils.SessionConstant;
import com.umeng.ufp.publisher.utils.SessionUtil;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController<User, Integer> {

    public static final String ALLOWED_LOGIN_ADDR = "adpm";
    private Logger log = Logger.getLogger(LoginController.class);
    
    @RequestMapping
    public String index(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
    	String returl = req.getParameter("returl");
    	model.addAttribute("returl", returl);
    	log.debug(returl);
    	if(SessionUtil.containsKey(req, SessionConstant.SESSION_USER)){
    		//1. read user from sesseion
    	    User user = getLoginUser(req);
    	    
    	    return doLogin(model, user, returl);
    	}
        return "login";
    }
    
    @ResponseBody
    @RequestMapping(value = "/doAjax")
    public Map<String,String> doAjax(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
    	HashMap<String,String> resultMap = new HashMap<String,String>();
    	resultMap.put("status", "login");
        return resultMap;
    }
    
    @RequestMapping(value = "/doLogin")
    public String login(HttpServletRequest req, HttpServletResponse res,
    		@RequestParam("loginName") String loginName, 
    		@RequestParam("passwd") String passwd, 
    		@RequestParam("returl") String returl, 
    		Model model)
            throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(passwd)) {
            map.put("error", "请填写用户名和密码!");
            model.addAllAttributes(map);
            return "login";
        }
//        passwd = CodeHelper.fromBase64(passwd);
        User user = userService.getUser(loginName, passwd);
        if (null != user) {
            SessionUtil.setSessionAttribute(req, SessionConstant.SESSION_USER, user);
    		
            return doLogin(model, user, returl);
        } else {
            map.put("error", "用户名和密码不正确!");
            model.addAllAttributes(map);
            return "login";
        }
    }
    
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest req, HttpServletResponse res, Model model)
            throws IOException {
    	//User user = getLoginUser(req);
    	SessionUtil.clearSession(req);
		//opLogService.sendLog();
		//cacheService.update();
    	return "redirect:/login";
    }
    private String getStartingUrl(Model model, User user, String returl){
        if(StringUtil.isNotEmpty(returl)){
            return "redirect:"+returl;
        }
         
        return "redirect:/adslot";
    }
    
    private String doLogin(Model model, User user, String returl) {
    	String startUrl=getStartingUrl(model, user, returl);
	    //2.write action log
		userActionLogService.sendLog(user.getId(), user.getId(), user, user, "login");
        //3.write op log
		userOpLogService.sendLog(user, "login");
		return startUrl;
    }
}
