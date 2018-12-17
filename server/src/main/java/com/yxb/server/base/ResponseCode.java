package com.yxb.server.base;

public enum ResponseCode {
	SUCCESS(0,"success"),
	ERROR(1,"error"),
	NEED_LOGIN(10,"need_login"),
	ILLEGAL_ARGUMENT(2,"illegal argument");
	
	private int code;
	private String desc;
	ResponseCode (int code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
}
