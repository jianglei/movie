package com.umeng.ufp.publisher.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Joiner;
import com.umeng.core.utils.BaseUtils;
import com.umeng.core.utils.ExportUtil;
import com.umeng.core.utils.Page;
import com.umeng.core.utils.StringUtil;
import com.umeng.ufp.core.domain.Ad;
import com.umeng.ufp.core.domain.AdContent;
import com.umeng.ufp.core.domain.AdHolder;
import com.umeng.ufp.core.domain.AdSlot;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.core.domain.validator.AdContentValidator;
import com.umeng.ufp.core.domain.validator.AdValidator;
import com.umeng.ufp.publisher.utils.AndroidUtil;
import com.umeng.ufp.publisher.utils.Constants;
import com.umeng.ufp.publisher.utils.ListRequestUtil;
import com.umeng.ufp.publisher.utils.StatusUtil;

@Controller
@RequestMapping(value = "/ad")
public class AdController extends BaseController<Ad, Integer> {

    public static final String ALLOWED_LOGIN_ADDR = "adpm";
    private Logger log = Logger.getLogger(getClass());
    private AdValidator adValidator = new AdValidator();
    private AdContentValidator adContentValidator = new AdContentValidator();
    
    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        /*
         * 查询分页数据
         */
    	
        return "ad/list";
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
	    		Page<Map<String, Object>> adPage = ListRequestUtil.getListRequestPage(request);
		        adPage.addParam("userId", user.getId());

		        adPage=adService.getAdListByPage(adPage);
   
		        List<Map<String, Object>> adList = adPage.getResult();     
		        for(Map<String, Object> ad : adList) {	        		
		        	if(ad.containsKey("id")) {
		        		List<Integer> adSlotIdList = adHolderService.getAdSlotIdListByAdId(Integer.parseInt(ad.get("id").toString()));
		        		List<String> adSlotNameList = new ArrayList<String>();
		        		for(Integer adSlotId : adSlotIdList) {
		        			AdSlot adSlot = adSlotService.getById(adSlotId);
		        			adSlotNameList.add(adSlot.getName());
		        		}
		        		ad.put("ad_slot_name", Joiner.on(",").join(adSlotNameList));
		        	}
		        }
		        
		        resultParams.put("adPage", adPage);
		        resultParams.put("exportUrl", ExportUtil.getCsvRequestUrl(adPage.getCompleteRequestUrl()));
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
        page=adService.getAdListByPage(page);

        List<String> columnNames=Arrays.asList(new String[]{"name", "platform"});
        columnNames = new ArrayList(page.getParam().keySet());
        
