package com.chinadaas.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * projectName: chinadaas-data<br>
 * desc: http工具类<br>
 * date: 2015年4月2日 上午9:13:48<br>
 * @author 开发者真实姓名[Andy]
 */
public class HttpUtil {
	
	/**
	 * desc: 初始化httpclient对象<br>
	 * date: 2015年4月2日 下午1:48:24<br>
	 * @author 开发者真实姓名[Andy]
	 * @return
	 */
	private static HttpClient getHttpClient() {
		HttpParams mHttpParams = new BasicHttpParams();
		// 设置网络链接超时
		HttpConnectionParams.setConnectionTimeout(mHttpParams, 20 * 1000);
		// 设置socket响应超时
		HttpConnectionParams.setSoTimeout(mHttpParams, 20 * 1000);
		// 设置socket缓存大小
		HttpConnectionParams.setSocketBufferSize(mHttpParams, 8 * 1024);
		// 设置是否可以重定向
		HttpClientParams.setRedirecting(mHttpParams, true);

		HttpClient httpClient = new DefaultHttpClient(mHttpParams);

		return httpClient;
	}
	
	/**
	 * desc: 获取URL访问返回的状态码<br>
	 * date: 2015年4月2日 下午1:48:44<br>
	 * @author 开发者真实姓名[Andy]
	 * @param url
	 * @return
	 */
	public static int getStatusCode(String url) {
		try {
			HttpGet get = new HttpGet(url);
			HttpResponse httpResponse = getHttpClient().execute(get);
			return httpResponse.getStatusLine().getStatusCode();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return HttpStatus.SC_NOT_FOUND;
	}
	
	/**
	 * desc: 获取URL访问返回的JSON对象<br>
	 * date: 2015年4月2日 下午1:48:47<br>
	 * @author 开发者真实姓名[Andy]
	 * @param url
	 * @return
	 */
	public static JSONObject getJSON(String url) {
		JSONObject json = null;
		try {
			HttpGet get = new HttpGet(url);
			HttpResponse httpResponse = getHttpClient().execute(get);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				JSONParser parser = new JSONParser();
				json = (JSONObject) parser.parse(new BufferedReader(new InputStreamReader(entity.getContent(), HTTP.UTF_8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return json;
	}

}

