package com.umeng.ufp.publisher.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.umeng.ufp.core.domain.User;


@Controller
@RequestMapping(value = "/support")
public class HelperController extends BaseController<User, Integer>{

    public static final String ALLOWED_LOGIN_ADDR = "adpm";
    private Logger log = Logger.getLogger(getClass());

    @RequestMapping(value = "docs/landingtype")
    public String doc(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
    	User user = getLoginUser(request);
    	if(user != null) 
    		return "docs/landingtype_docs";
    	else
    		return "login";
    }
    @RequestMapping(value = "docs/channel")
    public String channel(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
    	User user = getLoginUser(request);
    	if(user != null) 
    		return "docs/channel_docs";
    	else
    		return "login";
    }
    
    @RequestMapping(value = "docs/template")
    public String template(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
    	User user = getLoginUser(request);
    	if(user != null) 
    		return "docs/template_docs";
    	else
    		return "login";
    }
}
 