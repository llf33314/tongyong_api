package com.gt.api.enums;

/**
 * 定义消息返回的枚举类
 *
 * @author zhangmz
 * @create 2017/6/12
 */
public enum ResponseEnums {
    SUCCESS( 0, "成功" ),
    ERROR( 1, "错误" ),
    SYSTEM_ERROR( 9999, "系统异常" );

    private final int    code;
    private final String msg;

    ResponseEnums( int code, String msg ) {
	this.code = code;
	this.msg = msg;
    }

    public int getCode() {
	return code;
    }

    public String getMsg() {
	return msg;
    }
}
