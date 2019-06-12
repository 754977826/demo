package com.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author nieyawei
 * @version 1.0
 * @className: DateUtil
 * @description:
 * @date 2019-06-09 20:14
 */

public class DateUtil {

    public volatile static int ERROR_COUNT = 0;

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(date);
    }

    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return f.format(date);
    }
}
