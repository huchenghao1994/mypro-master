package com.yaspeed.common;

/**
 * 自定义响应结构
 * 
 * @author Administrator
 *
 */
public class ReturnResult {

	public static final int SUCCESS_CODE = 0;           //成功
	public static final int ERROR_CODE = 1;             //失败
	public static final int PARA_VALID_CODE = 12022;        //参数验证失败
	public static final int CUS_EX_CODE = 12023;            //自定义异常代码
	public static final int BAD_REQUEST = 400;              //错误的请求
	public static final int UNAUTHORIZED = 401;             //未经授权
	public static final int FORBIDDEN = 403;                //没有权限
	public static final int NOT_FOUND = 404;                //没有找到
	public static final int INTERNAL_SERVER_ERROR = 500;    //内部服务器错误
	
	
	private int code;
	/**
	 * 响应中的数据
	 */
	private Object data;
	/**
	 * 响应中的消息
	 */
	private String msg;

	public boolean isSuccess() {
		return SUCCESS_CODE==code;
	}

	public ReturnResult() {
	}

	public ReturnResult(int code, Object data, String msg) {
		super();
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static ReturnResult byCode(int code) {
		return new ReturnResult(code, "", null);
	}

	public static ReturnResult byCodeMsg(int code, String msg) {
		return new ReturnResult(code, null ,msg);
	}

	public static ReturnResult success() {
		return new ReturnResult(SUCCESS_CODE, null, null);
	}

	public static ReturnResult success(Object data) {
		return new ReturnResult(SUCCESS_CODE, data, null);
	}

	public static ReturnResult error(String msg) {
		return new ReturnResult(ERROR_CODE, null, msg);
	}

	public static ReturnResult error(Object data, String msg) {
		return new ReturnResult(ERROR_CODE, data, msg);
	}
}
