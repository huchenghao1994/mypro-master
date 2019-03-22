package com.yaspeed.core.util;

import javax.crypto.*;
import javax.crypto.spec.*;

public class AESUTIL {
	/**
	 * 加密用的Key
	 * 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
	 */
	public static final String cKey = "BaiFuKuaiDai2018";
	

	/**
	 * 解密算法
	 * @param sSrc
	 * @param sKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String sSrc) throws Exception {
		try {
			//判断Key是否正确
			if (cKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			
			//判断Key是否为16位
			if (cKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			
			byte[] raw = cKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = hex2byte(sSrc);
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	/**
	 * 加密算法
	 * @param sSrc
	 * @param sKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String sSrc) throws Exception {
		if (cKey == null) {
			System.out.print("Key为空null");
			return null;
		}
		
		//判断Key是否为16位
		if (cKey.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		
		byte[] raw = cKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes());
		return byte2hex(encrypted).toLowerCase();
	}
	
	/**
	 * 字符串转字节数组
	 * @param strhex
	 * @return
	 */
	public static byte[] hex2byte(String strhex) {
		if (strhex == null) {
			return null;
		}
		int l = strhex.length();
		if (l % 2 == 1) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
		}
		return b;
	}

	/**
	 * 字节数组转字符串
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
}