package com.listen.sspg.controller;

import javax.servlet.http.HttpServletRequest;

import com.listen.sspg.basecore.ApiAcceptObj;
import com.listen.sspg.basecore.ApiReturnObj;
import com.listen.sspg.entity.User;
import com.listen.sspg.service.UserServiceImpl;
import com.listen.sspg.tools.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author
 * @date 2019/2/26
 */
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

    /**
     * pageHelper测试
     * @param apiAcceptObj
     * @return
     */
    @RequestMapping(value = "/testPage", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ApiReturnObj<Object> testPage(@Validated ApiAcceptObj apiAcceptObj) {
        return userService.testPage(apiAcceptObj);
    }

    /**
     * 测试保存图片
     * @return
     */
    @RequestMapping(value = "/testPicture", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String testPicture(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
        String result = userService.testPicture(request,file);
        return result;
    }

}
