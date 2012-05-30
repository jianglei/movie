package com.umeng.ufp.publisher.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umeng.core.utils.BaseUtils;
import com.umeng.core.utils.Page;
import com.umeng.ufp.core.domain.Ad;
import com.umeng.ufp.core.domain.AdContent;
import com.umeng.ufp.core.domain.AdHolder;
import com.umeng.ufp.core.domain.AdOrder;
import com.umeng.ufp.core.domain.AdSlot;
import com.umeng.ufp.publisher.dao.EntityDaoImpl;
import com.umeng.ufp.publisher.service.AdContentService;
import com.umeng.ufp.publisher.service.AdHolderService;
import com.umeng.ufp.publisher.service.AdOrderService;
import com.umeng.ufp.publisher.service.AdService;
import com.umeng.ufp.publisher.service.AdSlotService;
import com.umeng.ufp.publisher.service.BackendService;
import com.umeng.ufp.publisher.service.UserOpLogService;
import com.umeng.ufp.publisher.utils.Constants;
import com.umeng.ufp.publisher.utils.StatusUtil;

@Service
public class AdServiceImpl extends EntityDaoImpl<Ad, Integer> implements
		AdService {
	@Resource
	protected AdHolderService adHolderService;
	@Resource
	protected AdSlotService adSlotService;
	@Resource
	protected AdOrderService adOrderService;
	@Resource
	protected AdContentService adContentService;
	@Resource
	protected BackendService backendService;
	@Resource
	protected UserOpLogService userOpLogService;

	@Override
	public Ad getById(int id) {
		return super.get(id);
	}

	@Override
	@Transactional(rollbackForClassName={"RuntimeException","Exception"})
	public void save(Ad ad, AdContent adContent, String []adSlotIds) {

		//1.save ad
		saveOrUpdate(ad);
    	
		//2.delete adholder
        adHolderService.updateAdSlotStatusByAdId(ad.getId(), Constants.Status.DELETE, Constants.STATE_DELETE, ~Constants.STATE_NORMAL, ad.getUserId());
        //3.build new relation between adSlot and ad
		Ad selectAd = getById(ad.getId());
		//4.user validate for ad
		if(ad.getUserId().equals(selectAd.getUserId()) && adSlotIds.length > 0) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("adSlotIdList", adSlotIds);
			List<AdSlot> selectAdSlotList = adSlotService.getAdSlotListByMap(param);
			for(AdSlot selectAdSlot : selectAdSlotList) {
				if(selectAdSlot.getUserId().equals(ad.getUserId())) {
		    		AdHolder adHolder = new AdHolder();
			    	adHolder.setAdId(selectAd.getId());
			    	adHolder.setAdSlotId(selectAdSlot.getId());
			    	adHolder.setAdState(selectAd.getState());
			    	adHolder.setAdSlotState(selectAdSlot.getState());
			    	adHolder.setUserId(selectAdSlot.getUserId());
			    	adHolderService.saveOrUpdate(adHolder);
	    		}
			}
		}

		//4.save adcontent
    	adContent.setId(ad.getId());
    	adContentService.saveOrUpdate(adContent);

	}
	
	@Override
	public void saveOrUpdate(Ad ad) {	
		String opType;
		opType = (ad.getId() == null ? "add" : "edit");	
	
		//check select adorder's user is the same with ad
		AdOrder adOrder = null;
		if(ad.getAdOrderId() != null) {
			adOrder= adOrderService.getById(ad.getAdOrderId());
			if(adOrder == null || !adOrder.getUserId().equals(ad.getUserId())) {
				ad.setAdOrderId(null);
			}
		}
		//1.save ad
		super.saveOrUpdate(ad);
		//2.write op log
		userOpLogService.sendLog(ad, opType);
		//3.update cache
		backendService.sendUpdateCacheMessage("ad:" + ad.getId());
		//4.judge ad status 
		Integer orState = Constants.STATE_NORMAL;
		Integer andState = ~(Constants.STATE_READY | Constants.STATE_FINISH);
		Date now = new Date();
		if(ad.getEndTime() != null && now.compareTo(ad.getEndTime()) > 0) {
			orState |= Constants.STATE_FINISH;
		} else if(ad.getStartTime() != null && now.compareTo(ad.getStartTime()) < 0) {
			orState |= Constants.STATE_READY;
		} else {
			orState |= Constants.STATE_NORMAL;
		}
		//5.update ad status by adorder status
		andState &= ~(Constants.STATE_ADORDER_PAUSE | Constants.STATE_ADORDER_FINISH 
				| Constants.STATE_ADORDER_READY);
		if(ad.getAdOrderId() != null) {
			orState |= StatusUtil.upgradeState("adorder", adOrder.getState());
		} 
		updateStatusById(ad.getId(), Constants.Status.NORMAL, orState, andState, ad.getUserId());
	}

	@Override
	public Page<Map<String, Object>> getAdListByPage(
			Page<Map<String, Object>> page) {
		Page<Map<String, Object>> adPage = super.findPageMap("listQueryPage", page);    
        for(Map<String, Object> ad : adPage.getResult()) {        		
        	if(ad.containsKey("state")) {
        		ad.put("status", StatusUtil.getStatus((Integer)ad.get("state")));
        	}
        }
		return adPage;
	}
	
	@Override
	public List<Map<String, Object>> getAdListByMap(Map<String, Object> param) {
		List<Map<String, Object>> adList = super.getMapListByMap("listQueryPage", param);
		for(Map<String, Object> ad : adList) {        		
        	if(ad.containsKey("state")) {
        		ad.put("status", StatusUtil.getStatus((Integer)ad.get("state")));
        	}
        }
		return adList;
	}
	
	@Override
	public void updateStatusById(Integer adId, String status, Integer orState, Integer andState, Integer userId) {
		//1.update ad_slot status by ad_slot_id
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", adId);
		param.put("orState", orState);
		param.put("andState", andState);
		param.put("userId", userId);
		super.updateByMap(param);
		//2.write op log
		userOpLogService.sendLog(param, status.equals(Constants.Status.DELETE) ? "delete" : "change", "ad");
		//3.update cache
		backendService.sendUpdateCacheMessage("ad:" + adId);
		//4.ad_slot status change occur adHolder's adslot_status update
		adHolderService.updateAdStatusByAdId(adId, status, orState, andState, userId);
	}
	@Override
	public void updateStatusByIds(String ids, String status, Integer userId) {
		// there are many adIds
		List<Integer> adIdList = BaseUtils.str2list(ids);
		updateStatusByIdList(adIdList, status, userId);
	}

	@Override
	public void updatePriorById(Integer adId, Integer prior, Integer userId) {
		// update ad
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", adId);
		param.put("prior", prior);
		param.put("userId", userId);
		super.updateByMap(param);
		//2.write op log
		userOpLogService.sendLog(param, "change", "ad");
		//3.update cache
		backendService.sendUpdateCacheMessage("ad:" + adId);
		//4.ad_slot status change occur adHolder's adslot_status update
		adHolderService.updatePriorByAdId(adId, prior, userId);
	}
	
	@Override
	public void deleteAdOrderId(Integer adOrderId, Integer userId) {
		List<Integer> adIdList = getAdIdListByAdOrderId(adOrderId);
		for(Integer adId : adIdList) {
			//1.set adOrderId to nil in ad by adId which is one to one
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", adId);
			param.put("userId", userId);
			//2.only ad_order_id
			super.delete(param);
			//3.write op log
			param.put("adOrderId", null);
			userOpLogService.sendLog(param, "trigger", "ad");
			//4.update cache and 
			backendService.sendUpdateCacheMessage("ad:" + adId);
			//5.update ad status
			Integer state = ~(Constants.STATE_ADORDER_FINISH | Constants.STATE_ADORDER_PAUSE |
					Constants.STATE_ADORDER_READY);
			updateStatusById(adId, Constants.Status.NORMAL, Constants.STATE_NORMAL, state, userId);
		}
	}

	@Override
	public void updateStatusByAdOrderId(Integer adOrderId, String status, Integer orState, Integer andState, Integer userId) {
		List<Integer> adIdList = getAdIdListByAdOrderId(adOrderId);
		for(Integer adId : adIdList) {
			updateStatusById(adId, status, orState, andState, userId);
		}
	}
	
	private void updateStatusByIdList(List<Integer> adIdList, String status, Integer userId) {
		for(Integer adId : adIdList) {
			updateStatusById(adId, status, StatusUtil.getState(status), StatusUtil.getRevertState(status), userId);
		}
	}
	
	@Override
	public List<Integer> getAdIdListByAdOrderId(Integer adOrderId) {
        List<Integer> result= super.findIds("adOrderId", adOrderId);
        return result;
	}

	@Override
	public List<Integer> getAdIdListByUserId(Integer userId) {
		List<Integer> result = super.findIds("userId", userId);
		return result;
	}

	@Override
	public Map<String, Object> getMapById(Integer adId) {
		Map<String, Object> map = super.getMapById("getMapById", adId);
		if (map == null)
			return null;
		map.put("promoter", map.get("id"));

		return map;
	}

}
