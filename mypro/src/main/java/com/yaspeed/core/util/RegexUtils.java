package com.yaspeed.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create by LiYilin on 2018-09-07
 * 验证正则表达式工具类
 */

public class RegexUtils {
	
	public static boolean regexInspectIdCard(String str) {
		String regex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
		return match(regex, str);
	}
	
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
