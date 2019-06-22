package com.demo.user;

import com.alibaba.fastjson.JSONObject;
import com.demo.user.bean.User;
import com.demo.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author nieyawei
 * @version 1.0
 * @className: LoginController
 * @description:
 * @date 2019-06-15 23:37
 */

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class LoginController {


    @PostMapping(value = "/login")
    public JSONObject login(@RequestBody User user){
        log.info("请求参数：{}", user);
        if("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())){
            return ResultUtil.getJSON("0", "登录成功！");
        }else{
            return ResultUtil.getJSON("9", "账号或密码错误！");
        }
    }
}
