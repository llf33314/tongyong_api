package com.gt.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gt.api.enums.ResponseEnums;

import java.io.Serializable;

import static com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing.DEFAULT_TYPING;

/**
 * 统一接口的响应数据格式
 * <pre>
 *
 * </pre>
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/08/02
 * @since 1.0.0
 */
//保证序列化json的时候,如果是null的对象,key也会消失
@JsonSerialize( typing = DEFAULT_TYPING )
public class ResponseUtils< T > implements Serializable {

    /*状态码*/
    private int code;

    /*返回消息*/
    private String msg;

    /*泛型数据*/
    private T data;

    protected ResponseUtils( int code ) {
	this.code = code;
    }
    
    protected ResponseUtils() {
        }

    protected ResponseUtils( int code, T data ) {
	this.code = code;
	this.data = data;
    }

    protected ResponseUtils( int code, String msg ) {
	this.code = code;
	this.msg = msg;
    }

    protected ResponseUtils( int code, String msg, T data ) {
	this.code = code;
	this.msg = msg;
	this.data = data;
    }

    /**
     * 创建响应成功
     *
     * @return ResponseUtils
     */
    public static < T > ResponseUtils< T > createBySuccess() {
	return createBySuccessMessage( ResponseEnums.SUCCESS.getMsg() );
    }

    /**
     * 创建响应成功
     *
     * @param data 数据包
     *
     * @return ResponseUtils
     */
    public static < T > ResponseUtils< T > createBySuccess( T data ) {
	return createBySuccess( null, data );
    }

    /**
     * 创建响应成功
     *
     * @param msg 返回消息
     *
     * @return ResponseUtils
     */
    public static < T > ResponseUtils< T > createBySuccessMessage( String msg ) {
	return createBySuccess( msg, null );
    }

    /**
     * 创建响应成功
     *
     * @param msg  消息
     * @param data 数据包
     *
     * @return ResponseUtils
     */
    public static < T > ResponseUtils< T > createBySuccess( String msg, T data ) {
	return createBySuccessCodeMessage( ResponseEnums.SUCCESS.getCode(), msg, data );
    }

    /**
     * 创建响应成功
     *
     * @param code 状态码
     * @param msg    消息
     * @param data   数据包
     *
     * @return ResponseUtils
     */
    public static < T > ResponseUtils< T > createBySuccessCodeMessage( int code, String msg, T data ) {
	return new ResponseUtils<>( code, msg, data );
    }

    /**
     * 创建响应失败
     *
     * @return ResponseUtils
     */
    public static < T > ResponseUtils< T > createByError() {
	return createByErrorCodeMessage( ResponseEnums.ERROR.getCode(), ResponseEnums.ERROR.getMsg() );
    }

    /**
     * 创建响应失败
     *
     * @param errorMessage 消息
     *
     * @return ResponseUtils
     */
    public static < T > ResponseUtils< T > createByErrorMessage( String errorMessage ) {
	return createByErrorCodeMessage( ResponseEnums.ERROR.getCode(), errorMessage );
    }

    /**
     * 创建响应失败
     *
     * @param errorCode    状态码
     * @param errorMessage 消息
     *
     * @return ResponseUtils
     */
    public static < T > ResponseUtils< T > createByErrorCodeMessage( int errorCode, String errorMessage ) {
	return new ResponseUtils<>( errorCode, errorMessage );
    }

    //使之不在json序列化结果当中，作用用于判断
    @JsonIgnore
    public boolean isSuccess() {
	return this.code == ResponseEnums.SUCCESS.getCode();
    }

    public int getcode() {
	return code;
    }

    public T getData() {
	return data;
    }

    public String getMsg() {
	return msg;
    }
}
