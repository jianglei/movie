package com.wuxianmeihao.service;

import java.util.List;
import java.util.Map;

import com.umeng.core.utils.Page;
import com.wuxianmeihao.core.domain.Creatives;

/**
 * @author ke
 */
public interface CreativesService {
	public Creatives getById(int id);
	public Map<String, Object> getMapById(Integer adId);
	
	public Page<Map<String, Object>> getAdListByPage(Page<Map<String, Object>> page);
	public List<Map<String, Object>> getAdListByMap(Map<String, Object> param);

	public List<Integer> getAdIdListByUserId(Integer userId);
	public List<Integer> getAdIdListByAdOrderId(Integer adOrderId);

    public void saveOrUpdate(Creatives creatives);
	
	public void updateStatusByIds(String ids, String status, Integer userId);
	public void updateStatusByAdOrderId(Integer adOrderId, String status, Integer orState, Integer andState, Integer userId);
	public void updateStatusById(Integer adId, String status,  Integer orState, Integer andState, Integer userId);
	
    public void updatePriorById(Integer id, Integer prior, Integer userId);
    

	public void deleteAdOrderId(Integer adOrderId, Integer userId);
	
}
