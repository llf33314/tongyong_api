package com.gt.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;

import com.gt.api.util.httpclient.LocalHttpClient;
/**
 * http 请求获取参数
 * @author Administrator
 *
 */
public class HttpClienUtils {
	
	protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_JSON.toString());

	/**
	 * post请求
	 * 
	 * @param url
	 *            url地址
	 * @param jsonParam
	 *            参数
	 * @param noNeedResponse
	 *            不需要返回结果
	 * @return
	 * @throws Exception
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam,
			boolean noNeedResponse) throws Exception {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 设置超时时间
		// 构建请求配置信息
		RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) // 创建连接的最长时间
				.setConnectionRequestTimeout(500) // 从连接池中获取到连接的最长时间
				.setSocketTimeout(10 * 1000) // 数据传输的最长时间
				.setStaleConnectionCheckEnabled(true) // 提交请求前测试连接是否可用
				.build();
		// 设置请求配置信息
		JSONObject jsonResult = null;
		HttpPost post = new HttpPost(url);
		post.setConfig(config);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(),
						"utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				post.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(post);
			url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					/** 读取服务器返回过来的json字符串数据 **/
					if (noNeedResponse) {
						return null;
					}
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						InputStream instreams = entity.getContent();
						str = convertStreamToString(instreams);
						post.abort();
					}

					/** 把json字符串转换成json对象 **/
					jsonResult = JSONObject.fromObject(str);
				} catch (Exception e) {
					throw new Exception();
				}
			}
		} catch (IOException e) {
			throw new Exception();
		}
		return jsonResult;
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject httpGet(String url) throws Exception {
		// get请求返回结果
		JSONObject jsonResult = null;
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// 构建请求配置信息
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(1000) // 创建连接的最长时间
					.setConnectionRequestTimeout(500) // 从连接池中获取到连接的最长时间
					.setSocketTimeout(10 * 1000) // 数据传输的最长时间
					.setStaleConnectionCheckEnabled(true) // 提交请求前测试连接是否可用
					.build();

			// 发送get请求
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(config);
			HttpResponse response = httpClient.execute(httpGet);
			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				String str="";
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instreams = entity.getContent();
					str = convertStreamToString(instreams);
					httpGet.abort();
				}
				/** 把json字符串转换成json对象 **/
				jsonResult = JSONObject.fromObject(str);
				url = URLDecoder.decode(url, "UTF-8");
			} else {
				throw new Exception();
			}
		} catch (IOException e) {
			throw new Exception();
		}
		return jsonResult;
	}

	public static String convertStreamToString(InputStream is) {
		StringBuilder sb1 = new StringBuilder();
		byte[] bytes = new byte[4096];
		int size = 0;
		try {
			while ((size = is.read(bytes)) > 0) {
				String str = new String(bytes, 0, size, "UTF-8");
				sb1.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb1.toString();
	}
	
	
	
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
	
	
	/**
	 * post请求
	 * @param messageJson
	 * @return
	 */
	public static  <T> T reqGet(String messageJson ,String url,Class<T> clazz){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
										.setHeader(jsonHeader)
										.setUri(url)
										.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,clazz);
	}
	

	
	/**
	 * post请求(返回乱码)
	 * @param messageJson
	 * @return
	 */
	public static  <T> T reqGetUTF8(String messageJson ,String url,Class<T> clazz){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
										.setHeader(jsonHeader)
										.setUri(url)
										.build();
		return LocalHttpClient.executeJsonResultUTF8(httpUriRequest,clazz);
	}
	
	
	public static void main(String arg[]) throws Exception{
		Map<String, Object> params = new HashMap<>();
		
		params.put("style", "1");
		params.put("userId", "4856");
		params.put("loginuc", "0");
		params.put("erpstyle", "8");
		String remoteUrl ="http://nb.deeptel.com.cn";
		String url = remoteUrl+"/ErpMenus/79B4DE7C/getMenus.do";
		JSONObject json = HttpClienUtils.httpPost(url, JSONObject.fromObject(params), false);
		System.out.print( JsonUtils.json2Map(json.toString()));
		
	}
}
