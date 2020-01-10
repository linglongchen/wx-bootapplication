package com.modules.bootapplication.modules.utils;

import java.math.BigDecimal;

/**
 * @Description:
 * @Date: 2018/1/29
 * @Author: wcf
 */
public class BaiDuMapUtils {


     /**
      * @description 获取两个gps点之间的距离
      * @param
      * @author wcf
      * @date 2018/1/29
      * @return
      */
    public static long getDistance(BigDecimal lng1, BigDecimal lat1, BigDecimal lng2, BigDecimal lat2){
        double radLat1 = Math.toRadians(lat1.doubleValue());
        double radLat2 = Math.toRadians(lat2.doubleValue());
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng1.doubleValue()) - Math.toRadians(lng2.doubleValue());
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;
        return (long)s;
    }

    public static long getDistance(double lng1, double lat1, double lng2, double lat2){
        double a = lat1 - lat2;
        double b = lng1 - lng2;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(lat1)
                * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;
        return (long)s;
    }
}
