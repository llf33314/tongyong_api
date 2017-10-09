package com.gt.api.util;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.session.BusUser;
import com.gt.api.bean.session.Member;
import com.gt.api.bean.session.TCommonStaff;
import com.gt.api.bean.session.WxPublicUsers;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by Administrator on 2017/9/11 0011.
 */
public class SessionUtils {
    public  static String SESSION_BUSINESS_KEY = "business_key";
    public  static String SESSION_WXPUBLICUSERS_KEY = "wxPublicUsers";
    public  static String SESSION_MEMBER = "member";
    public static String SESSION_LOGIN_STYLE = "loginStyle";
    public static String SESSION_COMMON_STAFF = "TCommonStaff";
    public static String SESSION_PIDBUSID = "PidBusId";
    public static String SESSION_UNIONBUS = "union_bus";
    //获取用户bus_user   SESSION的值
    public static BusUser getLoginUser(HttpServletRequest request) {
        try {
            Object obj = request.getSession().getAttribute(SESSION_BUSINESS_KEY);

            if(obj != null){
                BusUser user =  JSONObject.toJavaObject((JSONObject.parseObject(obj.toString())),BusUser.class );
                return user;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    };

    //存入 用户bus_user  的值
    @SuppressWarnings("unchecked")
    public static void setLoginUser(HttpServletRequest request, BusUser busUser) {
        try {
            request.getSession().setAttribute(
                    SESSION_BUSINESS_KEY, JSONObject.toJSONString(busUser));
        } catch (Exception e) {
            e.printStackTrace();
        }
    };


    /**
     * 设置session中的t_wx_public_user微信信息
     *
     * @param request
     * @return
     */
    public static void setLoginPbUser(HttpServletRequest request,
                                      WxPublicUsers wxPublicUsers) {
        try {
            request.getSession().setAttribute(
                    SESSION_WXPUBLICUSERS_KEY, JSONObject.toJSONString(wxPublicUsers));
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    /**
     * 获取session中的t_wx_public_user登录用户
     *
     * @param request
     * @return
     */
    public static WxPublicUsers getLoginPbUser(HttpServletRequest request) {
        try {
            Object obj = request.getSession().getAttribute(SESSION_WXPUBLICUSERS_KEY);
            if(obj != null){
                WxPublicUsers wx = JSONObject.toJavaObject((JSONObject.parseObject(obj.toString())),WxPublicUsers.class );
                return wx;
            }else{
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    };


    public static Member getLoginMember(HttpServletRequest request) {
        try {
            Object obj = request.getSession().getAttribute(SESSION_MEMBER);
            if(obj != null){
                Member mem = JSONObject.toJavaObject((JSONObject.parseObject(obj.toString())),Member.class );
                return mem;
            }else{
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    };


    //存入登陆人的属性session   0是员工  1是老板
    public static void setLoginStyle(HttpServletRequest request, Integer loginStyle) {
        try {
            if(loginStyle==0) {
                request.getSession().setAttribute(
                        SESSION_LOGIN_STYLE, loginStyle);
                SessionUtils.setLoginUser(request,null);
            }else if(loginStyle==1){
                request.getSession().setAttribute(
                       SESSION_LOGIN_STYLE, loginStyle);
                SessionUtils.setCommonStaff(request,null);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    };
    //获取登录人的属性
    public static Integer getLoginStyle(HttpServletRequest request){
        try {
            Object obj = request.getSession().getAttribute(SESSION_LOGIN_STYLE);
            if(obj != null){
                return Integer.parseInt(obj.toString());
            }else{
                return null;
            }
        }catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    //存入员工session的值
    public  static  void setCommonStaff(HttpServletRequest request,TCommonStaff staff){
        try {
            request.getSession().setAttribute(SESSION_COMMON_STAFF, JSONObject.toJSONString(staff));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取员工session的值
    public static TCommonStaff getCommonStaff(HttpServletRequest request) {
        try {
            Object obj = request.getSession().getAttribute(SESSION_COMMON_STAFF);
            if(obj != null){
                TCommonStaff staff = JSONObject.toJavaObject((JSONObject.parseObject(obj.toString())),TCommonStaff.class );
                return staff;
            }else{
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    };
    //存入员工或者子账号最头部的主商家ID
    public static void setPidBusId(HttpServletRequest request,Integer PidBusId){
        try {
            request.getSession().setAttribute(SESSION_PIDBUSID, PidBusId);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    //获取主账号ID信息
    public static Integer getPidBusId(HttpServletRequest request){
        try {
            Object obj = request.getSession().getAttribute(SESSION_PIDBUSID);
            if(obj != null){
                return Integer.parseInt(obj.toString());
            }else{
                return null;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存入商家联盟里面的值
     * @param request
     * @param user
     */
    public static void setUnionBus(HttpServletRequest request,BusUser user){
        request.getSession().setAttribute(SESSION_UNIONBUS, JSONObject.toJSONString(user));
    }
    //获取商家联盟里面的值
    public static BusUser getUnionBus(HttpServletRequest request) {
        try {
            Object obj = request.getSession().getAttribute(SESSION_UNIONBUS);

            if(obj != null){

                BusUser user =  JSONObject.toJavaObject((JSONObject.parseObject(obj.toString())),BusUser.class );
                return user;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    };
}
