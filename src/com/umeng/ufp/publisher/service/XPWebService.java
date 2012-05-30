/**
 * 
 */
package com.umeng.ufp.publisher.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpException;

/**
 * @author ke
 *
 */
public interface XPWebService {

	public List<Map<String, Object>> getXPPromoterReport(String appkey, String startDate, String endDate) throws HttpException, IOException;
	public Map<String, Object> grabAppStoreInfo(String appStoreId) throws HttpException, IOException;
}
