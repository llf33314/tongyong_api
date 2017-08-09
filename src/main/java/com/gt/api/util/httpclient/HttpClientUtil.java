package com.gt.api.util.httpclient;

import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

/**
 * 
 * @author lfx
 *
 */
public class HttpClientUtil {
	
	protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_JSON.toString());

	/**
	 * post请求
	 * @param messageJson
	 * @return
	 */
	public static  <T> T reqPost(String messageJson ,String url,Class<T> clazz){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
										.setHeader(jsonHeader)
										.setUri(url)
										.setEntity(new StringEntity(messageJson,Charset.forName("utf-8")))
										.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,clazz);
	}
	

	
	/**
	 * post请求(返回乱码)
	 * @param messageJson
	 * @return
	 */
	public static  <T> T reqPostUTF8(String messageJson ,String url,Class<T> clazz){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
										.setHeader(jsonHeader)
										.setUri(url)
										.setEntity(new StringEntity(messageJson,Charset.forName("utf-8")))
										.build();
		return LocalHttpClient.executeJsonResultUTF8(httpUriRequest,clazz);
	}
}
