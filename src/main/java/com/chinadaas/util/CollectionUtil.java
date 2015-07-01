package com.chinadaas.util;

import java.util.HashMap;
import java.util.Map;

/**
 * projectName: chinadaas-data<br>
 * desc: 集合工具类<br>
 * date: 2015年3月31日 下午4:48:19<br>
 * @author 开发者真实姓名[Andy]
 */
public class CollectionUtil {
	
	/**
	 * desc: 将map中的所有key转化为小写<br>
	 * date: 2015年4月2日 下午1:50:47<br>
	 * @author 开发者真实姓名[Andy]
	 * @param map
	 */
	public static void lowerKey(Map<String, Object> map) {
		Map<String, Object> newMap = new HashMap<String, Object>();
		for (String key : map.keySet()) {
			newMap.put(key.toLowerCase(), map.get(key));
		}
		map.clear();
		map.putAll(newMap);
	}
}

