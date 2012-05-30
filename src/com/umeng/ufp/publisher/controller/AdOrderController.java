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
import com.umeng.ufp.core.domain.AdOrder;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.core.domain.validator.AdOrderValidator;
import com.umeng.ufp.publisher.utils.Constants;
import com.umeng.ufp.publisher.utils.ListRequestUtil;
import com.umeng.ufp.publisher.utils.StatusUtil;

@Controller
@RequestMapping(value = "/adorder")
public class AdOrderController extends BaseController<AdOrder, Integer> {

    public static final String ALLOWED_LOGIN_ADDR = "adpm";
    private Logger log = Logger.getLogger(AdOrderController.class);
    private AdOrderValidator adOrderValidator = new AdOrderValidator();

    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
        return "adorder/list";
    }
    
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String,Object> list(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
    	Map<String, Object> resultParams = new HashMap<String, Object>();
    	User user = getLoginUser(request);
    	
    	if(user != null) {
    		try {
		    	Page<Map<String, Object>> adOrderPage=ListRequestUtil.getListRequestPage(request);
		    	adOrderPage.addParam("userId", user.getId());
		        adOrderPage=adOrderService.getAdOrderListByPage(adOrderPage);
		        
		        resultParams.put("adOrderPage", adOrderPage);
		        resultParams.put("exportUrl", ExportUtil.getCsvRequestUrl(adOrderPage.getCompleteRequestUrl()));
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
        page=adOrderService.getAdOrderListByPage(page);

        List<String> columnNames=Arrays.asList(new String[]{"name", "platform"});
        try{
            ExportUtil.writeCsvOutput(response, page.getResult(), columnNames,"adslot_list.csv");   
        }catch(IOException e){
            //error page?
        }
        return null;
    }    

    @RequestMapping(value = "add")
    public String add(HttpServletRequest req, HttpServletResponse res,
            final SessionStatus status, Model model) {
        return "adorder/add";
    }
    
    @RequestMapping(value = "edit/{id}")
    @ResponseBody
    public Map<String, Object> edit(@PathVariable int id, HttpServletRequest req,
            HttpServletResponse res, Model model) {
    	
    	User user = getLoginUser(req);
    	Map<String, Object> resultParams = new HashMap<String, Object>();
    	
    	if(user != null) {
    		try {
		    	AdOrder adOrder = adOrderService.getById(id);
		    	//user validate
		    	if(adOrder.getUserId().equals(user.getId())) {
			    	resultParams.put("adOrder", adOrder);
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
    	
        User user = getLoginUser(req);
        Map<String, Object> resultParams = new HashMap<String, Object>();
        
        if(user != null) {
        	try {
        		Page<Map<String, Object>> adPage = ListRequestUtil.getListRequestPage(req);
		        adPage.addParam("adOrderId", id);
		        adPage.addParam("userId", user.getId());
		        adPage=adService.getAdListByPage(adPage);
		        resultParams.put("adPage", adPage);
    			resultParams.put("status", "ok");
        	} catch (Exception e) {
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
    public Map<String, Object> save(HttpServletRequest req, HttpServletResponse res,
            final @ModelAttribute("command") AdOrder adOrder,Model model)
            throws IOException {
    	
    	User user = getLoginUser(req);
    	Map<String, Object> resultParams = new HashMap<String, Object>();
    	
    	if (user != null) {
    		//validate for adorder param
        	Map<String, Object> adOrderValidParams = adOrderValidator.isValid(adOrder);
        	if(!(Boolean)adOrderValidParams.get("isValid")) {
        		resultParams.put("status", "failed");
        		resultParams.putAll(adOrderValidParams);
        		return resultParams;
        	}
	    	try {
    			//1.read old value
    			String opType = (adOrder.getId() == null ? "add" : "edit");
    			AdOrder oldAdOrder = (adOrder.getId() == null ? new AdOrder() : adOrderService.getById(adOrder.getId()));
    			/*
    			 * 2.user validation pass only when add a adSlot 
    			 * 	 or userId equals oldAd.getUserId
    			 *   (if oldAd.getUserId is null, only adSlot.getId is null 
    			 *   or no such adSlot
    			*/
    			if(adOrder.getId() == null || user.getId().equals(oldAdOrder.getUserId())) {
    				//3. save adorder
        			adOrder.setUserId(user.getId());
    	    		adOrderService.saveOrUpdate(adOrder);
    	    		//4.read new value
    	    		AdOrder newAdOrder = adOrderService.getById(adOrder.getId()); 
    	    		//5.send user action log
    	    		userActionLogService.sendLog(newAdOrder.getId(), user.getId(), oldAdOrder, newAdOrder, opType);

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
		        //there are many adOrderId in ids
				List<Integer> adOrderIdList = BaseUtils.str2list(ids);
				
				for(Integer adOrderId : adOrderIdList) {
		        	//1.read old value
		        	AdOrder oldAdOrder = adOrderService.getById(adOrderId);
		        	//2. user validation
					if(user.getId().equals(oldAdOrder.getUserId())) {
			        	//3.update status
						adOrderService.updateStatusById(adOrderId, value, StatusUtil.getState(value), StatusUtil.getRevertState(value), user.getId());
			    		//4.read new value
			    		AdOrder newAdOrder = adOrderService.getById(adOrderId); 
			    		//5.send user action log
			    		userActionLogService.sendLog(newAdOrder.getId(), user.getId(), oldAdOrder, newAdOrder, opType);
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
