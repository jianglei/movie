package com.umeng.ufp.publisher.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.umeng.core.utils.Page;
import com.umeng.ufp.core.domain.AdContent;
import com.umeng.ufp.core.domain.UserOpLog;
import com.umeng.ufp.publisher.dao.EntityDaoImpl;
import com.umeng.ufp.publisher.service.AdContentService;
import com.umeng.ufp.publisher.service.BackendService;
import com.umeng.ufp.publisher.service.UserOpLogService;

@Service
public class AdContentServiceImpl  extends EntityDaoImpl<AdContent, Integer> implements AdContentService {
	@Resource
	protected BackendService backendService;
	@Resource
	protected UserOpLogService userOpLogService;
	
	@Override
	public AdContent getById(int id) {
		return super.get(id);
	}

	@Override
	public void saveOrUpdate(AdContent adContent) {
		//1.save adContent
		super.saveOrUpdate(adContent);
		//2.write op loguser
		userOpLogService.sendLog(adContent, "padding");
		//3.update cache
		backendService.sendUpdateCacheMessage("adcontent:" + adContent.getId());
	}

	@Override
	public Page<Map<String, Object>> getAdContentListByPage(
			Page<Map<String, Object>> page) {
        Page<Map<String, Object>> result=super.findPageMap("listQueryPage", page);
        return result;
	}

	@Override
	public void updatePackage(String path) {
		// TODO Auto-generated method stub
		
	}
	
	public Map<String, Object> getMapById(int id) {
		Map<String, Object> map = super.getMapById("getMapById", id);
		if (map == null)		return null;
		map.put("promoter", map.get("id"));
		
		return map;
	}
	
	@Override
	public List<Integer> getAdIdListByUserId(Integer userId) {
        List<Integer> result= super.findIds("userId", userId);
        return result;
	}

}
