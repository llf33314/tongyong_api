package com.gt.api.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 浏览器相关信息
 * @author Administrator
 *
 */
public class BrowserUtils {
	public final static String[] AGENT = { "Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser" };  

	/**
	 * 判断浏览器
	 * @return 1:微信浏览器,99:其他浏览器
	 */
	public static Integer  judgeBrowser(HttpServletRequest request){
		Integer result=null;
		String ua = request.getHeader("user-agent")
				.toLowerCase();
		if (ua.indexOf("micromessenger") > 0) {// 微信-1
			result=1;
		}else{//其他 -99
			result=99;
		}
		return result;
	}
	
	/**
	 * 验证是否来自移动端，是移动端，返回true
	 * @param ua
	 * @return
	 */
	public static boolean checkAgentIsMobile(String ua) {  
	    String[] agent = AGENT;  
	    boolean flag = false;  
	    if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {  
	        // 排除 苹果桌面系统  
	        if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {  
	            for (String item : agent) {  
	                if (ua.contains(item)) {  
	                    flag = true;  
	                    break;  
	                }  
	            }  
	        }  
	    }  
	    return flag;  
	} 
}
