package com.umeng.ufp.publisher.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.umeng.core.utils.BaseUtils;
import com.umeng.core.utils.Page;
import com.umeng.ufp.core.domain.AdOrder;
import com.umeng.ufp.publisher.dao.EntityDaoImpl;
import com.umeng.ufp.publisher.service.AdOrderService;
import com.umeng.ufp.publisher.service.AdService;
import com.umeng.ufp.publisher.service.BackendService;
import com.umeng.ufp.publisher.service.UserOpLogService;
import com.umeng.ufp.publisher.utils.Constants;
import com.umeng.ufp.publisher.utils.StatusUtil;

@Service
public class AdOrderServiceImpl  extends EntityDaoImpl<AdOrder, Integer> implements AdOrderService {

	@Resource
	protected AdService adService; 
	@Resource
	protected BackendService backendService; 
	@Resource
	protected UserOpLogService userOpLogService;
	
	
	@Override
	public AdOrder getById(int id) {
		return super.get(id);
	}
	
	@Override
	public Map<String, Object> getMapById(Integer adOrderId) {
		return super.getMapById("getMapById", adOrderId);
	}

	@Override
	public void saveOrUpdate(AdOrder adOrder) {
		String opType;
		opType = (adOrder.getId() == null ? "add" : "edit");
		//1.save adOrder
		super.saveOrUpdate(adOrder);
		//2.write op log
		userOpLogService.sendLog(adOrder, opType);
		//3.update cache
		backendService.sendUpdateCacheMessage("adorder:" + adOrder.getId());	
		//4.judge and update adorder status 
		Integer orState = Constants.STATE_NORMAL;
		Integer andState = ~(Constants.STATE_READY | Constants.STATE_FINISH);
		Date now = new Date();
		if(adOrder.getEndTime() != null && now.compareTo(adOrder.getEndTime()) > 0) {
			orState |= Constants.STATE_FINISH;
		} else if(adOrder.getStartTime() != null && now.compareTo(adOrder.getStartTime()) < 0) {
			orState |= Constants.STATE_READY;
		} else {
			orState |= Constants.STATE_NORMAL;
		}
		updateStatusById(adOrder.getId(), Constants.Status.NORMAL, orState, andState, adOrder.getUserId());
		
	}

	@Override
	public Page<Map<String, Object>> getAdOrderListByPage(
			Page<Map<String, Object>> page) {
        Page<Map<String, Object>> adOrderPage = super.findPageMap("listQueryPage", page); 
        for(Map<String, Object> adOrder : adOrderPage.getResult()) {        		
        	if(adOrder.containsKey("state")) {
        		adOrder.put("status", StatusUtil.getStatus((Integer)adOrder.get("state")));
        	}
        }
        return adOrderPage;
	}
	
	@Override
	public void updateStatusById(Integer adOrderId, String status, Integer orState, Integer andState, Integer userId) {
		
		//1.save adOrder status, one adOrderId to one adOrder status
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", adOrderId);
		param.put("orState", orState);
		param.put("andState", andState);
		param.put("userId", userId);
		super.updateByMap(param);
		//2.write op log
		userOpLogService.sendLog(param, status.equals(Constants.Status.DELETE) ? "delete" : "change", "adorder");
		//3.update cache
		backendService.sendUpdateCacheMessage("adorder:" + adOrderId);
		//4.adOrder status changed occur cascade update
		if(status.equals(Constants.Status.DELETE)) {
			adService.deleteAdOrderId(adOrderId, userId);
		} else {
			adService.updateStatusByAdOrderId(adOrderId, status, StatusUtil.upgradeState("adorder", orState), ~StatusUtil.upgradeState("adorder", ~andState), userId);
		}
	}
	
	@Override
	public void updateStatusByIds(String ids, String status, Integer userId) {
		//there are many adOrderId in ids
		List<Integer> adOrderIdList = BaseUtils.str2list(ids);
		
		for(Integer adOrderId : adOrderIdList) {
			updateStatusById(adOrderId, status, StatusUtil.getState(status), StatusUtil.getRevertState(status), userId);
		}	
	}
	
	@Override
	public List<Integer> getAdOrderIdListByUserId(Integer userId) {
        List<Integer> result= super.findIds("userId", userId);
        return result;
	}
}
