/**
 * 
 */
package com.umeng.ufp.core.domain.validator;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.umeng.core.utils.StringUtil;
import com.umeng.ufp.core.domain.App;
import com.umeng.ufp.publisher.utils.Constants;


public class AppValidator {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    
	public Map<String, Object> isValid(App app) {
		Map<String, Object> validation = new HashMap<String, Object>();
		String name = app.getName();
		if(StringUtil.isEmpty(name)) {
			validation.put("isValid", false);
			validation.put("message", "name参数不合法");
			return validation;
		}
		String platform = app.getPlatform();
		if(StringUtil.isNotEmpty(platform) && !Constants.paltformSet.contains(platform)) {
			validation.put("isValid", false);
			validation.put("message", "platform参数不合法");
			return validation;
		}
		validation.put("isValid", true);
		return validation;
		
	}

}