        try{
            ExportUtil.writeCsvOutput(response, page.getResult(), columnNames,"ad.csv");   
        }catch(IOException e){
            //error page?
        }
        return null;
    }    

    @RequestMapping(value = "add1")
    @ResponseBody
    public Map<String, Object> add(HttpServletRequest req, HttpServletResponse res, Model model) {
    	Map<String, Object> resultParams = new HashMap<String, Object>();
    	
    	User user = getLoginUser(req);
    	if(user != null) {
    		try {
		        Page<Map<String, Object>> adSlotPage=ListRequestUtil.getListRequestPage(req, 10000);
		        adSlotPage.addParam("userId", user.getId());
		        adSlotPage=adSlotService.getAdSlotListByPage(adSlotPage);
		        resultParams.put("adSlotPage", adSlotPage);
		        
		        Page<Map<String, Object>> adOrderPage=ListRequestUtil.getListRequestPage(req, 10000);
		        adOrderPage.addParam("userId", user.getId());
		        adOrderPage=adOrderService.getAdOrderListByPage(adOrderPage);
	
		    	resultParams.put("adOrderPage", adOrderPage);
		        resultParams.put("status", "ok");
    		} catch (Exception e) {
		        resultParams.put("status", "failed");
		        resultParams.put("message", e.getMessage());
    		}
    	}
        return resultParams;
    }
   
    @RequestMapping(value = "add2")
    @ResponseBody
    public Map<String,Object> add2(HttpServletRequest request, HttpServletResponse response, 
    		@RequestParam(value="platform", required = false) String platform,
    		@RequestParam(value="landingSize", required = false) String landingSize,
    		Model model) {
    		
    	Map<String, Object> resultParams = new HashMap<String, Object>();	
    	User user = getLoginUser(request);
    	
    	if(user != null) {
    		try {
    			//1. get ad_content info by user_id and platform and landingsize
	    		Page<Map<String, Object>> adContentPage = ListRequestUtil.getListRequestPage(request, 10000);
		        adContentPage.addParam("userId", user.getId());
		        adContentPage.addParam("platform", platform);
		        adContentPage.addParam("landingSize", landingSize);
		        adContentPage=adContentService.getAdContentListByPage(adContentPage);
		        //2. get ad info for every ad_content
		        Map<String, Object> param = new HashMap<String, Object>();
	    		List<Map<String, Object>> adList = new ArrayList<Map<String, Object>>();
		        for(Map<String, Object> adContent : adContentPage.getResult()) {
		        	if(adContent.containsKey("id")) {
		        		Integer adId = (Integer)adContent.get("id");
		        		param.put("adId", adId);
		        		param.put("userId", user.getId());
			    		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			        	list = adService.getAdListByMap(param);
		        		/*
		        		 * 3. one ad_content only mapping to one ad, but ad status may be delete,
			        	 * so we only add the status not delete to adList
			        	 */
			        	if(list.size() > 0) {
			        		adContent.putAll(list.get(0));
			        		adList.add(adContent);
			        	} 
		        	}
		        }
		        adContentPage.setResult(adList);
		        resultParams.put("adPage", adContentPage);
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
    	User user = getLoginUser(req);
    	Map<String, Object> resultParams = new HashMap<String, Object>();
    	
    	if(user != null) {
    		try {
		    	Ad ad = adService.getById(id);
		    	//user validate
		    	if(ad.getUserId().equals(user.getId())) {
			    	resultParams.put("ad", ad);
			    			    	
			    	Page<Map<String, Object>> adSlotPage=ListRequestUtil.getListRequestPage(req, 10000);
			    	adSlotPage.addParam("userId", user.getId());
			        adSlotPage=adSlotService.getAdSlotListByPage(adSlotPage);
			        resultParams.put("adSlotPage", adSlotPage);
			    			
			        List<AdHolder> adHolderList = adHolderService.findByAdId(ad.getId());
			        resultParams.put("adHolderList", adHolderList);
			    	
			        Page<Map<String, Object>> adOrderPage=ListRequestUtil.getListRequestPage(req, 10000);
			        adOrderPage.addParam("userId", user.getId());
			        adOrderPage=adOrderService.getAdOrderListByPage(adOrderPage);
			        resultParams.put("adOrderPage", adOrderPage);
			        resultParams.put("status", "ok");
		    	} else {
    				resultParams.put("message", "authentication failed");
			        resultParams.put("status", "failed");
		    		
		    	}
    		} catch (Exception e) {
		        resultParams.put("message", e.getMessage());
		        resultParams.put("status", "failed");
    		}
	    			
    	}
        return resultParams;
        
    }
    
    @RequestMapping(value = "edit2/{id}")
    @ResponseBody
    public Map<String, Object> edit2(@PathVariable int id, HttpServletRequest req,
            HttpServletResponse res, Model model) {
    	User user = getLoginUser(req);
    	Map<String, Object> resultParams = new HashMap<String, Object>();
    	
    	if(user != null) {
	    	AdContent adContent = adContentService.getById(id);
	    	//user validation
	    	if(adContent.getUserId().equals(user.getId())) {
		    	resultParams.put("adContent", adContent);
	    		resultParams.put("status", "ok");
	    	} else {
	    		resultParams.put("status", "failed");
				resultParams.put("message", "authentication failed");
	    	}
    	}
        return resultParams;
    }
    
    @RequestMapping(value = "save")
    @ResponseBody
    public Map<String, Object> save(HttpServletRequest req, HttpServletResponse res,
    		@RequestParam("adSlotId[]") String[] adSlotIds, 
    		Model model)
            throws Exception {

        Ad ad = new Ad();
    	bind(req, ad);
    	AdContent adContent = new AdContent();
    	bind(req, adContent);
 	
    	User user = getLoginUser(req);
		Map<String, Object> resultParams = new HashMap<String, Object>();
    	
    	if (user != null) {
        	//validate for ad param
        	Map<String, Object> adValidParams = adValidator.isValid(ad);
        	if(!(Boolean)adValidParams.get("isValid")) {
        		resultParams.put("status", "failed");
        		resultParams.putAll(adValidParams);
        		return resultParams;
        	}
        	//validate for adcontent param
        	Map<String, Object> adContentValidParams = adContentValidator.isValid(adContent);
        	if(!(Boolean)adContentValidParams.get("isValid")) {
        		resultParams.put("status", "failed");
        		resultParams.putAll(adContentValidParams);
    		}
    		try {
    			//1. read oldvalue
    			String opType = (ad.getId() == null ? "add" : "edit");
    			Ad oldAd = (ad.getId() == null ? new Ad() : adService.getById(ad.getId()));
    			/*
    			 * 2.user validation pass only when add a ad
    			 * 	 or userId equals oldAd.getUserId
    			 *   (if oldAd.getUserId is null, only ad.getId is null 
    			 *   or no such ad
    			*/
    			if(ad.getId() == null || user.getId().equals(oldAd.getUserId())) {
    				AdContent oldAdContent = (ad.getId() == null ? new AdContent() : adContentService.getById(ad.getId()));
    				//3. parse the apk info
    				ad.setUserId(user.getId());
    		    	adContent.setUserId(user.getId());
    	    		String path = adContent.getUrl();

    		    	if(StringUtil.isNotEmpty(path) && path.endsWith(".apk")) {
    		    		try {
    			    		String localPath = getServletContext().getRealPath("/");//本地根目录路径
    			    		Map<String, String> info = AndroidUtil.getAPKInfo(localPath, path);
    			    		adContent.setAppPackageName(info.get("appPackageName"));
    			    		adContent.setAppVersionName(info.get("appVersionName"));
    			    		adContent.setAppVersionCode(info.get("appVersionCode"));
    					} catch (Exception e) {
    						// TODO: handle exception
    					}
    		    	}
        			//4. save ad adcontent
    		    	adService.save(ad, adContent, adSlotIds);
    		    	//5. read newvalue
        			Ad newAd = adService.getById(ad.getId());
        			AdContent newAdContent = adContentService.getById(ad.getId());
    	    		//6.send user action log
    	    		userActionLogService.sendLog(newAd.getId(), user.getId(), oldAd, newAd, opType);
    	    		userActionLogService.sendLog(newAdContent.getId(), user.getId(), oldAdContent, newAdContent, opType);
    	    		
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
    
    @RequestMapping(value = "prior")
    @ResponseBody
    public Map<String, Object> prior(HttpServletRequest req,
            HttpServletResponse res, Model model)
            throws IOException {
    	    
    	User user = getLoginUser(req);
		Map<String, Object> resultParams = new HashMap<String, Object>();
		
    	if(user != null) {
    		try {
		    	String id = req.getParameter("id");
		        String prior = req.getParameter("prior");
		        
		        if (StringUtil.isEmpty(id) || StringUtil.isEmpty(prior)) {
		        	return null;
		        }
		        Integer adId = Integer.parseInt(id);
		        //1.read old value
	        	Ad oldAd = adService.getById(adId);
				//2. user validation
				if(user.getId().equals(oldAd.getUserId())) {
		        	//3.update prior
			        adService.updatePriorById(adId, Integer.parseInt(prior), user.getId());
		    		//4.read new value
		    		Ad newAd = adService.getById(adId); 
		    		//5.send user action log
		    		userActionLogService.sendLog(newAd.getId(), user.getId(), oldAd, newAd, "change");
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
    public Map<String, Object> changes(HttpServletRequest request,
            HttpServletResponse response, Model model)
            throws IOException {
    	
    	User user = getLoginUser(request);
		Map<String, Object> resultParams = new HashMap<String, Object>();
		
    	if(user != null){
    		try {
		        String ids = request.getParameter("ids");
		        String value = request.getParameter("value");
		
		        if (StringUtil.isEmpty(ids) || StringUtil.isEmpty(value)) {
		        	return null;
		        }
	        	String opType = (Constants.Status.DELETE.equals(value) ? "delete" : "change");
		        //there are many adOrderId in ids
				List<Integer> adIdList = BaseUtils.str2list(ids);
	
				for(Integer adId : adIdList) {
		        	//1.read old value
		        	Ad oldAd = adService.getById(adId);
					//2. user validation
					if(user.getId().equals(oldAd.getUserId())) {
						//3.update status
						adService.updateStatusById(adId, value, StatusUtil.getState(value), StatusUtil.getRevertState(value), user.getId());
			    		//4.read new value
			    		Ad newAd = adService.getById(adId); 
			    		//5.send user action log
			    		userActionLogService.sendLog(newAd.getId(), user.getId(), oldAd, newAd, opType);
			    		resultParams.put("status", "ok");
					} else {
	    				resultParams.put("status", "failed");
	    				resultParams.put("message", "authentication failed");
	    			}
				}
    		}catch (Exception e) {
    			resultParams.put("status", "failed");
    			resultParams.put("message", e.getMessage());
    		}
    	}
        return resultParams;
    }
}
