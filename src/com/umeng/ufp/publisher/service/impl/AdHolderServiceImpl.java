package com.umeng.ufp.publisher.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.base.Joiner;
import com.umeng.core.utils.Page;
import com.umeng.ufp.core.domain.AdHolder;
import com.umeng.ufp.publisher.dao.EntityDaoImpl;
import com.umeng.ufp.publisher.service.AdHolderService;
import com.umeng.ufp.publisher.service.BackendService;
import com.umeng.ufp.publisher.service.UserOpLogService;
import com.umeng.ufp.publisher.utils.Constants;
import com.umeng.ufp.publisher.utils.StatusUtil;

@Service
public class AdHolderServiceImpl extends EntityDaoImpl<AdHolder, Integer> implements AdHolderService {
	@Resource
	protected BackendService backendService;
	@Resource
	protected UserOpLogService userOpLogService;
	
	@Override
	public AdHolder getByAdId(int adId) {
		return super.get(adId);
	}
	
	@Override
	public List<Map<String, Object>> getMapListById(Integer adSlotId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("adSlotId", adSlotId);
		return super.getMapListByMap("getMapListByMap", param);
	}
	
	@Override
	public List<AdHolder> findByAdId(Integer adId) {
		return super.findBy("adId", adId);
	}
	

	@Override
	public List<AdHolder> findByAdSlotId(Integer adSlotId) {
		return super.findBy("adSlotId", adSlotId);
	}
	@Override
	public List<AdHolder> findByMap(Map<String, Object> param) {
		return super.find(param);
	}
	@Override
	public List<Integer> getAdIdListByAdSlotId(Integer adSlotId) {
		return super.findIds("adSlotId", adSlotId);
	}
	@Override
	public List<Integer> getAdSlotIdListByAdId(Integer adId) {
		return super.findIds("adId", adId);
	}
	
	@Override
	public Map<Integer, Double> findMapByAdSlotId(Integer adSlotId){
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("adSlotId", adSlotId);
		param.put("adState", Constants.STATE_NORMAL);
		param.put("adSlotState", Constants.STATE_NORMAL);
		List<AdHolder> list = findByMap(param);
		for (AdHolder adHolder : list){
			map.put(adHolder.getAdId(), adHolder.getWeight());
		}
		
		return map;
	}


	@Override
	public Page<Map<String, Object>> getAdHolderListByPage(
			Page<Map<String, Object>> page) {
        Page<Map<String, Object>> adHolderPage=super.findPageMap("listQueryPage", page);        
        for(Map<String, Object> adHolder : adHolderPage.getResult()) {        		
        	if(adHolder.containsKey("ad_state") && adHolder.containsKey("adslot_state")) {
        		Integer adState = Integer.parseInt(adHolder.get("ad_state").toString());
        		Integer adSlotState = Integer.parseInt(adHolder.get("adslot_state").toString());
        		adHolder.put("ad_status", StatusUtil.getStatus(adState));
        		adHolder.put("adslot_status", StatusUtil.getStatus(adSlotState));
        	}
        }
        return adHolderPage;
	}
	
	
	@Override
	public void saveOrUpdate(AdHolder adHolder) {
		//1.save adHolder
		super.saveOrUpdate(adHolder);
		//2.write op log
	    userOpLogService.sendLog(adHolder, "relate");
		//3.update cache
		backendService.sendUpdateCacheMessage("adholder:" + adHolder.getAdSlotId() + ":" + adHolder.getAdId());
	}


	@Override
	public void updateAdStatusByAdSlotId(Integer adSlotId, String adStatus, Integer adOrState, Integer adAndState, Integer userId) {
		//one adSlotId to many adIds
		List<Integer> adIdList = getAdIdListByAdSlotId(adSlotId);
		updateAdStatusByAdIdList(adIdList, adSlotId, adStatus, adOrState, adAndState, userId);
	}

	
	@Override
	public void updateAdStatusByAdId(Integer adId, String adStatus, Integer adOrState, Integer adAndState, Integer userId) {
		//one adId to many adSlotIds
		List<Integer> adSlotIdList = getAdSlotIdListByAdId(adId);
		updateAdStatusByAdSlotIdList(adSlotIdList, adId, adStatus, adOrState, adAndState, userId);
	}

	@Override
	public void updateAdSlotStatusByAdSlotId(Integer adSlotId, String adSlotStatus, Integer adSlotOrState, Integer adSlotAndState, Integer userId) {
		//one adSlotId to many adIds
		List<Integer> adIdList = getAdIdListByAdSlotId(adSlotId);
		updateAdSlotStatusByAdIdList(adIdList, adSlotId, adSlotStatus, adSlotOrState, adSlotAndState, userId);
	}
	
