/**
 * 
 */
package com.umeng.ufp.publisher.service.impl;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.umeng.ufp.BaseTestCase;
import com.umeng.ufp.core.domain.App;
import com.umeng.ufp.publisher.service.AppService;

/**
 * @author yanshuyuan
 *
 */
public class AppServiceImplTest extends BaseTestCase {
	@Resource
	AppService appService;

	public AppService getAppService() {
		return appService;
	}
	
	@Test
	public void testGetById(){
		App app = appService.getById(1);
		
		Assert.assertNotNull(app);
	}
	
	@Test
	public void testSaveOrUpdate(){
		App app = new App();
		//app.setId(1); //test for insert or update
		app.setName("testAdmin");	
		app.setPlatform("ios");
		app.setDescription("just for test");
		
		appService.saveOrUpdate(app);
		
		App dbApp = appService.getById(app.getId());
		//Assert.assertEquals(dbApp.getId(), dbApp.getId()); //test for insert or update
		Assert.assertEquals(dbApp.getName(), dbApp.getName());
		Assert.assertEquals(dbApp.getPlatform(), dbApp.getPlatform());
		Assert.assertEquals(dbApp.getDescription(), dbApp.getDescription());
	}
	
	@Test
	public void testUpdateStatusByIds() {
		
	}
}
