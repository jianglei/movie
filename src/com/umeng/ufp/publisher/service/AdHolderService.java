package com.umeng.ufp.publisher.service;

import java.util.List;
import java.util.Map;

import com.umeng.core.utils.Page;
import com.umeng.ufp.core.domain.AdHolder;

/**
 * @author yanshuyuan
 */
public interface AdHolderService {
	/**
	 * AdHolder find
	 */
	public AdHolder getByAdId(int adId);
	public List<Map<String, Object>> getMapListById(Integer adSlotId);
	
	public List<AdHolder> findByAdId(Integer adId);
	public List<AdHolder> findByAdSlotId(Integer adSlotId);
	public List<AdHolder> findByMap(Map<String, Object> param);
	public List<Integer> getAdIdListByAdSlotId(Integer adSlotId);
	public List<Integer> getAdSlotIdListByAdId(Integer adId);
	//TODO: convert Object to map
	public Map<Integer, Double> findMapByAdSlotId(Integer adSlotId);
	/**
	 * update AdHolder or save
	 */
    public void saveOrUpdate(AdHolder adHolder);
	/**
	 * AdHolder list
	 */
	public Page<Map<String, Object>> getAdHolderListByPage(Page<Map<String, Object>> page);
	

	public void updatePriorByAdId(Integer adId, Integer prior, Integer userId);
	public void updatePriorByAdSlotId(Integer adSlotId, Integer prior, Integer userId);
    
	public void updateAdStatusByAdSlotId(Integer adSlotId, String adStatus, Integer adOrState, Integer adAndState, Integer userId);
	public void updateAdSlotStatusByAdId(Integer adId, String adSlotStatus, Integer adSlotOrState, Integer adSlotAndState, Integer userId);
	public void updateAdStatusByAdId(Integer adId, String adStatus, Integer adOrState, Integer adAndState, Integer userId);
	public void updateAdSlotStatusByAdSlotId(Integer adSlotId, String adSlotStatus, Integer adSlotOrState, Integer adSlotAndState, Integer userId);
}
