package com.listen.sspg.controller;

import com.listen.sspg.entity.User;
import com.listen.sspg.entity.blocknoinfo;
import com.listen.sspg.service.BlockServiceImpl;
import com.listen.sspg.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    public UserServiceImpl userService;

    @RequestMapping(value = "/getUser", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User getUser() {
        return userService.getUser();
    }

}
