package com.yaspeed.core.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Create by LiYilin on 2018-07-06
 * 对原始密码进行加密
 */
public class EncryptPasswd {
	
	public static String encrypt(String source, String salt, Integer hashIterations) {
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
		return md5Hash.toString();
	}
}
