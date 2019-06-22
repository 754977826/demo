package com.demo.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author nieyawei
 * @version 1.0
 * @className: ResultUtil
 * @description:
 * @date 2019-06-13 19:47
 */

public class ResultUtil {
    public static JSONObject getJSON(String errCode, String errInfo){
        JSONObject json = new JSONObject();
        json.put("errCode", errCode);
        json.put("errInfo", errInfo);
        return json;
    }
    public static JSONObject getDataJSON(String errCode, String errInfo, Object data ){
        JSONObject json = new JSONObject();
        json.put("errCode", errCode);
        json.put("errInfo", errInfo);
        json.put("data", data);
        return json;
    }
}
