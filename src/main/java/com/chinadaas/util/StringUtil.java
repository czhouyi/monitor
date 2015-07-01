package com.chinadaas.util;

/**
 * projectName: chinadaas-data<br>
 * desc: 字符串工具类<br>
 * date: 2015年4月2日 下午1:12:11<br>
 * @author 开发者真实姓名[Andy]
 */
public class StringUtil {
	
	/**
	 * desc: 字符串是否为空<br>
	 * date: 2015年4月2日 下午1:47:44<br>
	 * @author 开发者真实姓名[Andy]
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	/**
	 * desc: 获取对象字符串<br>
	 * date: 2015年4月2日 下午1:47:57<br>
	 * @author 开发者真实姓名[Andy]
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	
	/**
	 * desc: 首字母大写<br>
	 * date: 2015年4月3日 下午2:04:37<br>
	 * @author 开发者真实姓名[Andy]
	 * @param str
	 * @return
	 */
	public static String upperFirst(String str) {
		if (isEmpty(str)) {
			return null;
		}
		StringBuffer buffer = new StringBuffer(str);
		buffer.setCharAt(0, Character.toUpperCase(buffer.charAt(0)));
		return buffer.toString();
	}

}

