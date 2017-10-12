package com.gt.api.util.sign;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.sign.SignBean;
import com.gt.api.exception.SignException;

import java.util.HashMap;
import java.util.Map;

/**
 * 带签名的Http请求工具类
 * Created by psr on 2017/8/10 0010.
 */
public class SignHttpUtils {

    /**
     * 发送带签名的POST请求
     * @param url 请求url
     * @param paramObj 请求参数
     * @param signKey 签名
     * @return 服务器返回的String类型的数
     * @throws SignException 签名异常
     */
    public static String postByHttp(String url, Object paramObj, String signKey) throws SignException{
        if (signKey == null){
            throw new SignException("signKey could not be null");
        }
        String param = JSONObject.toJSONString(paramObj);
        SignBean signBean = SignUtils.sign(signKey, param);
        Map<String, String> headers = new HashMap<>();
        headers.put("sign", JSONObject.toJSONString(signBean));
        String result = HttpUtils.sendPostByHeadersByTens(url, headers, param);
        return result;
    }
  /**
     * 发送带签名的POST请求（调wxmp项目的接口方法）
     * @param url 请求url
     * @param paramObj 请求参数
     * @param signKey 签名
     * @return 服务器返回的String类型的数
     * @throws SignException 签名异常
     */
  public static String WxmppostByHttp(String url, Object paramObj, String signKey) throws SignException{
        if (signKey == null){
            throw new SignException("signKey could not be null");
        }
        String param = JSONObject.toJSONString(paramObj);
        SignBean signBean = SignUtils.sign(signKey, null);
        Map<String, String> headers = new HashMap<>();
        headers.put("sign", JSONObject.toJSONString(signBean));
        String result = HttpUtils.sendwxmpPostByHeadersByTens(url, headers, param);
        return result;
    }

}
