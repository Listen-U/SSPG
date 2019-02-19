package com.listen.sspg.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Test")
public class TestController {
    @RequestMapping(value = "/hello", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getShipControlRule() {
        return "hello";
    }

}
