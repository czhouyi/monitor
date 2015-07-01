package com.chinadaas.util;

import junit.framework.TestCase;

/**
 * projectName: chinadaas-data<br>
 * desc: TODO<br>
 * date: 2015年4月2日 下午1:51:43<br>
 * @author 开发者真实姓名[Andy]
 */
public class RegexUtilTest extends TestCase {

	public void testIsIP() {
		assertEquals(true, RegexUtil.isIP("10.0.0.1"));
		assertEquals(true, RegexUtil.isIP("192.168.11.2"));
		assertEquals(false, RegexUtil.isIP("192.168.11.1."));
		assertEquals(false, RegexUtil.isIP("192.168.11.300"));
		assertEquals(false, RegexUtil.isIP("0.168.11.30"));
		assertEquals(false, RegexUtil.isIP("19a.168.11.2"));
	}
	
	public void testIsID() {
		assertEquals(false, RegexUtil.isId("37092252627193X"));
		assertEquals(true, RegexUtil.isId("410102198309261518"));
		assertEquals(false, RegexUtil.isId("410102198309261517"));
	}
	
	public void testIsEmail() {
		assertEquals(false, RegexUtil.isEmail("o21xsa._@1com"));
		assertEquals(true, RegexUtil.isEmail("o21xsa._@1.com"));
		assertEquals(false, RegexUtil.isEmail("o21x163.com"));
	}
	
	public void testIsMobile() {
		assertEquals(false, RegexUtil.isMobile("14723482834"));
		assertEquals(true, RegexUtil.isMobile("13723482834"));
		assertEquals(false, RegexUtil.isMobile("137234828341"));
	}
}

