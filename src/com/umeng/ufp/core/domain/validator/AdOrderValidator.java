/**
 * 
 */
package com.umeng.ufp.core.domain.validator;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.umeng.ufp.core.domain.AdOrder;


public class AdOrderValidator {
    protected Logger logger = LoggerFactory.getLogger(getClass());

	public Map<String, Object> isValid(AdOrder adOrder) {
		Map<String, Object> validation = new HashMap<String, Object>();
		validation.put("isValid", true);
		return validation;
		
	}

}
