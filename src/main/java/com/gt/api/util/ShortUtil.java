package com.gt.api.util;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.sign.SignBean;
import com.gt.api.util.httpclient.LocalHttpClient;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 短连接调用工具
 * @author zengwx
 *
 */
public class ShortUtil {

    private static char hexDigitsSM[]   = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    private static final String SIGNKEY = "goodtom2016@shortUrl.";

    /**
     * 带头部信息的post请求
     * @param
     * @return 服务返回信息
     */
    public static String sendLongUrlToShortApi(String shortApiurl, String longUrl) {
        Map<String, String> headers = encryptHeader();
        String url = shortApiurl+"?longUrl="+longUrl;
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
//            JSONObject json = new JSONObject();
//            json.put("longUrl",longUrl);
//            out.print(json);
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

    public static String newSendLongUrlToShortApi(String shortApiurl, String longUrl){
        Map<String, String> headers = encryptHeader();
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setHeader("key",headers.get("key").toString())
                .setHeader("timestamp",headers.get("timestamp").toString())
                .setUri(shortApiurl)
                .setEntity(new StringEntity(longUrl,Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResultUTF8(httpUriRequest,String.class);
    }
    /**
     * 生成头部加密参数
     * @return
     */
    private static Map<String, String> encryptHeader(){
        // 生成时间戳
        String timestamp = DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmss");
        // 生成签名
        String key = getMD5SM(SIGNKEY + timestamp);
        // 组合签名请求
        Map<String, String> headers = new HashMap<>();
        headers.put("key", key);
        headers.put("timestamp", timestamp);
        return headers;
    }

    // 获取32位MD5 小写
    private final static String getMD5SM(String str) {
        String md5Hex  = null;
        try {
            byte[] md5Digits = getMD5Digits(str);
            md5Hex = getHexSM(md5Digits);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5Hex;
    }

    private static String getHexSM(byte[] buffer) {
        return getHexSM(buffer, buffer.length);
    }

    private static String getHexSM(byte[] buffer, int len) {
        char str[] = new char[len * 2];
        int k = 0;
        for (int i = 0; i < len; i++) {
            byte byte0 = buffer[i];
            str[k++] = hexDigitsSM[byte0 >>> 4 & 0xf];
            str[k++] = hexDigitsSM[byte0 & 0xf];
        }
        return new String(str);
    }

    private static byte[] getMD5Digits(String str) throws NoSuchAlgorithmException {
        byte[] btInput = str.getBytes();
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        mdInst.update(btInput);
        return mdInst.digest();
    }
}
