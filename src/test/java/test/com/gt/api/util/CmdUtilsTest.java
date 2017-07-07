package test.com.gt.api.util;

import com.gt.api.util.CmdUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by psr on 2017/7/7 0007.
 */
public class CmdUtilsTest {

    private static Logger logger = Logger.getLogger(CmdUtilsTest.class);

    @Test
    public void execTest(){
        String cmd = "ping 192.168.3.10";
        String result = CmdUtils.exec(cmd);
        logger.debug(result);
    }

}
