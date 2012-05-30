package com.umeng.ufp.publisher.service.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.umeng.ufp.core.domain.UserActionLog;
import com.umeng.ufp.core.domain.UserOpLog;
import com.umeng.ufp.publisher.service.BackendService;
import com.umeng.ufp.publisher.service.MessageQueueService;

@Service
public class BackendServiceImpl implements BackendService {
	@Resource 
	MessageQueueService messageQueueService;
	
	public void sendUpdateCacheMessage(String key) {
		try {
			messageQueueService.sendMessage("Portal", "Cache", "Cache", key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendUserOpLogMessage(UserOpLog userOpLog) {
		try {
			messageQueueService.sendMessage("Portal", "OpLog", "UserOpLog", userOpLog.serialize());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void sendUserActionLogMessage(UserActionLog userActionLog) {
		try {
			messageQueueService.sendMessage("Portal", "ActionLog", "UserActionLog", userActionLog.serialize());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
