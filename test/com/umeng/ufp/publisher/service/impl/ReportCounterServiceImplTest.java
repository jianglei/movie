/**
 * 
 */
package com.umeng.ufp.publisher.service.impl;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.umeng.ufp.BaseTestCase;
import com.umeng.ufp.core.domain.ReportCounter;
import com.umeng.ufp.publisher.service.ReportCounterService;

/**
 * @author yanshuyuan
 *
 */
public class ReportCounterServiceImplTest extends BaseTestCase {
	@Resource
	ReportCounterService mReportCounterService;

	public ReportCounterService getReportCounterService() {
		return mReportCounterService;
	}
	
	/*@Test
	public void testFindByAdId(){
		int adId = 1;
		List<ReportCounter> reportCounterList = mReportCounterService.findByAdId(adId);
		Assert.assertNotNull(reportCounterList);
	}
	
	@Test
	public void testFindByAdSlotId() {
		int adSlotId = 1;
		List<ReportCounter> reportCounterList = mReportCounterService.findByAdId(adSlotId);
		Assert.assertNotNull(reportCounterList);
	}
	
	@Test
	public void testFindByAdHolderId() {
		int adSlotId = 1;
		int adId = 3;
		List<ReportCounter> reportCounterList = mReportCounterService.findByAdHolderId(adId, adSlotId);
		Assert.assertNotNull(reportCounterList);
	}*/
	
	
}
