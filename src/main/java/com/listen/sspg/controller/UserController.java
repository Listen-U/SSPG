package com.listen.sspg.controller;

import javax.servlet.http.HttpServletRequest;
import java.io.Writer;

import com.listen.sspg.entity.User;
import com.listen.sspg.service.UserServiceImpl;
import com.listen.sspg.tools.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 测试保存图片
     * @return
     */
    @RequestMapping(value = "/savePicture", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String savePicture(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
        String result = userService.savePicture(request,file);
        return result;
    }

}
