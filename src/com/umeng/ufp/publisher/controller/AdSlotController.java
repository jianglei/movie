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
import com.umeng.ufp.core.domain.AdSlot;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.core.domain.validator.AdSlotValidator;
import com.umeng.ufp.publisher.utils.Constants;
import com.umeng.ufp.publisher.utils.ListRequestUtil;
import com.umeng.ufp.publisher.utils.StatusUtil;

@Controller
@RequestMapping(value = "/adslot")
public class AdSlotController extends BaseController<AdSlot, Integer> {

    private Logger log = Logger.getLogger(AdSlotController.class);
    private AdSlotValidator adSlotValidator = new AdSlotValidator();

    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
        return "adslot/list";
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
	    	Page<Map<String, Object>> adSlotPage=ListRequestUtil.getListRequestPage(request);
		    	adSlotPage.addParam("userId", user.getId());
		        adSlotPage=adSlotService.getAdSlotListByPage(adSlotPage);
		        resultParams.put("adSlotPage", adSlotPage);
		        resultParams.put("exportUrl", ExportUtil.getCsvRequestUrl(adSlotPage.getCompleteRequestUrl()));
	    		resultParams.put("status", "ok");
	    	} catch (Exception e) {
	    		resultParams.put("status", "failed");
    			resultParams.put("message", e.getMessage());
    		}
    	}
        return resultParams;
    }
    
    @RequestMapping(value = "export")
    public String export(HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Map<String, Object>> page=ListRequestUtil.getListRequestPage(request);
        page=adSlotService.getAdSlotListByPage(page);

        List<String> columnNames=Arrays.asList(new String[]{"name", "platform"});
        try{
            ExportUtil.writeCsvOutput(response, page.getResult(), columnNames,"adslot_list.csv");   
        }catch(IOException e){
            //error page?
        }
        return null;
    }   

    @RequestMapping(value = "add1")
    @ResponseBody
    public Map<String,Object> add1(HttpServletRequest req, HttpServletResponse res,
    		final @ModelAttribute("command") AdSlot adSlot, final SessionStatus status, Model model) {
    	User user = getLoginUser(req);
    	Map<String, Object> resultParams = new HashMap<String, Object>();
    	
    	if(user != null) {
    		try {
		        Page<Map<String, Object>> appPage=ListRequestUtil.getListRequestPage(req, 10000);
		        appPage.addParam("userId", user.getId());
		        appPage=appService.getAppListByPage(appPage);
		          	
		        resultParams.put("appPage", appPage);
		        resultParams.put("adSlot", adSlot);
		        resultParams.put("status", "ok");
    		} catch (Exception e) {
		        resultParams.put("status", "failed");
    			resultParams.put("message", e.getMessage());
    		}
    	}
        
        return resultParams;
    }
    
    @RequestMapping(value = "edit1/{id}")
    @ResponseBody
    public Map<String, Object> edit1(@PathVariable int id, HttpServletRequest req,
            HttpServletResponse res, Model model) {
    	
    	Map<String, Object> resultParams = new HashMap<String, Object>();
    	User user = getLoginUser(req);
    	
    	if(user != null) {
    		try {
		    	AdSlot adSlot = adSlotService.getById(id);
		    	//user validate
		    	if(user.getId().equals(adSlot.getUserId())) {
			    	resultParams.put("adSlot", adSlot);    	
			    	Page<Map<String, Object>> appPage=ListRequestUtil.getListRequestPage(req, 10000);
			    	appPage.addParam("userId", user.getId());
			        appPage=appService.getAppListByPage(appPage);
			        resultParams.put("appPage", appPage); 
			        resultParams.put("status", "ok");
		    	} else {
		    		resultParams.put("status", "failed");
    				resultParams.put("message", "authentication failed");
		    	}
    		} catch (Exception e) {
		        resultParams.put("status", "failed");
    			resultParams.put("message", e.getMessage());
    		}
    	}
    	
        return resultParams;
    }
    
    @RequestMapping(value = "save")
    @ResponseBody
    public Map<String, Object> save(HttpServletRequest req, HttpServletResponse res,
            final @ModelAttribute("command") AdSlot adSlot,Model model)
            throws IOException {
    	
    	User user = getLoginUser(req);
    	Map<String, Object> resultParams = new HashMap<String, Object>();
    	
    	if (user != null) {
    		//validate for adorder param
        	Map<String, Object> adSlotValidParams = adSlotValidator.isValid(adSlot);
        	if(!(Boolean)adSlotValidParams.get("isValid")) {
        		resultParams.put("status", "failed");
        		resultParams.putAll(adSlotValidParams);
        		return resultParams;
        	}
    		try {
    			//1. read oldvalue
    			String opType = (adSlot.getId() == null ? "add" : "edit");
    			AdSlot oldAdSlot = (adSlot.getId() == null ? new AdSlot() : adSlotService.getById(adSlot.getId()));
    			/*
    			 * 2.user validation pass only when add a adSlot 
    			 * 	 or userId equals oldAd.getUserId
    			 *   (if oldAd.getUserId is null, only adSlot.getId is null 
    			 *   or no such adSlot
    			*/
    			if(adSlot.getId() == null || user.getId().equals(oldAdSlot.getUserId())) {
    				//3. read app info 
    				
    				//4. save adslot
		    		adSlot.setUserId(user.getId());
		    		adSlotService.saveOrUpdate(adSlot);
		    		//5. read newvalue
		    		AdSlot newAdSlot = adSlotService.getById(adSlot.getId());
					//6.send action log
		    		userActionLogService.sendLog(newAdSlot.getId(), user.getId(), oldAdSlot, newAdSlot, opType);
		    		resultParams.put("status", "ok");
    			} else {
    				resultParams.put("status", "failed");
    				resultParams.put("message", "authentication failed");
        		}
    		} catch (Exception e) {
    			resultParams.put("status", "failed");
    			resultParams.put("message", e.getMessage());
    		}
    	}
        return resultParams;
    }
    
    @RequestMapping(value = "showAd/{id}")
    @ResponseBody
    public Map<String, Object> showAd(@PathVariable int id, HttpServletRequest req,
    		HttpServletResponse res, Model model) {
    	
    	Map<String, Object> resultParams = new HashMap<String, Object>();
    	Page<Map<String, Object>> adHolderPage = ListRequestUtil.getListRequestPage(req);
        User user = getLoginUser(req);
        
        if(user != null) {
        	try {
		        adHolderPage.addParam("adSlotId", id);
		        adHolderPage.addParam("userId", user.getId());
		        adHolderPage=adHolderService.getAdHolderListByPage(adHolderPage);
		        for(Map<String, Object> adHolder :  adHolderPage.getResult()) {		        	
		        	if(adHolder.containsKey("ad_id")) {
		        		Map<String, Object> param = new HashMap<String, Object>();
		        		Integer adId = (Integer)adHolder.get("ad_id");
			        	param.put("adId", adId);
			        	param.put("userId", user.getId());
			        	List<Map<String, Object>> list = adService.getAdListByMap(param);
			        	adHolder.putAll(list.get(0));
		        	}
		        }
		        resultParams.put("adPage", adHolderPage);
	        	resultParams.put("status", "ok");
        	} catch (Exception e) {
        		resultParams.put("status", "failed");
        		resultParams.put("message", e.getMessage());
        	}
        }
        
        return resultParams;
    }
    
    @RequestMapping(value = "changes")
    @ResponseBody
    public Map<String, Object> changes(HttpServletRequest request,
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
	
	        	String opType = (Constants.Status.DELETE.equals(value) ? "delete" : "change");
	        	
		        List<Integer> adSlotIdList = BaseUtils.str2list(ids);
		        for(Integer adSlotId : adSlotIdList) {
		        	//1.read old value
		        	AdSlot oldAdSlot = adSlotService.getById(adSlotId);
		        	//2. user validation
					if(user.getId().equals(oldAdSlot.getUserId())) {
			        	//3.update status
						adSlotService.updateStatusById(adSlotId, value, StatusUtil.getState(value), StatusUtil.getRevertState(value), user.getId());
			    		//4. read newvalue
			    		AdSlot newAdSlot = adSlotService.getById(adSlotId);
						//5.send action log
			    		userActionLogService.sendLog(newAdSlot.getId(), user.getId(), oldAdSlot, newAdSlot, opType);
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
