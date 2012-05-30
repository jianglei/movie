package com.umeng.ufp.publisher.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.publisher.service.AdContentService;
import com.umeng.ufp.publisher.service.AdHolderService;
import com.umeng.ufp.publisher.service.AdOrderService;
import com.umeng.ufp.publisher.service.AdService;
import com.umeng.ufp.publisher.service.AdSlotService;
import com.umeng.ufp.publisher.service.AppService;
import com.umeng.ufp.publisher.service.BackendService;
import com.umeng.ufp.publisher.service.ReportCounterService;
import com.umeng.ufp.publisher.service.UserActionLogService;
import com.umeng.ufp.publisher.service.UserOpLogService;
import com.umeng.ufp.publisher.service.UserService;
import com.umeng.ufp.publisher.service.XPWebService;
import com.umeng.ufp.publisher.utils.SessionConstant;
import com.umeng.ufp.publisher.utils.SessionUtil;

@Controller
public class BaseController<T, PK> extends MultiActionController {
	@Resource
	protected UserService userService;
	@Resource
	protected AppService appService;
	@Resource
	protected AdSlotService adSlotService;
	@Resource
	protected AdService adService;
	@Resource
	protected AdOrderService adOrderService;
	@Resource
	protected AdHolderService adHolderService;
	@Resource
	protected AdContentService adContentService;
	@Resource
	protected ReportCounterService reportCounterService;
	@Resource
	protected XPWebService xpWebService;
	@Resource 
	protected UserActionLogService userActionLogService;
	@Resource 
	protected UserOpLogService userOpLogService;
	@Resource 
	protected BackendService backendService;
	
	
	protected User getLoginUser(HttpServletRequest request){
		Object obj = SessionUtil.getSessionAttribute(request, SessionConstant.SESSION_USER);
		User user = null;
		if(obj != null) 
			user = (User) obj;
		return user;
	}
	
    @InitBinder  
    protected void initBinder(HttpServletRequest request,  
            ServletRequestDataBinder binder) throws Exception {  
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            CustomDateEditor editor = new CustomDateEditor(df, true);  
            binder.registerCustomEditor(Date.class, editor);  
    }  

}
