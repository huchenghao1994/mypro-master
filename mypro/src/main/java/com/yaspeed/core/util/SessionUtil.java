package com.yaspeed.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 * session 工具类
 *
 */
public final class SessionUtil {
	
	private static HttpServletRequest request=RequestHolder.getRequest();
	
	private static String session_token="session_token";

	protected static final Logger logger = LoggerFactory.getLogger(SessionUtil.class);

	/**
	  * 设置session的值
	  * @param
	  * @param key
	  * @param value
	  */
	 public static void setSession(String key,Object value){
		 request.getSession(true).setAttribute(key, value);
	 }
	 
	 
	 /**
	  * 获取session的值
	  * @param
	  * @param key
	  * @param
	  */
	 public static Object getSession(String key){
		 return request.getSession(true).getAttribute(key);
	 }
	 
	 /**
	  * 删除Session值
	  * @param
	  * @param key
	  */
	 public static void removeSession(String key){
		 request.getSession(true).removeAttribute(key);
	 }
	 
	 /**
	  * 设置token
	  * @param
	  * @param
	  */
	 public static void setSessionToken(String token){
		 setSession(session_token,token);
	 }
	 
	 /**
	  * 获取token
	  * @param
	  * @param
	  */
	 public static Object getSessionToken(){
		return getSession(session_token);
	 }
}