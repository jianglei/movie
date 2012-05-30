/**
 * 
 */
package com.umeng.ufp.core.domain.validator;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.umeng.core.utils.StringUtil;
import com.umeng.ufp.core.domain.AdContent;
import com.umeng.ufp.core.domain.constants.AdContentConstants;
import com.umeng.ufp.publisher.utils.Constants;


public class AdContentValidator {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    
    
	public Map<String, Object> isValid(AdContent adContent) {
		Map<String, Object> validation = new HashMap<String, Object>();
		
		String platform = adContent.getPlatform();
		if(StringUtil.isEmpty(platform) || !Constants.paltformSet.contains(platform)) {
			validation.put("isValid", false);
			validation.put("message", "platform参数不合法");
			return validation;
		}
		
		String contentType = adContent.getContentType();
		if(StringUtil.isEmpty(contentType) || !AdContentConstants.contentTypeSet.contains(contentType)) {
			validation.put("isValid", false);
			validation.put("message", "contentType参数不合法");
			return validation;
		}
		
		String landingType = adContent.getLandingType();
		if(StringUtil.isEmpty(landingType) || !AdContentConstants.landingTypeSet.contains(landingType)) {
			validation.put("isValid", false);
			validation.put("message", "landingType参数不合法");
			return validation;
		}
		
		String displayType = adContent.getDisplayType();
		if(StringUtil.isEmpty(displayType) || !AdContentConstants.displayTypeSet.contains(displayType)) {
			validation.put("isValid", false);
			validation.put("message", "displayType参数不合法");
			return validation;
		}

	    validation.put("isValid", true);
		return validation;
	}

}
