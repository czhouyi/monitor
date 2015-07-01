package com.chinadaas.util;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * projectName: chinadaas-data<br>
 * desc: TODO<br>
 * date: 2015年4月2日 上午9:37:28<br>
 * @author 开发者真实姓名[Andy]
 */
public class CollectionUtilTest extends TestCase {
	
	public void testLowerKey() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Jack", "jack");
		map.put("tOM", "tom");
		CollectionUtil.lowerKey(map);
		assertEquals("jack", map.get("jack"));
		assertEquals("tom", map.get("tom"));
	}

}

