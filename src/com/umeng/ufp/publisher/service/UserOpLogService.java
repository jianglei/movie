package com.umeng.ufp.publisher.service;

import java.util.Map;

import com.umeng.ufp.core.domain.Ad;
import com.umeng.ufp.core.domain.AdContent;
import com.umeng.ufp.core.domain.AdHolder;
import com.umeng.ufp.core.domain.AdOrder;
import com.umeng.ufp.core.domain.AdSlot;
import com.umeng.ufp.core.domain.App;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.core.domain.UserOpLog;

/**
 * @author ke
 *
 */
public interface UserOpLogService {
	public void sendLog(App app, String opType);
	public void sendLog(AdSlot adSlot, String opType);
	public void sendLog(Ad ad, String opType);
	public void sendLog(AdContent adContent, String opType);
	public void sendLog(AdOrder adOrder, String opType);
	public void sendLog(User user, String opType);
	public void sendLog(AdHolder adHolder, String opType);
	public void sendLog(Map<String, Object> param, String opType, String objectType);
}
