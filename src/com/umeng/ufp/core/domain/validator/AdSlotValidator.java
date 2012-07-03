/**
 * 
 */
package com.umeng.ufp.core.domain.validator;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.umeng.core.utils.StringUtil;
import com.umeng.ufp.core.domain.AdSlot;
import com.umeng.ufp.core.domain.constants.AdSlotConstants;
import com.umeng.ufp.publisher.utils.Constants;


public class AdSlotValidator {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    
    
	public Map<String, Object> isValid(AdSlot adSlot) {
		Map<String, Object> validation = new HashMap<String, Object>();
		
		String name = adSlot.getName();
		if(StringUtil.isEmpty(name)) {
			validation.put("isValid", false);
			validation.put("message", "name参数不合法");
			return validation;
		}
		
		String platform = adSlot.getPlatform();
		if(StringUtil.isEmpty(platform) || !Constants.paltformSet.contains(platform)) {
			validation.put("isValid", false);
			validation.put("message", "platform参数不合法");
			return validation;
		}
		
		String landingType = adSlot.getLandingType();
		if(StringUtil.isEmpty(landingType) || !AdSlotConstants.landingTypeSet.contains(landingType)) {
			validation.put("isValid", false);
			validation.put("message", "landingType参数不合法");
			return validation;
		}
		
		String device = adSlot.getDevice();
		if(StringUtil.isEmpty(device)) {
			validation.put("isValid", false);
			validation.put("message", "device参数不合法");
			return validation;
		}
		
		String displayStrategy = adSlot.getDisplayStrategy();
		if(StringUtil.isNotEmpty(displayStrategy) && !AdSlotConstants.displayStrategySet.contains(displayStrategy)) {
			validation.put("isValid", false);
			validation.put("message", "displayStrategy参数不合法");
			return validation;
		}
		
		String landingSize = adSlot.getLandingSize();
		if(StringUtil.isEmpty(landingSize)) {
			validation.put("isValid", false);
			validation.put("message", "landingSize参数不合法");
			return validation;
		}
	    
		String areas = adSlot.getAreas();
		if(StringUtil.isEmpty(areas)) {
			validation.put("isValid", false);
			validation.put("message", "areas参数不合法");
			return validation;
		}
		
		String timeslots = adSlot.getTimeslots();
		if(StringUtil.isEmpty(timeslots)) {
			validation.put("isValid", false);
			validation.put("message", "timeslots参数不合法");
			return validation;
		}
		
	    String template  = adSlot.getTemplate();
		if(StringUtil.isNotEmpty(template) && !AdSlotConstants.templateSet.contains(template)) {
			validation.put("isValid", false);
			validation.put("message", "template参数不合法");
			return validation;
		}
		
		String enablePreload = adSlot.getEnablePreload();
		if(StringUtil.isEmpty(enablePreload) || !AdSlotConstants.enablePreloadSet.contains(enablePreload)) {
			validation.put("isValid", false);
			validation.put("message", "enablePreload参数不合法");
			return validation;
		}
		
		String enablePage = adSlot.getEnablePage();
		if(StringUtil.isEmpty(enablePage) || !AdSlotConstants.enablePageSet.contains(enablePage)) {
			validation.put("isValid", false);
			validation.put("message", "enablePage参数不合法");
			return validation;
		}
	    
		String adNetworkStrategy = adSlot.getAdNetworkStrategy();
		if(StringUtil.isNotEmpty(adNetworkStrategy) && !AdSlotConstants.adNetworkStrategySet.contains(adNetworkStrategy)) {
			validation.put("isValid", false);
			validation.put("message", "enablePage参数不合法");
			return validation;
		}
		
	    Integer uadsPercent = adSlot.getUadsPercent();
	    if(uadsPercent != null && !(uadsPercent.intValue() >= 0 && uadsPercent.intValue() <= 100)) {
	    	validation.put("isValid", false);
			validation.put("message", "uadsPercent参数不合法");
			return validation;
	    }
	    
	    Integer xpPercent = adSlot.getXpPercent();
	    if(xpPercent != null && !(xpPercent.intValue() >= 0 && xpPercent.intValue() <= 100)) {
	    	validation.put("isValid", false);
			validation.put("message", "uadsPercent参数不合法");
			return validation;
	    }
	    
		String opensize = adSlot.getOpensize();
		if(StringUtil.isNotEmpty(opensize) && !AdSlotConstants.opensizeSet.contains(opensize)) {
			validation.put("isValid", false);
			validation.put("message", "enablePage参数不合法");
			return validation;
		}
		
		String pushStrategy = adSlot.getPushStrategy();
		if(StringUtil.isNotEmpty(pushStrategy) && !AdSlotConstants.pushStrategySet.contains(pushStrategy)) {
			validation.put("isValid", false);
			validation.put("message", "enablePage参数不合法");
			return validation;
		}
		
	    validation.put("isValid", true);
		return validation;
		
	}

}
