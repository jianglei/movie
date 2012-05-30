package com.umeng.ufp.publisher.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpException;
import org.springframework.stereotype.Service;

import com.umeng.core.utils.net.HttpUtil;
import com.umeng.ufp.publisher.service.XPWebService;


@Service
public class XPWebServiceImpl implements XPWebService {
	private static final String XP_URL = "http://ex.mobmore.com";
	private static final String XP_UMENG_URL = "http://xp.umeng.com";
	private static final String XP_GRAB_APPSTORE_INFO = XP_UMENG_URL + "/service/grab_app_store?";
	private static final String XP_DATA_REPORT = XP_URL + "/api/xpromo/xp_report?";
	
	@Override
	public List<Map<String, Object>> getXPPromoterReport(String appkey, 
			String startDate, String endDate) throws HttpException, IOException {
		return getPromoterReport(appkey, startDate, endDate, XP_DATA_REPORT);
	}
	
	public Map<String, Object> grabAppStoreInfo(String appStoreId) throws HttpException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("app_store_id", appStoreId);
		return grabInfo(params, XP_GRAB_APPSTORE_INFO);
	}
	
	private List<Map<String, Object>> getPromoterReport(String appkey, 
			String startDate, String endDate, String urlPrefix) throws HttpException, IOException {
		List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>(0);
		Map<String, String> params = new HashMap<String, String>();
		params.put("app_key", appkey);
		params.put("start_date", startDate);
		params.put("end_date", endDate);
		
		String result = HttpUtil.sendGetRequestByParamsWithException(urlPrefix, params);
		//	System.out.println(result);
		reportList = convertToMapList(result);
		
		return reportList;
	}
	
	private Map<String, Object> grabInfo(Map<String, String> params, String urlPrefix) throws HttpException, IOException {
		Map<String, Object> resultParams = new HashMap<String, Object>();
		
		String result = HttpUtil.sendGetRequestByParamsWithException(urlPrefix, params);
		
		resultParams = convertToMap(result);
		
		return resultParams;
	}
	
	private Map<String, Object> convertToMap(String result) {
		Map<String, Object> resultParams = new HashMap<String, Object>();
		JSONObject output = JSONObject.fromObject(result);
		for (Object key : output.keySet()){
			resultParams.put(key.toString(), output.getString(key.toString()));
		}
		return resultParams;
	}

	private List<Map<String, Object>> convertToMapList(String result){
		List<Map<String, Object>> adsList = new ArrayList<Map<String, Object>>();
		
		JSONObject output = JSONObject.fromObject(result);

		String status = output.getString("status");
		if ("1".equals(status)) {
			JSONArray adArray = output.getJSONArray("promoters");
			for (int i = 0; i < adArray.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();

				JSONObject jo = adArray.getJSONObject(i);

				for (Object key : jo.keySet()){
					map.put(key.toString(), jo.getString(key.toString()));
				}
				
				adsList.add(map);
			}
		}
		
		return adsList;
	}
	

}
