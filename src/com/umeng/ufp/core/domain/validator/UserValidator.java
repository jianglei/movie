/**
 * 
 */
package com.umeng.ufp.core.domain.validator;

import java.util.HashMap;
import java.util.Map;

import com.umeng.ufp.core.domain.User;


public class UserValidator {
    
	public Map<String, Object> isValid(User user) {
		Map<String, Object> validation = new HashMap<String, Object>();

		validation.put("isValid", true);
		return validation;
		
	}

}
