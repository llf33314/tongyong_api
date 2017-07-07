package com.gt.api.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by psr on 2017/7/7 0007.
 */
public class TestUtils {

    /**
     * 手机号验证
     * @param value
     * @return
     */
    public static boolean isPhone(String phone){
        if(org.springframework.util.StringUtils.isEmpty(phone)){
            return false;
        }
        Pattern pattern =  Pattern.compile("(^(13[0123456789][0-9]{8}|15[0123456789][0-9]{8}|18[0123456789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$)") ;
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }


    /**
     * 检查用户邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if(org.springframework.util.StringUtils.isEmpty(email)){
            return false;
        }
        Pattern pattern =  Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$") ;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 检查用户名是否符合规范(数字, 字符, _ 6-16位)
     * @param userName
     * @return
     */
    public static boolean isUserName(String userName){
        if(org.springframework.util.StringUtils.isEmpty(userName)){
            return false;
        }
        Pattern pattern =  Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]{3,15}$") ;
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }

    /**
     * 检查是否符合ip规范
     * 该验证手机验证是有问题的，这个别用
     * @param ip
     * @return
     */
    public static boolean isIp(String ip){
        if(org.springframework.util.StringUtils.isEmpty(ip)){
            return false;
        }
        Pattern pattern =  Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$") ;
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    /**
     * String to int
     * @param value
     * @return
     */
    public static int string2Int(String value){
        if(org.springframework.util.StringUtils.isEmpty(value)){
            return 0;
        }

        return Integer.parseInt(value);
    }

    /**
     * 数字验证码
     * @param length 长度
     * @return
     */
    public static String securityCode(int length){
        // 参数验证
        if(length < 4 || length > 16){
            return null;
        }

        // 种子
        Random random = new Random(System.currentTimeMillis()) ;
        StringBuffer sb = new StringBuffer();

        // 循环参数值
        for(int i = 0; i < length; i++){
            sb.append(random.nextInt(9));
        }

        return sb.toString();
    }

    public static Object getObject4Session(HttpSession session, String key){
        if(org.springframework.util.StringUtils.isEmpty(key)){
            return 0;
        }
        return session == null? null: session.getAttribute(key);
    }

    /**
     * 模糊手机号 14718090320 --> 147***320
     * @param phone
     * @return
     */
    public static String blurPhone(String phone){
        if(!isPhone(phone)){
            return null;
        }
        return phone.substring(0, 3) + "***" + phone.substring(8, 11);

    }

    /**
     * 删除文件
     * @param path 文件路径
     * @return
     */
    public static boolean deleteFile(String path){
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 截取字符串 已...结尾
     * @param str 需要截取的字符串
     * @param len  截取的长度
     * @return
     */
    public static String subString(String str,int len) {
        String s = str;
        String tail = "...";
        if (str != null && !str.equals("") && str.length()>len) {

            if (str == null || str.length() <= len) {
                return str;
            }
            int length = str.length();
            char c = ' ';
            String tag = null;
            String name = null;
            int size = 0;
            String result = "";
            boolean isTag = false;
            List<String> tags = new ArrayList<String>();
            int i = 0;
            for (int end = 0, spanEnd = 0; i < length && len > 0; i++) {
                c = str.charAt(i);
                if (c == '<') {
                    end = str.indexOf('>', i);
                }

                if (end > 0) {
                    // 截取标签
                    tag = str.substring(i, end + 1);
                    int n = tag.length();
                    if (tag.endsWith("/>")) { // 结束符--判断字符串a 是不是以字符串b开头
                        isTag = true;
                    } else if (tag.startsWith("</")) { // 结束符--判断字符串a 是不是以字符串b开头
                        name = tag.substring(2, end - i);
                        size = tags.size() - 1;
                        // 堆栈取出html开始标签
                        if (size >= 0 && name.equals(tags.get(size))) {
                            isTag = true;
                            tags.remove(size);
                        }
                    } else { // 开始符
                        spanEnd = tag.indexOf(' ', 0);
                        spanEnd = spanEnd > 0 ? spanEnd : n;
                        name = tag.substring(1, spanEnd);
                        if (name.trim().length() > 0) {
                            // 如果有结束符则为html标签
                            spanEnd = str.indexOf("</" + name + ">", end);
                            if (spanEnd > 0) {
                                isTag = true;
                                tags.add(name);
                            }
                        }
                    }
                    // 非html标签字符
                    if (!isTag) {
                        if (n >= len) {
                            result += tag.substring(0, len);
                            break;
                        } else {
                            len -= n;
                        }
                    }

                    result += tag;
                    isTag = false;
                    i = end;
                    end = 0;
                } else { // 非html标签字符
                    len--;
                    result += c;
                }
            }
            // 添加未结束的html标签
            for (String endTag : tags) {
                result += "</" + endTag + ">";
            }
            if (i < length) {
                result += tail;
            }
            return result;
        }
        return s;
    }

    /**
     * 判断字符是否有中文
     * @param str
     * @return
     */
    public static boolean isChineseChar(String str){
        boolean temp = false;
        Pattern p=Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m=p.matcher(str);
        if(m.find()){
            temp =  true;
        }
        return temp;
    }


}
