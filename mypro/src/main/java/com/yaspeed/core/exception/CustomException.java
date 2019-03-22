package com.yaspeed.core.exception;

/**
 * 系统自定义的异常类型，实际开发中可能要定义多种异常类型
 * 
 * @author Administrator
 *
 */
public class CustomException extends Exception {

	// 异常信息
	private String message;

	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
