package com.listen.sspg.service;

import java.io.File;
import java.util.LinkedHashMap;

import com.github.pagehelper.util.StringUtil;
import com.listen.sspg.basecore.ApiAcceptObj;
import com.listen.sspg.basecore.ApiReturnObj;
import com.listen.sspg.baseenum.ImgPathEnum;
import com.listen.sspg.tools.FastJsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 图片管理
 * @author winfo064
 * @className ImgInfoServiceImpl
 * @date 2019/2/28
 **/
@Service
public class ImgInfoServiceImpl {

    /**
     * 获取顶部图片
     * @param apiAcceptObj
     * @return
     */
    public ApiReturnObj<Object> getHeadImgs(@Validated ApiAcceptObj apiAcceptObj){
        ApiReturnObj<Object> returnObj = new ApiReturnObj<>(2,"参数有误");
        String parameterJson = apiAcceptObj.getParameterJson();
        if (parameterJson != null && !"".equals(parameterJson)) {
            int code = Integer.parseInt(FastJsonUtil.analyticJsonObjectStr(parameterJson, "code"));
            String path = ImgPathEnum.set(code);
            if(StringUtil.isNotEmpty(path)){
                File file = new File(path);
                File[] tempList = file.listFiles();
                LinkedHashMap<String,String> files = new LinkedHashMap<>();
                for (File f:tempList) {
                    if (!f.isDirectory()) {
                        files.put(f.getName(),f.getPath());
                    }
                }
                returnObj = new ApiReturnObj<>(1,files);
            }
        }
        return returnObj;
    }
}
