package com.demo.test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nieyawei
 * @version 1.0
 * @className: ControllerTest
 * @description:
 * @date 2019-06-08 18:28
 */
@Slf4j
@RestController
public class ControllerTest {
    @RequestMapping("test")
    public JSONObject test(){
        JSONObject json = new JSONObject();
        json.put("name", "张三");
        log.info("我是日志：{}", json);
        return json;
    }
}
