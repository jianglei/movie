package com.umeng.ufp.publisher.service;

import java.util.Map;

import com.umeng.ufp.core.domain.User;

/**
 * @author ke
 *
 */
public interface UserService {
	public User getById(int id);
	public Map<String, Object> getMapById(Integer id);
	public User getUser(String loginName,String passwd);
	public void saveOrUpdate(User user);
}
