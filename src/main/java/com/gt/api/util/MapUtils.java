package com.gt.api.util;

/**
 * 地图工具类
 * @author psr
 * @since 2017/7/7
 * @version v1.0
 */
public class MapUtils {

    /**
     * 地球半径
     */
    public static final double EARTH_RADIUS = 6378137;

    /**
     * 根据经纬度判断两地相差的距离，单位m
     * @param lon1  经度1
     * @param lat1  纬度1
     * @param lon2  经度2
     * @param lat2  纬度2
     * @return 成功返回相差距离<br>
     * 		         失败返回-1<br>
     */
    public static double getDistance(double lon1, double lat1, double lon2, double lat2){
        Double distance = 0.0;
        try {
            double rad = Math.PI / 180.0;

            double radLon1 = lon1 * rad;
            double radLat1 = lat1 * rad;
            double radLon2 = lon2 * rad;
            double radLat2 = lat2 * rad;

            double dis = Math.acos(Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLon1 - radLon2) + Math.sin(radLat1) * Math.sin(radLat2)) * EARTH_RADIUS;
            distance = Math.round(dis * 10000.0) / 10000.0;

            return distance;
        } catch (Exception e) {
            return -1;
        }
    }

}
