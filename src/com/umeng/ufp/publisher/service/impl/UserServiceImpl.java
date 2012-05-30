package com.umeng.ufp.publisher.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.umeng.core.utils.BaseUtils;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.core.domain.UserOpLog;
import com.umeng.ufp.publisher.dao.EntityDaoImpl;
import com.umeng.ufp.publisher.service.BackendService;
import com.umeng.ufp.publisher.service.UserOpLogService;
import com.umeng.ufp.publisher.service.UserService;

@Service
public class UserServiceImpl  extends EntityDaoImpl<User, Integer> implements UserService {
	@Resource
	protected BackendService backendService;
	@Resource
	protected UserOpLogService userOpLogService;
	
	@Override
	public User getById(int id) {
		return super.get(id);
	}
	
	public Map<String, Object> getMapById(Integer id) {
		Map<String, Object> map = super.getMapById("getMapById", id);
		return map;
	}
	
	@Override
	public User getUser(String email, String passwd) {
		List<User> users = new ArrayList<User>();
        users = super.find(BaseUtils.createQueryParam("email,password", email, passwd));
        User user = null;
        if (users.size() > 0) {
            user = users.get(0);
        }
        return user;
	}
	
    public void saveOrUpdate(User user){

		String opType;
		opType = (user.getId() == null ? "add" : "edit");
		
		//1. save user
        super.saveOrUpdate(user);
		//2.write op log
		userOpLogService.sendLog(user, opType);
		//3.update cache
        backendService.sendUpdateCacheMessage("user:" + user.getId());
    }
}
