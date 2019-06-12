package com.demo.utils;

import java.util.UUID;

/**
 * @author nieyawei
 * @version 1.0
 * @className: UUIDUtil
 * @description:
 * @date 2019-06-12 20:48
 */

public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
