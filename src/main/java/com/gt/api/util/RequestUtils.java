package com.gt.api.util;

import java.io.Serializable;

/**
 * 参数父级类
 * @author Administrator
 *
 */
public class RequestUtils<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 业务请求参数
	 */
	private T reqdata;
	
	
	
	

	
	public RequestUtils(){
		
	}

	/**
	 * 插入请求参数
	 * @param data
	 */
	 public RequestUtils(T reqdata,String action) {
		       this.reqdata = reqdata;
	 }
	public static void main(String[] args) {
//		BaseResult data=new BaseResult();
//		data.setCode(0);
//		data.setMsg("111");
//		BaseParam baseParam=new BaseParam ();
//		baseParam.setData(data);
//		System.out.println(JSONObject.toJSONString(baseParam));
//		System.out.println(JSONObject.toJSONString(baseParam.getData()));
	}


	public T getReqdata() {
		return reqdata;
	}

	public void setReqdata(T reqdata) {
		this.reqdata = reqdata;
	}
}
