package com.gt.api.util.sign;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.ServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * HTTP工具类
 * 
 * @author PanSiran <br>
 * @date 2017年3月27日 <br>
 *
 */
public class HttpUtils {

	private static 	CloseableHttpClient httpClient = null;
	private static 	RequestConfig config = null;

	public static void init(){
		if(httpClient==null||httpClient.equals("")||config==null||config.equals("")){
			httpClient = HttpClients.createDefault();
			// 设置超时时间
			// 构建请求配置信息
			RequestConfig config = RequestConfig.custom().setConnectTimeout(5000) // 创建连接的最长时间
					.setConnectionRequestTimeout(500) // 从连接池中获取到连接的最长时间
					.setSocketTimeout(10 * 1000) // 数据传输的最长时间
					.setStaleConnectionCheckEnabled(true) // 提交请求前测试连接是否可用
					.build();

		}
	}

	/**
	 * 带头部信息的post请求，超时10秒钟
	 * @param url
	 * @param headers
	 * @param param
	 * @return
	 */
	static String sendPostByHeadersByTens(String url, Map<String, String> headers, String param) {
		return sendPostByHeaders(url, headers, param, 10000, "utf-8");
	}

	/**
	 * 带头部信息的post请求
	 * @param url 请求链接
	 * @param headers 请求头
	 * @param param 请求参数
	 * @param timeout 超时时间
	 * @return 返回参数
	 */
	static String sendPostByHeaders(String url, Map<String, String> headers, String param, int timeout, String charsetName) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-type", "application/json;charset=GBK");
			
			// 自定义header头部请求参数
			if(headers != null && !headers.isEmpty()){
				for(String key : headers.keySet()){
					conn.setRequestProperty(key, headers.get(key));
				}
			}
			String entity = new String(param.getBytes("utf-8"));
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setConnectTimeout(timeout);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(entity);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			InputStreamReader isr = new InputStreamReader(conn.getInputStream(), charsetName);
			in = new BufferedReader(isr);
			String line;
			while ((line = in.readLine()) != null) {
				result += new String(line.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 带头部信息的post请求
	 * @param url 请求链接
	 * @param headers 请求头
	 * @param param 请求参数
	 * @return 返回参数
	 */
	static String sendPostByHeaders(String url, Map<String, String> headers, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-type", "application/json;charset=UTF-8");

			// 自定义header头部请求参数
			if(headers != null && !headers.isEmpty()){
				for(String key : headers.keySet()){
					conn.setRequestProperty(key, headers.get(key));
				}
			}

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			in = new BufferedReader(isr);
			String line;
			while ((line = in.readLine()) != null) {
				result += new String(line.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 获取请求Body<br/>
	 * 返回body里面的内容
	 * @param request
	 * @return String类型
	 */
	public static String getBodyString(ServletRequest request) {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}


	static String sendwxmpPostByHeadersByTens(String url, Map<String, String> headers, String param) {
		String result = null;
		for(int i=0;i<5;i++){
			result = sendwxmpPostByHeaders(url, headers, param);
			if(result!=null&&!result.equals("")){
				break;
			}
		}
		return result;
	}

	static String sendwxmpPostByHeaders(String url, Map<String, String> headers, String param) {

		init();
		// 设置请求配置信息
		String jsonResult = null;
		HttpPost post = new HttpPost(url);
		post.setConfig(config);
		post.addHeader("sign", headers.get("sign").toString());
		try {
			if (null != param) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(param,
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
					if (false) {
						return null;
					}
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						InputStream instreams = entity.getContent();
						str = convertStreamToString(instreams);
						post.abort();
					}

					/** 把json字符串转换成json对象 **/
					jsonResult = str;
				} catch (Exception e) {
					try {
						return null;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			try {
				return null;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return jsonResult;
	}


	public static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
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
		return sb.toString();
	}
}
