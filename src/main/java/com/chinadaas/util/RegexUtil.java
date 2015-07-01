package com.chinadaas.util;

import java.util.regex.Pattern;

/**
 * projectName: chinadaas-data<br>
 * desc: 正则模式匹配工具类<br>
 * date: 2015年4月1日 下午12:55:10<br>
 * @author 开发者真实姓名[Andy]
 */
public class RegexUtil {
	
	/** 身份证加权因子 */
	private static final Integer[] ID_CARD_WEIGHT = new Integer[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
	/** 身份证校验码 */
	private static final String[] ID_CARD_CHECK = new String[] { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
	
	/**
	 * desc: 模式匹配：IP<br>
	 * date: 2015年4月2日 下午1:50:59<br>
	 * @author 开发者真实姓名[Andy]
	 * @param input
	 * @return
	 */
	public static boolean isIP(String input) {
		String re = "^(([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))\\.)(([0-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))\\.){2}([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))$";
		return Pattern.matches(re, input);
	}
	
	/**
	 * desc: 模式匹配：身份证号码<br>
	 * date: 2015年4月2日 下午1:59:41<br>
	 * @author 开发者真实姓名[Andy]
	 * @param input
	 * @return
	 */
	public static boolean isId(String input) {
		String re = "(^\\d{15}$)|(^\\d{17}([0-9]|X|x)$)";
		boolean flag = Pattern.matches(re, input);
		if (!flag) {
			return flag;
		}
		if (input.length() == 15) {
			input = id_15218(input);
		}
		int sum = 0;
		for (int i = 0; i < input.length() - 1; i++) {
			int val = Integer.valueOf(String.valueOf(input.charAt(i)));
			sum += val * ID_CARD_WEIGHT[i];
		}
		int y = sum % 11;
		
		return ID_CARD_CHECK[y].equals(String.valueOf(input.charAt(17)));
	}
	
	private static String id_15218(String str) {
		try {
			if (str.length() == 15) {
				String strTemp = "";
				strTemp = str.substring(0, 6) + "19" + str.substring(6);
				int nTemp = 0;
				for (int i = 0; i < 17; i++) {
					nTemp += Integer.parseInt(strTemp.substring(i, i + 1)) * ID_CARD_WEIGHT[i];
				}
				strTemp += ID_CARD_CHECK[nTemp % 11];
				return strTemp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * desc: 模式匹配：email<br>
	 * date: 2015年4月2日 下午2:01:44<br>
	 * @author 开发者真实姓名[Andy]
	 * @param input
	 * @return
	 */
	public static boolean isEmail(String input) {
		String re = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		return Pattern.matches(re, input);
	}
	
	/**
	 * desc: 模式匹配：手机号<br>
	 * date: 2015年4月2日 下午2:02:05<br>
	 * @author 开发者真实姓名[Andy]
	 * @param input
	 * @return
	 */
	public static boolean isMobile(String input) {
		String re = "^1[3|5|7|8][0-9]{9}$";
		return Pattern.matches(re, input);
	}

}

