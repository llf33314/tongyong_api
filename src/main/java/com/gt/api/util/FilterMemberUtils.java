package com.gt.api.util;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.session.Member;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/11/23 0023.
 */
public class FilterMemberUtils {
    /**
     * 只做拦截器上，无法做到该用户是该商家的
     * @param request
     * @return
     */
    public static Member getLoginMember(HttpServletRequest request) {
        try {
            Object obj = request.getSession().getAttribute(SessionUtils.SESSION_MEMBER);
            if (obj != null) {
                Member mem = JSONObject.toJavaObject((JSONObject.parseObject(obj.toString())), Member.class);
               return mem;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
