package com.listen.sspg.basecore;

import java.io.Serializable;

/**
 * 接口统一接受对象
 * @author Listen
 * @date 2019/2/26
 */
public class ApiAcceptObj implements Serializable {
//    @NotNull(message = "不能为空(can't be empty)")
    /**
     * @ApiModelProperty(value = "请求json对象/数组")
     */
    private String parameterJson;
    /**
     *@ApiModelProperty(value = "用户访问令牌Token",required = true)
     */
    private String accessToken;

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
