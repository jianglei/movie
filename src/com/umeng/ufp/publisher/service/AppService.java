package com.umeng.ufp.publisher.service;

import java.util.List;
import java.util.Map;

import com.umeng.core.utils.Page;
import com.umeng.ufp.core.domain.App;

/**
 * @author ke
 *
 */
public interface AppService {
	
	/**
	 * App find
	 */
	public App getById(int id);
	/**
	 * update App or save
	 */
    public void saveOrUpdate(App app);
	/**
	 * App list
	 */
	public Page<Map<String, Object>> getAppListByPage(Page<Map<String, Object>> page);
	
	public void updateStatusByIds(String ids, String status, Integer userId);
	public void updateStatusById(Integer id, String status, Integer orState, Integer andState, Integer userId);
	
	public List<Integer> getAppIdListByUserId(Integer userId);

}
