/**
 * 
 */
package com.umeng.ufp.publisher.service;

import com.umeng.ufp.core.domain.UserActionLog;
import com.umeng.ufp.core.domain.UserOpLog;


/**
 * @author ke
 *
 */
public interface BackendService {
	
	public void sendUpdateCacheMessage(String key);
	public void sendUserOpLogMessage(UserOpLog userOpLog);
	public void sendUserActionLogMessage(UserActionLog userActionLog);
}
