/**
 * 
 */
package com.umeng.ufp.publisher.service.impl;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.umeng.ufp.BaseTestCase;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.publisher.service.UserService;

/**
 * @author kewang
 *
 */
public class UserServiceImplTest extends BaseTestCase {
	@Resource
	UserService userService;

	public UserService getUserService() {
		return userService;
	}
	
	@Test
	public void testGetUser(){
		User user = userService.getUser("admin", "21232f297a57a5a743894a0e4a801fc3");
		
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testGetById(){
		User user = userService.getById(1);
		
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testSaveOrUpdate(){
		User user = new User();
		user.setNickname("testGod");
		//user.setName("testAdmin");
		user.setEmail("testAdmin@umeng.com");
		user.setPassword("test");
		//user.setType("admin_op");
		//user.setPhone("110");
		
		userService.saveOrUpdate(user);
		
		User dbUser = userService.getById(user.getId());
		//Assert.assertEquals(dbUser.getName(), dbUser.getName());
		Assert.assertEquals(dbUser.getNickname(), dbUser.getNickname());
		Assert.assertEquals(dbUser.getEmail(), dbUser.getEmail());
		Assert.assertEquals(dbUser.getPassword(), dbUser.getPassword());
		//Assert.assertEquals(dbUser.getType(), dbUser.getType());
		//Assert.assertEquals(dbUser.getPhone(), dbUser.getPhone());
	}
}
