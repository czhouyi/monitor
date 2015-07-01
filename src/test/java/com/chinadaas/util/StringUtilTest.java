package com.chinadaas.util;

import junit.framework.TestCase;

/**
 * projectName: chinadaas-data<br>
 * desc: TODO<br>
 * date: 2015年4月2日 下午1:45:17<br>
 * @author 开发者真实姓名[Andy]
 */
public class StringUtilTest extends TestCase {

	public void testIsEmpty() {
		assertEquals(true, StringUtil.isEmpty(null));
		assertEquals(true, StringUtil.isEmpty(""));
		assertEquals(false, StringUtil.isEmpty("helloworld"));
	}
	
	public void testGetString() {
		assertEquals("", StringUtil.getString(null));
		assertEquals("helloworld", StringUtil.getString("helloworld"));
	}
	
	public void testUpperFirst() {
		assertEquals(null, StringUtil.upperFirst(null));
		assertEquals(null, StringUtil.upperFirst(""));
		assertEquals("Helloworld", StringUtil.upperFirst("helloworld"));
	}
}

