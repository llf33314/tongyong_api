package test.com.gt.api.util;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.sign.SignBean;
import com.gt.api.exception.SignException;
import com.gt.api.util.sign.SignHttpUtils;
import com.gt.api.util.sign.SignUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by psr on 2017/8/4 0004.
 */
public class SignTest {

    @Test
    public void testSign(){
        String signKey = "123456";
        String param = "{id:1,name:'one'}";
        SignBean signBean = SignUtils.sign(signKey, param);
        // 验证签名
        String code = SignUtils.decSign(signKey, signBean, param);
        System.out.println(code);
    }

    @Test
    public void testSignPostByParam(){
        String url = "http://192.168.3.98:7002/demo/getByParam";
        String signKey = "5566";
        // 使用map也是同样的效果
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("name", "three");
        map.put("pwd", "123456");
        // 转成JSON，请使用fastjson
        String param = JSONObject.toJSONString(map);
        try {
            String result = SignHttpUtils.postByHttp(url, param, signKey);
            System.out.println(result);
        } catch (SignException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSignPostWithoutParam(){
        String url = "http://192.168.3.98:7002/demo/getWithoutParam";
        String signKey = "5566";
        try {
            String result = SignHttpUtils.postByHttp(url, null, signKey);
            System.out.println(result);
        } catch (SignException e) {
            e.printStackTrace();
        }
    }

}
