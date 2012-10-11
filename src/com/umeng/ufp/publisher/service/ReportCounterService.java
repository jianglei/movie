package com.umeng.ufp.publisher.service;

import java.util.List;

import com.umeng.ufp.core.domain.ReportCounter;

/**
 * @author yanshuyuan
 */
public interface ReportCounterService {
	/**
	 * ReportCounterfind
	 */
	public List<ReportCounter> findByAdHolderId(Integer adId, Integer adSlotId, String startDate, String endDate);
	
	public List<ReportCounter> findByTotal(List<Integer> adSlotIds, String startDate, String endDate, String category, String platform);
	public List<ReportCounter> findByAdId(List<Integer> adIds, Integer adId, String startDate, String endDate);
	public List<ReportCounter> findByAdSlotId(List<Integer> adSlotIds, Integer adSlotId, String startDate, String endDate, String category);
	public List<ReportCounter> findByAppId(List<Integer> appIds, Integer appId, String startDate, String endDate, String category);
	public List<ReportCounter> findByAdOrderId(List<Integer> adOrderIds, Integer adOrderId, String startDate, String endDate);

}
