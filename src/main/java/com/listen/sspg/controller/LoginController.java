package com.listen.sspg.controller;

import com.listen.sspg.basecore.ApiAcceptObj;
import com.listen.sspg.basecore.ApiReturnObj;
import com.listen.sspg.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
