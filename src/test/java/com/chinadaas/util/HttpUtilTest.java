package com.chinadaas.util;

import org.apache.commons.httpclient.HttpStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import junit.framework.TestCase;

/**
 * projectName: chinadaas-data<br>
 * desc: http工具测试类<br>
 * date: 2015年4月2日 上午9:21:26<br>
 * @author 开发者真实姓名[Andy]
 */
public class HttpUtilTest extends TestCase {
	
	public void testGetStatusCode() {
		// 服务器dns问题导致测试失败
		//assertEquals(HttpStatus.SC_OK, HttpUtil.getStatusCode("http://www.baidu.com"));
		//assertEquals(HttpStatus.SC_NOT_FOUND, HttpUtil.getStatusCode("http://www.chinadaas.com/111"));
		//assertEquals(HttpStatus.SC_OK, HttpUtil.getStatusCode("http://192.168.11.11:7778/gsinfo"));
	}
	
	public void testGetJSON() {
		//JSONObject json = HttpUtil.getJSON("http://192.168.11.12:50070/jmx?qry=Hadoop:service=NameNode,name=NameNodeInfo");
		//JSONArray array = (JSONArray) json.get("beans");
		//assertEquals(true, array.size() > 0);
	}
}

