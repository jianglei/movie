package com.umeng.ufp.publisher.service;

import java.util.List;
import java.util.Map;

import com.umeng.core.utils.Page;
import com.umeng.ufp.core.domain.AdSlot;

/**
 * @author ke
 *
 */
public interface AdSlotService {
	
	/**
	 * AdSlot find
	 */
	public AdSlot getById(int id);
	public Map<String, Object> getMapById(Integer id);
	/**
	 * update AdSlot or save
	 */
    public void saveOrUpdate(AdSlot adSlot);
	/**
	 * AdSlot list
	 */
	public Page<Map<String, Object>> getAdSlotListByPage(Page<Map<String, Object>> page);
	/**
	 * AdSlot Id list
	 */
	public List<AdSlot> getAdSlotListByMap(Map<String, Object> param);
	public List<Integer> getAdSlotIdListByAppId(Integer appId);
	
	public List<Integer> getAdSlotIdListByUserId(Integer userId);
	
	public Integer count(Map<String, Object> param);
	/**
	 * update status
	 */
	public void deleteAppId(Integer appId, Integer userId);
    
    public void updateStatusByAppId(Integer appId, String status, Integer orState, Integer andState, Integer userId);
    public void updateStatusByIdList(List<Integer> adSlotIdList, String status, Integer userId);
    public void updateStatusByIds(String ids, String status, Integer userId); 
    public void updateStatusById(Integer id, String status,  Integer orState, Integer andState, Integer userId); 
    
}
