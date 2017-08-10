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
 * Created by Administrator on 2017/8/4 0004.
 */
public class SignTest {

    @Test
    public void testSign(){
        String signKey = "123456";
        String param = "{id:1,name:'one'}";
        SignBean signBean = SignUtils.sign(signKey, param);

        String code = SignUtils.decSign(signKey, signBean, param);
        System.out.println(code);
    }

    @Test
    public void testSignPost(){
        String url = "http://192.168.3.98:7002/demo/getdemo";
        String signKey = "5566";
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "one");
        map.put("pwd", "123456");
        String param = JSONObject.toJSONString(map);
        try {
            String result = SignHttpUtils.postByHttp(url, null, signKey);
            System.out.println(result);
        } catch (SignException e) {
            e.printStackTrace();
        }
    }

}