	@Override
	public void updateAdSlotStatusByAdId(Integer adId, String adSlotStatus, Integer adSlotOrState, Integer adSlotAndState, Integer userId) {
		//one adId to many adSlotIds
		List<Integer> adSlotIdList = getAdSlotIdListByAdId(adId);
		updateAdSlotStatusByAdSlotIdList(adSlotIdList, adId, adSlotStatus, adSlotOrState, adSlotAndState, userId);
	}
	
	
	private void updateStatus(List<Integer> adSlotIdList, List<Integer> adIdList, String adSlotStatus, 
			String adStatus, Integer adSlotOrState, Integer adSlotAndState, Integer adOrState, Integer adAndState, Integer userId) {
		//1.update adHolder, which is a batch operator by adSlotIdList/adIdList 
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("adSlotIdList", (adSlotIdList == null || adSlotIdList.size() == 0) ? null : adSlotIdList);
		param.put("adIdList", (adIdList == null || adIdList.size() == 0) ? null : adIdList);
		param.put("adSlotOrState", adSlotOrState);
		param.put("adOrState", adOrState);
		param.put("adSlotAndState", adSlotAndState);
		param.put("adAndState", adAndState);
		param.put("userId", userId);
		super.updateByMap(param);
		//2.write op log

		param.put("id", Joiner.on(",").join(adSlotIdList) + ":" + Joiner.on(",").join(adIdList));
		userOpLogService.sendLog(param, (Constants.Status.DELETE.equals(adSlotStatus) || Constants.Status.DELETE.equals(adStatus)) ? "unrelate" : "trigger", "adholder");
		//3.update cache
		backendService.sendUpdateCacheMessage("adholder:" + Joiner.on(",").join(adSlotIdList) + ":"+ Joiner.on(",").join(adIdList));
		
	}
	private void updateAdStatusByAdIdList(List<Integer> adIdList, Integer adSlotId, String adStatus, Integer adOrState, Integer adAndState, Integer userId) {
		List<Integer> adSlotIdList = new ArrayList<Integer>();
		adSlotIdList.add(adSlotId);
		updateStatus(adSlotIdList, adIdList, null, adStatus, null, null, adOrState, adAndState, userId);
	}
	private void updateAdStatusByAdSlotIdList(List<Integer> adSlotIdList, Integer adId, String adStatus, Integer adOrState, Integer adAndState, Integer userId) {
		List<Integer> adIdList = new ArrayList<Integer>();
		adIdList.add(adId);
		updateStatus(adSlotIdList, adIdList, null, adStatus, null, null, adOrState, adAndState, userId);	
	}
	private void updateAdSlotStatusByAdIdList(List<Integer> adIdList, Integer adSlotId, String adSlotStatus, Integer adSlotOrState, Integer adSlotAndState, Integer userId) {
		List<Integer> adSlotIdList = new ArrayList<Integer>();
		adSlotIdList.add(adSlotId);
		updateStatus(adSlotIdList, adIdList, adSlotStatus, null, adSlotOrState, adSlotAndState, null, null, userId);
	}
	private void updateAdSlotStatusByAdSlotIdList(List<Integer> adSlotIdList, Integer adId, String adSlotStatus, Integer adSlotOrState, Integer adSlotAndState, Integer userId) {
		List<Integer> adIdList = new ArrayList<Integer>();
		adIdList.add(adId);
		updateStatus(adSlotIdList, adIdList,  adSlotStatus, null, adSlotOrState, adSlotAndState, null, null, userId);
	}
	
	private void updatePrior(List<Integer> adSlotIdList, List<Integer> adIdList, Integer prior, Integer userId) {
		//update ad
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("adSlotIdList", (adSlotIdList == null || adSlotIdList.size() == 0) ? null : adSlotIdList);
		param.put("adIdList", (adIdList == null || adIdList.size() == 0) ? null : adIdList);
		param.put("prior", prior);
		param.put("userId", userId);
		super.updateByMap(param);
		//2.write op log
		param.put("id", Joiner.on(",").join(adSlotIdList) + ":" + Joiner.on(",").join(adIdList));
		userOpLogService.sendLog(param, "trigger", "adholder");
		//3.update cache
		backendService.sendUpdateCacheMessage("adholder:" + Joiner.on(",").join(adSlotIdList) + ":"+ Joiner.on(",").join(adIdList));
	}

	@Override
	public void updatePriorByAdId(Integer adId, Integer prior, Integer userId) {
		//one adId to many adSlotIds
		List<Integer> adSlotIdList = getAdSlotIdListByAdId(adId);
		updatePriorByAdSlotIdList(adSlotIdList, adId, prior, userId);
	}
	@Override
	public void updatePriorByAdSlotId(Integer adSlotId, Integer prior, Integer userId) {
		//one adSlotId to many adIds
		List<Integer> adIdList = getAdIdListByAdSlotId(adSlotId);
		updatePriorByAdIdList(adIdList, adSlotId, prior, userId);
	}
	
	private void updatePriorByAdIdList(List<Integer> adIdList, Integer adSlotId, Integer prior, Integer userId) {
		List<Integer> adSlotIdList = new ArrayList<Integer>();
		adIdList.add(adSlotId);
		updatePrior(adSlotIdList, adIdList, prior, userId);
	}
	private void updatePriorByAdSlotIdList(List<Integer> adSlotIdList, Integer adId, Integer prior, Integer userId) {
		List<Integer> adIdList = new ArrayList<Integer>();
		adIdList.add(adId);
		updatePrior(adSlotIdList, adIdList, prior, userId);
	}
	


}
