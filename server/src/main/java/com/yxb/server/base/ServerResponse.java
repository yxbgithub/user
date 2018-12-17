package com.yxb.server.base;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//加入@JsonInclude注解，指明NON_NULL属性。表明只要属性为null，就不会序列化为json数据
@JsonInclude(value = Include.NON_NULL)
public class ServerResponse<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2904824358005057266L;
	private int status;
	private String msg;
	private T data;

	private ServerResponse(int status) {
		this.status = status;
	}

	private ServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	private ServerResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}

	private ServerResponse(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	// 加上@JsonIgnore注解，不然在序列化的时候，会将当做一个get方法，用于取得属性值
	@JsonIgnore
	public boolean isSuccess() {
		// 当code为0时返回true
		return this.status == ResponseCode.SUCCESS.getCode();
	}

	public int getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}

	// 使用静态方法
	public static <T> ServerResponse<T> createBySuccess() {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
	}

	// 这里使用createBySuccessMsg指明以msg创建serverResponse
	public static <T> ServerResponse<T> createBySuccessMsg(String msg) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
	}

	public static <T> ServerResponse<T> createBySuccess(T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
	}

	public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
	}

	/**
	 * 返回code=1的错误信息
	 * 
	 * @param errorMsg
	 * @return
	 */
	// 对于错误的情况，没有data数据，所以不用设置data参数.这个方法统一使用code：1
	public static <T> ServerResponse<T> createByErrorMsg(String errorMsg) {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), errorMsg);
	}

	/**
	 * 返回指定code的错误信息
	 * 
	 * @param code
	 * @param errorMsg
	 * @return
	 */
	public static <T> ServerResponse<T> createByErrorCodeMsg(int code,
			String errorMsg) {
		return new ServerResponse<>(code, errorMsg);
	}

}
