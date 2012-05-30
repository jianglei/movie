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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.core.domain.validator.UserValidator;
import com.umeng.ufp.publisher.utils.SessionConstant;
import com.umeng.ufp.publisher.utils.SessionUtil;

@Controller
@RequestMapping(value = "/settings")
public class SettingController extends BaseController<User, Integer> {

	public static final String ALLOWED_LOGIN_ADDR = "adpm";
	private Logger log = Logger.getLogger(getClass());
    private UserValidator userValidator = new UserValidator();

	@RequestMapping
	public String admin(HttpServletRequest req, HttpServletResponse res,
			Model model) {
		return "/settings/edit_user_info";
	}

	@RequestMapping(value = "edit_user_info")
	public String edit(HttpServletRequest req, HttpServletResponse res,
			Model model) {
		User user = this.getLoginUser(req);
		model.addAttribute("user", user);
		return "/settings/edit_user_info";
	}
	
	@RequestMapping(value = "edit_black_list")
	public String editBlacklist(HttpServletRequest req, HttpServletResponse res,
			Model model) {
		User user = this.getLoginUser(req);
		model.addAttribute("user", user);
		return "/settings/edit_black_list"; 
	}

	@RequestMapping(value = "edit_password")
	public String editPassword(HttpServletRequest req, HttpServletResponse res,
			Model model) {
		User user = this.getLoginUser(req);
		model.addAttribute("user", user);
		return "/settings/edit_password";
	}
	
	/**
     * 
     */
	@RequestMapping(value = "edit_logo")
	public String editLogo(HttpServletRequest req, HttpServletResponse res,Model model)
			throws IOException {
		User user = this.getLoginUser(req);
		model.addAttribute("user", user);
		return "/settings/edit_logo";
	}

	/**
     * 
     */
	@RequestMapping(value = "update_user_info")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest req, HttpServletResponse res,
			final @ModelAttribute("command") User user, Model model)
			throws IOException {
		User loginUser = getLoginUser(req);
		Map<String, Object> resultParams = new HashMap<String, Object>();
		
		if(loginUser != null) {
			try {
				user.setId(loginUser.getId());
				//1.read oldvalue
    			String opType = "edit";
    			User oldUser = userService.getById(user.getId());
    			//2. save user info
				userService.saveOrUpdate(user);
				//3. read newvalue
    			User newUser = userService.getById(user.getId());
	    		//4. send user action log
	    		userActionLogService.sendLog(user.getId(), user.getId(), oldUser, newUser, opType);
	    		
				resultParams.put("status", "ok");
				User updatedUser = userService.getById(user.getId());
				SessionUtil.setSessionAttribute(req, SessionConstant.SESSION_USER, updatedUser);
			} catch(Exception e) {
				resultParams.put("status", "failed");
				resultParams.put("message", e.getMessage());
			}
		}
		return resultParams;
	}
	/**
     * 
     */
	@RequestMapping(value = "update_user_password")
	@ResponseBody
	public Map<String, Object> save_password(HttpServletRequest req,
			HttpServletResponse res, Model model) throws IOException {
		
		Map<String, Object> resultParams = new HashMap<String, Object>();
		
		User user = getLoginUser(req);
		if(user != null) {        	
			try {
				String loginName = user.getEmail();
				String user_current_password = req.getParameter("user_current_password");
				
				if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(user_current_password)) {
					resultParams.put("status", "noinfo");
				} else {
					// passwd = CodeHelper.fromBase64(passwd);
					User exist_user = userService.getUser(loginName, user_current_password);
					if (null != exist_user) {
						String new_password = req.getParameter("user_password");
						if (!StringUtils.isEmpty(new_password)) {
							user.setPassword(new_password);
							
							//1.read oldvalue
			    			String opType = "edit";
			    			User oldUser = userService.getById(user.getId());
			    			//2. save user info
							userService.saveOrUpdate(user);
							//3. read newvalue
			    			User newUser = userService.getById(user.getId());
				    		//4.send user action log
				    		userActionLogService.sendLog(user.getId(), user.getId(), oldUser, newUser, opType);
				    		
							resultParams.put("status", "ok");
						}
					} else {
						resultParams.put("status", "failed");
					}
				}
			} catch(Exception e) {
				resultParams.put("status", "failed");
				resultParams.put("message", e.getMessage());
			}
		}
		return resultParams;
	}

}
