package com.umeng.ufp.publisher.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.umeng.ufp.core.domain.ReportCounter;
import com.umeng.ufp.publisher.dao.EntityDaoImpl;
import com.umeng.ufp.publisher.service.ReportCounterService;

@Service
public class ReportCounterServiceImpl extends EntityDaoImpl<ReportCounter, Integer> implements ReportCounterService {

	public List<ReportCounter> findByAdHolderId(Integer adId, Integer adSlotId, String startDate, String endDate) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("adId", adId + "");
		param.put("adSlotId", adSlotId + "");
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		return super.find(param);
	}
	
	
	
	public List<ReportCounter> findByTotal(List<Integer> adSlotIds, String startDate, String endDate, String category) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("adSlotIds", adSlotIds);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("category", category);
		return super.find(param);
	}

	@Override
	public List<ReportCounter> findByAdId(List<Integer> adIds,
			Integer adId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("adIds", adIds);
		param.put("adId", adId + "");
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		return super.find(param);
	}

	@Override
	public List<ReportCounter> findByAdSlotId(List<Integer> adSlotIds,
			Integer adSlotId, String startDate, String endDate, String category) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("adSlotIds", adSlotIds);
		param.put("adSlotId", adSlotId + "");
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("category", category);
		return super.find(param);
	}

	@Override
	public List<ReportCounter> findByAppId(List<Integer> appIds,
			Integer appId, String startDate, String endDate, String category) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("appIds", appIds);
		param.put("appId", appId + "");
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("category", category);
		return super.find(param);
	}
	
	@Override
	public List<ReportCounter> findByAdOrderId(List<Integer> adOrderIds,
			Integer adOrderId, String startDate, String endDate) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("adOrderIds", adOrderIds);
		param.put("adOrderId", adOrderId + "");
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		return super.find(param);
	}
	
	
}
