package com.umeng.ufp.publisher.service;

import java.util.List;
import java.util.Map;

import com.umeng.core.utils.Page;
import com.umeng.ufp.core.domain.AdOrder;

/**
 * @author ke
 */
public interface AdOrderService {
	/**
	 * AdOrder find
	 */
	public AdOrder getById(int id);
	
	public Map<String, Object> getMapById(Integer adOrderId);
	/**
	 * update AdOrder or save
	 */
    public void saveOrUpdate(AdOrder adOrder);
	/**
	 * AdOrder list
	 */
	public Page<Map<String, Object>> getAdOrderListByPage(Page<Map<String, Object>> page);
	
	public List<Integer> getAdOrderIdListByUserId(Integer userId);
	/**
	 * update status
	 */
	public void updateStatusByIds(String ids, String value, Integer userId);
    
	public void updateStatusById(Integer adOrderId, String status,  Integer orState, Integer andState, Integer userId);
    
}
