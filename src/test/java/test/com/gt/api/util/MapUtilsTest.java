package test.com.gt.api.util;

import com.gt.api.util.CmdUtils;
import com.gt.api.util.MapUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by psr on 2017/7/7 0007.
 */
public class MapUtilsTest {

    private static Logger logger = Logger.getLogger(MapUtilsTest.class);

    @Test
    public void getDistanceTest(){
        double lon1 = 37.5794125134;
        double lat1 = 110.0830078125;
        double lon2 = 40.44074158646195;
        double lat2 = 108.70070457458496;
        double distance = MapUtils.getDistance(lon1, lat1, lon2, lat2);
        logger.debug(distance);
    }

}
