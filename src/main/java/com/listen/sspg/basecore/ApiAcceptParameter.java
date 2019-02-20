package com.listen.sspg.basecore;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ApiAcceptParameter implements Serializable {
//    @NotNull(message = "项目编号不能为空(projectSu Can't be empty)")
//    private String projectSu ;	//是哪个项目的
    //@ApiModelProperty(value = "请求json对象/数组")
    private String parameterJson;	//请求json对象/数组
    //@ApiModelProperty(value = "用户访问令牌Token",required = true)
    private String accessToken;//用户访问令牌

    public String getParameterJson() {
        return parameterJson;
    }

    public void setParameterJson(String parameterJson) {
        this.parameterJson = parameterJson;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
