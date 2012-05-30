package com.umeng.ufp.publisher.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.umeng.ufp.core.domain.Ad;
import com.umeng.ufp.core.domain.AdContent;
import com.umeng.ufp.core.domain.AdHolder;
import com.umeng.ufp.core.domain.AdOrder;
import com.umeng.ufp.core.domain.AdSlot;
import com.umeng.ufp.core.domain.App;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.core.domain.UserOpLog;
import com.umeng.ufp.publisher.dao.EntityDaoImpl;
import com.umeng.ufp.publisher.service.BackendService;
import com.umeng.ufp.publisher.service.UserOpLogService;

@Service
public class UserOpLogServiceImpl extends EntityDaoImpl<UserOpLog, Integer> implements UserOpLogService {

	@Resource
	BackendService backendService;
	public void sendLog(App app, String opType) {
		UserOpLog userOpLog = new UserOpLog();
		
		userOpLog.setObjectKey(app.getId().toString());
		userOpLog.setObjectType("app");
		userOpLog.setObjectValue(app.toString());
		userOpLog.setOpDate(new Date());
		userOpLog.setOpType(opType);
		userOpLog.setUserId(app.getUserId());
		backendService.sendUserOpLogMessage(userOpLog);
	}
	public void sendLog(AdSlot adSlot, String opType) {
		UserOpLog userOpLog = new UserOpLog();
		
		userOpLog.setObjectKey(adSlot.getId().toString());
		userOpLog.setObjectType("adslot");
		userOpLog.setObjectValue(adSlot.toString());
		userOpLog.setOpDate(new Date());
		userOpLog.setOpType(opType);
		userOpLog.setUserId(adSlot.getUserId());
		backendService.sendUserOpLogMessage(userOpLog);
	}
	
	public void sendLog(Ad ad, String opType) {
		UserOpLog userOpLog = new UserOpLog();
		
		userOpLog.setObjectKey(ad.getId().toString());
		userOpLog.setObjectType("ad");
		userOpLog.setObjectValue(ad.toString());
		userOpLog.setOpDate(new Date());
		userOpLog.setOpType(opType);
		userOpLog.setUserId(ad.getUserId());
		backendService.sendUserOpLogMessage(userOpLog);
	}
	public void sendLog(AdContent adContent, String opType) {
		UserOpLog userOpLog = new UserOpLog();
		
		userOpLog.setObjectKey(adContent.getId().toString());
		userOpLog.setObjectType("adcontent");
		userOpLog.setObjectValue(adContent.toString());
		userOpLog.setOpDate(new Date());
		userOpLog.setOpType(opType);
		userOpLog.setUserId(adContent.getUserId());
		backendService.sendUserOpLogMessage(userOpLog);
	}
	public void sendLog(AdOrder adOrder, String opType) {
		UserOpLog userOpLog = new UserOpLog();
		
		userOpLog.setObjectKey(adOrder.getId().toString());
		userOpLog.setObjectType("adorder");
		userOpLog.setObjectValue(adOrder.toString());
		userOpLog.setOpDate(new Date());
		userOpLog.setOpType(opType);
		userOpLog.setUserId(adOrder.getUserId());
		backendService.sendUserOpLogMessage(userOpLog);
	}
	public void sendLog(User user, String opType) {		
		UserOpLog userOpLog = new UserOpLog();
		
		userOpLog.setObjectKey(user.getId().toString());
		userOpLog.setObjectType("user");
		userOpLog.setObjectValue(user.toString());
		userOpLog.setOpDate(new Date());
		userOpLog.setOpType(opType);
		userOpLog.setUserId(user.getId());
		backendService.sendUserOpLogMessage(userOpLog);
		
	}

	public void sendLog(AdHolder adHolder, String opType) {		
		UserOpLog userOpLog = new UserOpLog();
		
		userOpLog.setObjectKey(adHolder.getAdSlotId() + ":" + adHolder.getAdId());
		userOpLog.setObjectType("adholder");
		userOpLog.setObjectValue(adHolder.serialize());
		userOpLog.setOpDate(new Date());
		userOpLog.setOpType(opType);
		userOpLog.setUserId(adHolder.getUserId());
		backendService.sendUserOpLogMessage(userOpLog);
		
	}
	//only change status in this func
	public void sendLog(Map<String, Object> params, String opType, String objectType) {
		UserOpLog userOpLog = new UserOpLog();
		
		userOpLog.setObjectKey(params.get("id").toString());
		userOpLog.setObjectType(objectType);
		userOpLog.setObjectValue(params.toString());
		userOpLog.setOpDate(new Date());
		userOpLog.setOpType(opType);
		userOpLog.setUserId(Integer.parseInt(params.get("userId").toString()));
		backendService.sendUserOpLogMessage(userOpLog);
	}
	
}
