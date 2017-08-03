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
    private final String desc;

    ResponseEnums( int code, String desc ) {
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
