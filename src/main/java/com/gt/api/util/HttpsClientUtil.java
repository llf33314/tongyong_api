package com.gt.api.util;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.util.httpclient.HTTPSSecureProtocolSocketFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;

/**
 * HTTPS请求工具类
 * @author zengwenxiang
 *
 */
public class HttpsClientUtil {

    /**
     * 发送get请求
     * @param url
     * @return
     * @throws Exception
     */
    public static JSONObject doGet(String url) throws Exception {
        String result = "";
        Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", https);
        GetMethod get = new GetMethod(url);
        HttpClient client = new HttpClient();
        client.executeMethod(get);
        result = get.getResponseBodyAsString();
//        result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        Protocol.unregisterProtocol("https");
        return JSONObject.parseObject(result);
    }

    /**
     * 发送POST请求
     * @param url
     * @param jsonObject
     * @return
     * @throws Exception
     */
    public static JSONObject doPost(String url,JSONObject jsonObject) throws Exception {
        String result = "";
        Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", https);
        PostMethod post = new PostMethod(url);
        if (null != jsonObject) {
            RequestEntity entity = new StringRequestEntity(jsonObject.toString(),"application/json","UTF-8");
            post.setRequestEntity(entity);
        }
        HttpClient client = new HttpClient();
        client.executeMethod(post);
        result = post.getResponseBodyAsString();
//        result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        Protocol.unregisterProtocol("https");
        return JSONObject.parseObject(result);
    }


}
