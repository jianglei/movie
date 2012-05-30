package com.umeng.ufp.publisher.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.umeng.core.utils.BaseUtils;
import com.umeng.core.utils.Page;
import com.umeng.ufp.core.domain.App;
import com.umeng.ufp.publisher.dao.EntityDaoImpl;
import com.umeng.ufp.publisher.service.AdSlotService;
import com.umeng.ufp.publisher.service.AppService;
import com.umeng.ufp.publisher.service.BackendService;
import com.umeng.ufp.publisher.service.UserOpLogService;
import com.umeng.ufp.publisher.utils.Constants;
import com.umeng.ufp.publisher.utils.StatusUtil;

@Service
public class AppServiceImpl  extends EntityDaoImpl<App, Integer> implements AppService {
	@Resource
	protected AdSlotService adSlotService;	
	@Resource
	protected BackendService backendService;
	@Resource
	protected UserOpLogService userOpLogService;

	
	@Override
	public void saveOrUpdate(App app) {
		String opType;
		opType = (app.getId() == null ? "add" : "edit");
		//1.one appId to one app
		super.saveOrUpdate(app);
		//2.write op log
		userOpLogService.sendLog(app, opType);
		//3.update cache
		backendService.sendUpdateCacheMessage("app:" + app.getId());
	}

	@Override
	public void updateStatusById(Integer appId, String status, Integer orState, Integer andState, Integer userId) {
		
		//1.save app status, one appId to one app status
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", appId);
		param.put("andState", andState);
		param.put("orState", orState);
		param.put("userId", userId);
		super.updateByMap(param);
		//2.write op log
		userOpLogService.sendLog(param, status.equals(Constants.Status.DELETE) ? "delete" : "change", "app");
		//3.update cache
		backendService.sendUpdateCacheMessage("app:" + appId);
		//4.app status changed occur cascade update
		if(status.equals(Constants.Status.DELETE)) {
			adSlotService.deleteAppId(appId, userId);
		} else {
			adSlotService.updateStatusByAppId(appId, status, StatusUtil.upgradeState("app", orState), ~StatusUtil.upgradeState("app", ~andState), userId);
		}
	}
	
	@Override
	public void updateStatusByIds(String appIds, String status, Integer userId) {
		List<Integer> appIdList = BaseUtils.str2list(appIds);
		//appIdList contain many appId
		for(Integer appId : appIdList) {
			//update every app status
			updateStatusById(appId, status, StatusUtil.getState(status), StatusUtil.getRevertState(status), userId);
		}
	}
	

	@Override
	public App getById(int id) {
		return super.get(id);
	}
	

	@Override
	public Page<Map<String, Object>> getAppListByPage(
			Page<Map<String, Object>> page) {
        Page<Map<String, Object>> appPage = super.findPageMap("listQueryPage", page);
        for(Map<String, Object> app : appPage.getResult()) {        		
        	if(app.containsKey("state")) {
        		app.put("status", StatusUtil.getStatus((Integer)app.get("state")));
        	}
        }
        return appPage;
	}


	
	@Override
	public List<Integer> getAppIdListByUserId(Integer userId) {
        List<Integer> result= super.findIds("userId", userId);
        return result;
	}
}
