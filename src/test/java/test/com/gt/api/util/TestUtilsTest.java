package test.com.gt.api.util;

import com.gt.api.util.TestUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by psr on 2017/7/7 0007.
 */
public class TestUtilsTest {

    private static final Logger logger = Logger.getLogger(TestUtilsTest.class);

    @Test
    public void isPhoneTest(){
        String phone = "13632374547";
        boolean bl = TestUtils.isPhone(phone);
        logger.debug(bl);
        Assert.assertTrue(bl);
    }

}
