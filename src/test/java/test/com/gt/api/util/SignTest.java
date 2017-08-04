package test.com.gt.api.util;

import com.gt.api.bean.sign.SignBean;
import com.gt.api.util.SignUtils;
import org.junit.Test;

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

}
