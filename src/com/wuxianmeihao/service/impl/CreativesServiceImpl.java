package com.wuxianmeihao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.umeng.core.utils.BaseUtils;
import com.umeng.core.utils.Page;
import com.wuxianmeihao.core.domain.Creatives;
import com.wuxianmeihao.dao.EntityDaoImpl;
import com.wuxianmeihao.service.CreativesService;
import com.wuxianmeihao.utils.StatusUtil;

@Service
public class CreativesServiceImpl extends EntityDaoImpl<Creatives, Integer> implements
		CreativesService {
//	@Resource
//	protected AdHolderService adHolderService;


	@Override
	public Creatives getById(int id) {
		return super.get(id);
	}

	
	
	@Override
	public void saveOrUpdate(Creatives creatives) {	
		String opType;
		opType = (creatives.getId() == null ? "add" : "edit");	
	
		//check select adorder's user is the same with ad
//		AdOrder adOrder = null;
//		if(ad.getAdOrderId() != null) {
//			adOrder= adOrderService.getById(ad.getAdOrderId());
//			if(adOrder == null || !adOrder.getUserId().equals(ad.getUserId())) {
//				ad.setAdOrderId(null);
//			}
//		}
		//1.save ad
		super.saveOrUpdate(creatives);
//		//2.write op log
//		userOpLogService.sendLog(ad, opType);
//		//3.update cache
//		backendService.sendUpdateCacheMessage("ad:" + ad.getId());
		//4.judge ad status 
//		Integer orState = Constants.STATE_NORMAL;
//		Integer andState = ~(Constants.STATE_READY | Constants.STATE_FINISH);
//		Date now = new Date();
//		if(ad.getEndTime() != null && now.compareTo(ad.getEndTime()) > 0) {
//			orState |= Constants.STATE_FINISH;
//		} else if(ad.getStartTime() != null && now.compareTo(ad.getStartTime()) < 0) {
//			orState |= Constants.STATE_READY;
//		} else {
//			orState |= Constants.STATE_NORMAL;
//		}
//		//5.update ad status by adorder status
//		andState &= ~(Constants.STATE_ADORDER_PAUSE | Constants.STATE_ADORDER_FINISH 
//				| Constants.STATE_ADORDER_READY);
//		if(ad.getAdOrderId() != null) {
//			orState |= StatusUtil.upgradeState("adorder", adOrder.getState());
//		} 
//		updateStatusById(ad.getId(), Constants.Status.NORMAL, orState, andState, ad.getUserId());
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
//		for(Map<String, Object> ad : adList) {        		
//        	if(ad.containsKey("state")) {
//        		ad.put("status", StatusUtil.getStatus((Integer)ad.get("state")));
//        	}
//        }
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
//		//2.write op log
//		userOpLogService.sendLog(param, status.equals(Constants.Status.DELETE) ? "delete" : "change", "ad");
//		//3.update cache
//		backendService.sendUpdateCacheMessage("ad:" + adId);
//		//4.ad_slot status change occur adHolder's adslot_status update
//		adHolderService.updateAdStatusByAdId(adId, status, orState, andState, userId);
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
//		//2.write op log
//		userOpLogService.sendLog(param, "change", "ad");
//		//3.update cache
//		backendService.sendUpdateCacheMessage("ad:" + adId);
//		//4.ad_slot status change occur adHolder's adslot_status update
//		adHolderService.updatePriorByAdId(adId, prior, userId);
	}
	
	@Override
	public void deleteAdOrderId(Integer adOrderId, Integer userId) {
//		List<Integer> adIdList = getAdIdListByAdOrderId(adOrderId);
//		for(Integer adId : adIdList) {
//			//1.set adOrderId to nil in ad by adId which is one to one
//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put("id", adId);
//			param.put("userId", userId);
//			//2.only ad_order_id
//			super.delete(param);
//			//3.write op log
//			param.put("adOrderId", null);
//			userOpLogService.sendLog(param, "trigger", "ad");
//			//4.update cache and 
//			backendService.sendUpdateCacheMessage("ad:" + adId);
//			//5.update ad status
//			Integer state = ~(Constants.STATE_ADORDER_FINISH | Constants.STATE_ADORDER_PAUSE |
//					Constants.STATE_ADORDER_READY);
//			updateStatusById(adId, Constants.Status.NORMAL, Constants.STATE_NORMAL, state, userId);
//		}
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
