package com.umeng.ufp.publisher.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.umeng.core.utils.DateUtil;
import com.umeng.ufp.core.domain.Ad;
import com.umeng.ufp.core.domain.AdContent;
import com.umeng.ufp.core.domain.AdOrder;
import com.umeng.ufp.core.domain.AdSlot;
import com.umeng.ufp.core.domain.App;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.core.domain.UserActionLog;
import com.umeng.ufp.publisher.dao.EntityDaoImpl;
import com.umeng.ufp.publisher.service.BackendService;
import com.umeng.ufp.publisher.service.UserActionLogService;

@Service
public class UserActionLogServiceImpl extends EntityDaoImpl<UserActionLog, Integer> implements UserActionLogService {

	@Resource 
	BackendService backendService;
	
	public void sendLog(Integer id, Integer operatorId, App oldValue, App newValue, String opType) {
		try {
			UserActionLog userActionLog = new UserActionLog();
			
			userActionLog.setObjectId(id);
			userActionLog.setObjectType("app");
			userActionLog.setObjectOldValue(oldValue.serialize());
			userActionLog.setObjectNewValue(newValue.serialize());
			userActionLog.setOpDate(DateUtil.getDateString(new Date().getTime()));
			userActionLog.setOpType(opType);
			userActionLog.setOperatorId(operatorId);
			userActionLog.setUserId(operatorId);
			
			backendService.sendUserActionLogMessage(userActionLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void sendLog(Integer id, Integer operatorId, AdSlot oldValue, AdSlot newValue, String opType) {
		try {
			UserActionLog userActionLog = new UserActionLog();
			
			userActionLog.setObjectId(id);
			userActionLog.setObjectType("adslot");
			userActionLog.setObjectOldValue(oldValue.serialize());
			userActionLog.setObjectNewValue(newValue.serialize());
			userActionLog.setOpDate(DateUtil.getDateString(new Date().getTime()));
			userActionLog.setOpType(opType);
			userActionLog.setOperatorId(operatorId);
			userActionLog.setUserId(operatorId);
			
			backendService.sendUserActionLogMessage(userActionLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendLog(Integer id, Integer operatorId, Ad oldValue, Ad newValue, String opType) {
		try {
			UserActionLog userActionLog = new UserActionLog();
			
			userActionLog.setObjectId(id);
			userActionLog.setObjectType("ad");
			userActionLog.setObjectOldValue(oldValue.serialize());
			userActionLog.setObjectNewValue(newValue.serialize());
			userActionLog.setOpDate(DateUtil.getDateString(new Date().getTime()));
			userActionLog.setOpType(opType);
			userActionLog.setOperatorId(operatorId);
			userActionLog.setUserId(operatorId);
			
			backendService.sendUserActionLogMessage(userActionLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void sendLog(Integer id, Integer operatorId, AdContent oldValue, AdContent newValue, String opType) {
		try {
			UserActionLog userActionLog = new UserActionLog();
			
			userActionLog.setObjectId(id);
			userActionLog.setObjectType("adcontent");
			userActionLog.setObjectOldValue(oldValue.serialize());
			userActionLog.setObjectNewValue(newValue.serialize());
			userActionLog.setOpDate(DateUtil.getDateString(new Date().getTime()));
			userActionLog.setOpType(opType);
			userActionLog.setOperatorId(operatorId);
			userActionLog.setUserId(operatorId);
			
			backendService.sendUserActionLogMessage(userActionLog);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendLog(Integer id, Integer operatorId, AdOrder oldValue, AdOrder newValue, String opType) {
		try {
			UserActionLog userActionLog = new UserActionLog();
			
			userActionLog.setObjectId(id);
			userActionLog.setObjectType("adorder");
			userActionLog.setObjectOldValue(oldValue.serialize());
			userActionLog.setObjectNewValue(newValue.serialize());
			userActionLog.setOpDate(DateUtil.getDateString(new Date().getTime()));
			userActionLog.setOpType(opType);
			userActionLog.setOperatorId(operatorId);
			userActionLog.setUserId(operatorId);
			
			backendService.sendUserActionLogMessage(userActionLog);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendLog(Integer id, Integer operatorId, User oldValue, User newValue, String opType) {		
		try {
			UserActionLog userActionLog = new UserActionLog();
			
			userActionLog.setObjectId(id);
			userActionLog.setObjectType("user");
			userActionLog.setObjectOldValue(oldValue.serialize());
			userActionLog.setObjectNewValue(newValue.serialize());
			userActionLog.setOpDate(DateUtil.getDateString(new Date().getTime()));
			userActionLog.setOpType(opType);
			userActionLog.setOperatorId(operatorId);
			userActionLog.setUserId(operatorId);
			
			backendService.sendUserActionLogMessage(userActionLog);
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public List<UserActionLog> findActionLog(Integer userId, String startDate, String endDate, Map<String, Object> extraParam) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.putAll(extraParam);
		param.put("userId", userId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);	
		return findByMap(param);
	}
	
	private List<UserActionLog> findByMap(Map<String, Object> param) {
		return super.find(param);	
	}


}
