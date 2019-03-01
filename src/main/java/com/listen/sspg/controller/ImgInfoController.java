package com.listen.sspg.controller;

import com.listen.sspg.basecore.ApiAcceptObj;
import com.listen.sspg.basecore.ApiReturnObj;
import com.listen.sspg.service.ImgInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图片管理
 * @author Listen
 * @className ImgInfoController
 * @date 2019/2/28
 **/
@RestController
@RequestMapping("/imgInfo")
public class ImgInfoController {
    @Autowired
    private ImgInfoServiceImpl imgInfoService;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/headImgs", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ApiReturnObj<Object> headImgs(@Validated ApiAcceptObj apiAcceptObj) {
        return imgInfoService.getHeadImgs(apiAcceptObj);
    }
}
