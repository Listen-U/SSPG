package com.listen.sspg.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.listen.sspg.basecore.ApiAcceptObj;
import com.listen.sspg.basecore.ApiReturnObj;
import com.listen.sspg.entity.LoginInfo;
import com.listen.sspg.entity.UserInfo;
import com.listen.sspg.service.LoginServiceImpl;
import com.listen.sspg.service.UserInfoServiceImpl;
import com.listen.sspg.tools.RedisUtil;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.listen.sspg.tools.LoginUtils.getSessionKeyOrOpenId;
import static com.listen.sspg.tools.LoginUtils.getUserInfo;

/**
 * WX登录
 * @author Listen
 * @date 2019/2/26
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginServiceImpl loginService;

    @ResponseBody
    @RequestMapping("/doLogin")
    public ApiReturnObj<Object> doLogin(@Validated ApiAcceptObj apiAcceptObj){

        return loginService.doLogin(apiAcceptObj);
    }
}
