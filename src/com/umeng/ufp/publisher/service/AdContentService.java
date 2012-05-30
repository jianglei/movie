package com.umeng.ufp.publisher.service;

import java.util.List;
import java.util.Map;

import com.umeng.core.utils.Page;
import com.umeng.ufp.core.domain.AdContent;

/**
 * @author yanshuyuan
 */
public interface AdContentService {
	/**
	 * AdContent find
	 */
	public AdContent getById(int id);
	/**
	 * update AdContent or save
	 */
    public void saveOrUpdate(AdContent adContent);
	/**
	 * AdContent list
	 */
	public Page<Map<String, Object>> getAdContentListByPage(Page<Map<String, Object>> page);
    
    public void updatePackage(String path);
	/**
	 * AdContent find
	 */
	public Map<String, Object> getMapById(int id);
	
	public List<Integer> getAdIdListByUserId(Integer userId);
}
