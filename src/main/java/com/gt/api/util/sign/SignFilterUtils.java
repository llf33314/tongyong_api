package com.gt.api.util.sign;

import com.alibaba.fastjson.JSON;
import com.gt.api.bean.sign.SignBean;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * 签名过滤器解析类
 * Created by psr on 2017/8/10 0010.
 */
public class SignFilterUtils {

    /**
     * 调用SignHttpUtils.postByHttp();发送请求对应的过滤器解析类
     * 在调用该方法前，请调用
     * servletRequest = new BodyRequestWrapper(httpServletRequest);
     * 用于重写servletRequest，否则将会出现io错误
     * @param servletRequest
     * @param signKey
     * @return
     */
    public static String postByHttp(ServletRequest servletRequest, String signKey){
        // 获取参数
        String param = HttpUtils.getBodyString(servletRequest);
        // 获取header中的签名
        String signStr = ((HttpServletRequest)servletRequest).getHeader("sign");
        // 解析签名
        SignBean signBean = JSON.parseObject(signStr, SignBean.class);
        // 返回签名验证信息
        String code = SignUtils.decSign(signKey, signBean, param);
        return code;
    }

}
