package com.listen.sspg.controller;

import com.listen.sspg.entity.User;
import com.listen.sspg.service.UserServiceImpl;
import com.listen.sspg.tools.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserServiceImpl userService;
    @Autowired
    public RedisUtil redisUtil;

    @RequestMapping(value = "/getUser", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User getUser() {
        return userService.getUser();
    }

    @RequestMapping(value = "/insert", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String insert() {
        redisUtil.set("ok","123456abcdefg");
        return "OK";
    }

    @RequestMapping(value = "/get", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object get() {
        Object value = redisUtil.get("ok");
        return value;
    }
}
