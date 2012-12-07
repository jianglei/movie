package com.umeng.core.utils.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.ContentEncodingHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Joiner;
import com.umeng.core.utils.StringUtil;

public class HttpUtil {

	/**
	 * 中文参数解码
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decode(String str) {
		try {
			return URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encode(String str) {
		try {
			return URLEncoder.encode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void sendGetRequestNoResponseByParams(String urlPrefix, Map<String, String> params) {
		String url = createGetRequestUrl(urlPrefix, params);
		System.out.println(url);
		sendGetRequestNoResponse(url);
	}
	
	public static void sendGetRequestNoResponse(String url) {
		sendGetRequest(url, true, false);
	}
	
	public static String sendGetRequestByParams(String urlPrefix, Map<String, String> params) {
		String url = createGetRequestUrl(urlPrefix, params);
		return sendGetRequest(url);
	}

	public static String sendGetRequest(String url) {
		return sendGetRequest(url, true, true);
	}

	private static String sendGetRequest(String url, boolean appendRand,
			boolean withResult) {
		String result = new String();
		try {
			result = sendGetRequestWithException(url, appendRand, withResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String sendGetRequestByParamsWithException(String urlPrefix, Map<String, String> params) 
			throws HttpException, IOException {
		String url = createGetRequestUrl(urlPrefix, params);
		return sendGetRequestWithException(url);
	}
	
	public static String sendGetRequestWithException(String url)
			throws HttpException, IOException {
		return sendGetRequestWithException(url, true, true);
	}
	
	private static String sendGetRequestWithException(String url,
			boolean appendRand, boolean withResult) throws HttpException,
			IOException {
		
		if (appendRand) {
			if (url.indexOf('?') == -1) {
				url += "?";
			}
			url += "&_rand=" + Math.random();
		}
		String result = "";

		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 30000);
		HttpConnectionParams.setSoTimeout(httpParameters, 30000);
		
		//ContentEncodingHttpClient like setHeader("Accept-Encoding", "gzip,deflate...");
		HttpClient httpClient = new ContentEncodingHttpClient(httpParameters);		
		
		try {
			HttpGet httpget = new HttpGet(url);
			
			HttpResponse response = httpClient.execute(httpget);
			
			if (withResult) {
				HttpEntity entity = response.getEntity();
				if(entity!=null) {
				    result = EntityUtils.toString(entity);
//				    System.out.println(result.length());
				}
			}
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		return result;
	}
	
	public static String createGetRequestUrl(String urlPrefix, Map<String, String> params) {
		//enquote value
		Map<String, String> urlParams = new HashMap<String, String>();
		for (String k : params.keySet()){
			if(StringUtil.isNotEmpty(params.get(k))) {
				urlParams.put(k, encode(params.get(k)));
			}
		}
		//send url
		String url = urlPrefix + Joiner.on("&").withKeyValueSeparator("=").join(urlParams);
		return url;
	}

	

}
