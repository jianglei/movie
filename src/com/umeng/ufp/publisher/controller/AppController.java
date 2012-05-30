package com.umeng.ufp.publisher.controller;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.umeng.core.utils.BaseUtils;
import com.umeng.core.utils.ExportUtil;
import com.umeng.core.utils.Page;
import com.umeng.core.utils.StringUtil;
import com.umeng.ufp.core.domain.App;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.core.domain.validator.AppValidator;
import com.umeng.ufp.publisher.utils.Constants;
import com.umeng.ufp.publisher.utils.ListRequestUtil;
import com.umeng.ufp.publisher.utils.StatusUtil;

@Controller
@RequestMapping(value = "/app")
public class AppController extends BaseController<App, Integer> {

    public static final String ALLOWED_LOGIN_ADDR = "adpm";
    private Logger log = Logger.getLogger(AppController.class);
    private AppValidator appValidator = new AppValidator();

    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
        return "app/list";
    }
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String,Object> list(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
    	User user = getLoginUser(request);
		Map<String, Object> resultParams = new HashMap<String, Object>();
		
    	if(user != null) {
    		try {
		        Page<Map<String, Object>> appPage=ListRequestUtil.getListRequestPage(request);
		        appPage.addParam("userId", user.getId());
		        appPage=appService.getAppListByPage(appPage);
				resultParams.put("appPage", appPage);
				resultParams.put("exportUrl", ExportUtil.getCsvRequestUrl(appPage.getCompleteRequestUrl()));
    			resultParams.put("status", "ok");
    		} catch (Exception e) {
    			resultParams.put("status", "failed");
    			resultParams.put("message", e.getMessage());
    		}
    	}
        return resultParams;
    }
    /**
     * TODO : not need page
     */
    @RequestMapping(value = "export")
    public String export(HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Map<String, Object>> page=ListRequestUtil.getListRequestPage(request);
        page=appService.getAppListByPage(page);

        List<String> columnNames=Arrays.asList(new String[]{"name", "platform"});
        try{
            ExportUtil.writeCsvOutput(response, page.getResult(), columnNames,"app_list.csv");   
        }catch(IOException e){
            //error page?
        }
        return null;
    }    

    @RequestMapping(value = "add")
    public String add(HttpServletRequest req, HttpServletResponse res,
            final SessionStatus status, Model model) {
        return "app/add";
    }
    
    @RequestMapping(value = "edit/{id}")
    @ResponseBody
    public Map<String,Object> edit(@PathVariable int id, HttpServletRequest req,
            HttpServletResponse res, Model model) {
    	User user = getLoginUser(req);
    	Map<String, Object> resultParams = new HashMap<String, Object>();
    	
    	if(user != null) {
    		try {
		    	App app = appService.getById(id);
		    	//user validate
		    	if(app.getUserId().equals(user.getId())) {
					resultParams.put("app", app);
	    			resultParams.put("status", "ok");
		    	} else {
	    			resultParams.put("status", "failed");
    				resultParams.put("message", "authentication failed");
		    	}
    		}catch (Exception e) {
    			resultParams.put("status", "failed");
    			resultParams.put("message", e.getMessage());
    		}
    	}
        return resultParams;
    }
    
    /**
     * 
     */
    @RequestMapping(value = "save")
    @ResponseBody
    public Map<String,Object> save(HttpServletRequest req, HttpServletResponse res,
            final @ModelAttribute("command") App app, Model model)
            throws IOException {
    	User user = getLoginUser(req);
		Map<String, Object> resultParams = new HashMap<String, Object>();
		
    	if (user != null) {
    		//validate for app param
        	Map<String, Object> appValidParams = appValidator.isValid(app);
        	if(!(Boolean)appValidParams.get("isValid")) {
        		resultParams.put("status", "failed");
        		resultParams.putAll(appValidParams);
        		return resultParams;
        	}
    		try {
    			//1.read old value
    			String opType = (app.getId() == null ? "add" : "edit");
    			App oldApp = (app.getId() == null ? new App() : appService.getById(app.getId()));
    			/*
    			 * 2.user validation pass only when add a app 
    			 * 	 or userId equals oldApp.getUserId
    			 *   (if oldApp.getUserId is null, only app.getId is null 
    			 *   or no such app
    			*/
    			if(app.getId() == null || user.getId().equals(oldApp.getUserId())) {
	    			//3.save app info
		    		app.setUserId(user.getId());
		    		appService.saveOrUpdate(app);
		    		//4.read new value
		    		App newApp = appService.getById(app.getId()); 
		    		//5.send user action log
		    		userActionLogService.sendLog(newApp.getId(), user.getId(), oldApp, newApp, opType);
		    		resultParams.put("status", "ok");
    			} else {
    				resultParams.put("status", "failed");
    				resultParams.put("message", "authentication failed");
    			}
    		}catch (Exception e) {
    			resultParams.put("status", "failed");
    			resultParams.put("message", e.getMessage());
    		}
    	}
        return resultParams;
    }
    
    @RequestMapping(value = "changes")
    @ResponseBody
    public Map<String,Object> changes(HttpServletRequest request,
            HttpServletResponse response, Model model)
            throws IOException {
    	
    	User user = getLoginUser(request);
		Map<String, Object> resultParams = new HashMap<String, Object>();
		
    	if(user != null) {
    		try {
		        String ids = request.getParameter("ids");
		        String value = request.getParameter("value");
		
		        if (StringUtil.isEmpty(ids) || StringUtil.isEmpty(value)) {
		        	return null;
		        }
		        Map<Object, Object> resultMap = new HashMap<Object, Object>();
		        List<Integer> appIdList = BaseUtils.str2list(ids);
		        
				//appIdList contain many appId
		        String opType = (Constants.Status.DELETE.equals(value) ? "delete" : "change");
				for(Integer appId : appIdList) {
					//1.read oldvalue
					App oldApp = appService.getById(appId);
					//2. user validation
	    			if(user.getId().equals(oldApp.getUserId())) {
						//3.update every app status
						appService.updateStatusById(appId, value, StatusUtil.getState(value), StatusUtil.getRevertState(value), user.getId());
						//4.read newvalue
						App newApp = appService.getById(appId);
						//5.send action log
			    		userActionLogService.sendLog(newApp.getId(), user.getId(), oldApp, newApp, opType);
			    		resultParams.put("status", "ok");
	    			} else {
	    				resultParams.put("status", "failed");
	    				resultParams.put("message", "authentication failed");
	    			}
				}
    		} catch (Exception e) {
    			resultParams.put("status", "failed");
    			resultParams.put("message", e.getMessage());
    		}
    	}
        return resultParams;
    }    
}
