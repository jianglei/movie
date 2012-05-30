package com.umeng.ufp.publisher.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.umeng.core.utils.BaseUtils;
import com.umeng.core.utils.Page;
import com.umeng.ufp.core.domain.AdSlot;
import com.umeng.ufp.core.domain.App;
import com.umeng.ufp.publisher.dao.EntityDaoImpl;
import com.umeng.ufp.publisher.service.AdHolderService;
import com.umeng.ufp.publisher.service.AdService;
import com.umeng.ufp.publisher.service.AdSlotService;
import com.umeng.ufp.publisher.service.AppService;
import com.umeng.ufp.publisher.service.BackendService;
import com.umeng.ufp.publisher.service.UserOpLogService;
import com.umeng.ufp.publisher.utils.Constants;
import com.umeng.ufp.publisher.utils.StatusUtil;

@Service
public class AdSlotServiceImpl  extends EntityDaoImpl<AdSlot, Integer> implements AdSlotService {
	@Resource
	protected AdService adService;
	@Resource
	protected AppService appService;
	@Resource
	protected AdHolderService adHolderService;
	@Resource
	protected BackendService backendService;
	@Resource
	protected UserOpLogService userOpLogService;

	@Override
	public void saveOrUpdate(AdSlot adSlot) {
	
		String opType;
		opType = (adSlot.getId() == null ? "add" : "edit");

		//check select adorder's user is the same with ad
		App app = null;
		if(adSlot.getAppId() != null) {
			app = appService.getById(adSlot.getAppId());
			if(app == null || !app.getUserId().equals(adSlot.getUserId())) {
				adSlot.setAppId(null);
			}
		}
		//1.one adSlotId to one adSlot
		super.saveOrUpdate(adSlot);
		//2.write op log
		userOpLogService.sendLog(adSlot, opType);
		//3.update cache
		backendService.sendUpdateCacheMessage("adslot:" + adSlot.getId());
		//4.update ad_slot status by app status
		Integer andState = ~Constants.STATE_APP_PAUSE;
		Integer orState = Constants.STATE_NORMAL;
		if(adSlot.getAppId() != null) {
			orState |= StatusUtil.upgradeState("app", app.getState());
		}
		updateStatusById(adSlot.getId(), Constants.Status.NORMAL, orState, andState, adSlot.getUserId());
	}
	
	@Override
	public void deleteAppId(Integer appId, Integer userId) {
		//one appId to many adSlotId
		List<Integer> adSlotIdList = getAdSlotIdListByAppId(appId);
		for(Integer adSlotId : adSlotIdList) {
			//1.set appId to nil in adslot by adSlotId which is one to one
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", adSlotId);
			param.put("userId", userId);
			super.delete(param);
			//2.write op log
			param.put("appId", null);
			userOpLogService.sendLog(param, "trigger", "adslot");
			//3.update cache
			backendService.sendUpdateCacheMessage("adslot:" + adSlotId);
			updateStatusById(adSlotId, Constants.Status.NORMAL, Constants.STATE_NORMAL, ~Constants.STATE_APP_PAUSE, userId);
		}
		
	}
	
	@Override
	public void updateStatusByAppId(Integer appId, String status, Integer orState, Integer andState, Integer userId) {
		//one app to many adSlot
		List<Integer> adSlotIdList = getAdSlotIdListByAppId(appId);
		for(Integer adSlotId : adSlotIdList) {
			updateStatusById(adSlotId, status, orState, andState, userId);
		}
	}
	

	@Override
	public void updateStatusByIds(String ids, String status, Integer userId) {
		//there are many adSlotIds, func call where status only could be adslot_pause, normal, delete
		List<Integer> adSlotIdList = BaseUtils.str2list(ids);
		updateStatusByIdList(adSlotIdList, status, userId);

	}
	
	//batch update adslot status by adSlotIdList
	@Override
	public void updateStatusByIdList(List<Integer> adSlotIdList, String status, Integer userId) {
		//3.ad_slot status change occur adHolder's adslot_status update
		for(Integer adSlotId : adSlotIdList) {
			updateStatusById(adSlotId, status, StatusUtil.getState(status), StatusUtil.getRevertState(status), userId);
		}
	}
	
	//update adslot status one by one
	@Override
	public  void updateStatusById(Integer adSlotId, String status, Integer orState, Integer andState, Integer userId) {
		//1.update ad_slot status by ad_slot_id
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", adSlotId);
		param.put("userId", userId);
		param.put("andState", andState);
		param.put("orState", orState);
		
		super.updateByMap(param);
		//2.write op log
		userOpLogService.sendLog(param, status.equals(Constants.Status.DELETE) ? Constants.Status.DELETE : "change", "adslot");
		//3.update cache
		backendService.sendUpdateCacheMessage("adslot:" + adSlotId);
		//4.ad_slot status change occur adHolder's adslot_status update
		adHolderService.updateAdSlotStatusByAdSlotId(adSlotId, status, orState, andState, userId);
	}
	
	
	@Override
	public AdSlot getById(int id) {
		return super.get(id);
	}
	
	@Override
	public Map<String, Object> getMapById(Integer adSlotId) {
		Map<String, Object> map = super.getMapById("getMapById", adSlotId);
		return map;
	}

	@Override
	public Page<Map<String, Object>> getAdSlotListByPage(
			Page<Map<String, Object>> page) {
        Page<Map<String, Object>> adSlotPage = super.findPageMap("listQueryPage", page);
        for(Map<String, Object> adSlot : adSlotPage.getResult()) {        		
        	if(adSlot.containsKey("state")) {
        		adSlot.put("status", StatusUtil.getStatus((Integer)adSlot.get("state")));
        	}
        }
        return adSlotPage;
	}
	
	@Override
	public List<Integer> getAdSlotIdListByAppId(Integer appId) {
        List<Integer> result= super.findIds("appId", appId);
        return result;
	}
	
	@Override
	public List<Integer> getAdSlotIdListByUserId(Integer userId) {
        List<Integer> result= super.findIds("userId", userId);
        return result;
	}
	

	@Override
	public List<AdSlot> getAdSlotListByMap(Map<String, Object> param) {
		return super.find(param);
	}

	@Override
	public Integer count(Map<String, Object> param) {
		return (Integer)super.count(param);
	}

}
