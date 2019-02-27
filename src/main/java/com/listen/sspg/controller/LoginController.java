package com.listen.sspg.controller;

import com.listen.sspg.basecore.ApiAcceptObj;
import com.listen.sspg.basecore.ApiReturnObj;
import com.listen.sspg.service.LoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ResponseBody
    @RequestMapping("/doLogin")
    public ApiReturnObj<Object> doLogin(@Validated ApiAcceptObj apiAcceptObj){
        logger.info("test");
        return loginService.doLogin(apiAcceptObj);
    }
}
