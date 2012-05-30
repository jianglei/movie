/**
 * 
 */
package com.umeng.ufp.core.domain.validator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.umeng.core.utils.StringUtil;
import com.umeng.ufp.core.domain.Ad;
import com.umeng.ufp.core.domain.constants.AdConstants;
import com.umeng.ufp.publisher.utils.Constants;


public class AdValidator {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    
    
    
	public Map<String, Object> isValid(Ad ad) {
		Map<String, Object> validation = new HashMap<String, Object>();
		
		String name = ad.getName();
		if(StringUtil.isEmpty(name)) {
			validation.put("isValid", false);
			validation.put("message", "name参数不合法");
			return validation;
		}
		
		Date startTime = ad.getStartTime();
		if(startTime == null) {
			validation.put("isValid", false);
			validation.put("message", "startTime参数不合法");
			return validation;
		}
		
		
		String platform = ad.getPlatform();
		if(StringUtil.isEmpty(platform) || !Constants.paltformSet.contains(platform)) {
			validation.put("isValid", false);
			validation.put("message", "platform参数不合法");
			return validation;
		}
		

		String areas = ad.getAreas();
		if(StringUtil.isEmpty(areas)) {
			validation.put("isValid", false);
			validation.put("message", "areas参数不合法");
			return validation;
		}
		
		String networks = ad.getNetworks();
		if(StringUtil.isEmpty(networks)) {
			validation.put("isValid", false);
			validation.put("message", "network参数不合法");
			return validation;
		}
		
		String budget = ad.getBudget();
		if(StringUtil.isEmpty(budget) || !AdConstants.budgetSet.contains(budget)) {
			validation.put("isValid", false);
			validation.put("message", "budget参数不合法");
			return validation;
		}
		
		String priceType = ad.getPriceType();
		if(StringUtil.isNotEmpty(priceType) && !AdConstants.priceTypeSet.contains(priceType)) {
			validation.put("isValid", false);
			validation.put("message", "priceType参数不合法");
			return validation;
		}

	    validation.put("isValid", true);
		return validation;
	}

}
