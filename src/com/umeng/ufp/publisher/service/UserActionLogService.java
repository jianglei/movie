package com.umeng.ufp.publisher.service;

import java.util.List;
import java.util.Map;

import com.umeng.ufp.core.domain.Ad;
import com.umeng.ufp.core.domain.AdContent;
import com.umeng.ufp.core.domain.AdOrder;
import com.umeng.ufp.core.domain.AdSlot;
import com.umeng.ufp.core.domain.App;
import com.umeng.ufp.core.domain.User;
import com.umeng.ufp.core.domain.UserActionLog;

/**
 * @author ke
 *
 */
public interface UserActionLogService {
	public void sendLog(Integer appId, Integer operatorId, App oldApp, App newApp, String opType);
	public void sendLog(Integer adSlotId, Integer operatorId, AdSlot oldAdSlot, AdSlot newAdSlot, String opType);
	public void sendLog(Integer adId, Integer operatorId, Ad oldAd, Ad newAd, String opType);
	public void sendLog(Integer adContentId, Integer operatorId, AdContent oldAdContent, AdContent newAdContent, String opType);
	public void sendLog(Integer adOrderId, Integer operatorId, AdOrder oldAdOrder, AdOrder newAdOrder, String opType);
	public void sendLog(Integer userId, Integer operatorId, User oldUser, User newUser, String opType);

	public List<UserActionLog> findActionLog(Integer userId, String startDate, String endDate, Map<String, Object> extraParam);
	
}
