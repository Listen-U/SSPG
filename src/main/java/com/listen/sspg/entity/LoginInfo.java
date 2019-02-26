package com.listen.sspg.entity;

import org.springframework.ui.Model;

/**
 * 登录信息
 * @author Listen
 * @date 2019/2/26
 */
public class LoginInfo {
    private Model model;
    private String code;
    private String rawData;
    private String signature;
    private String encrypteData;
    private String iv;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEncrypteData() {
        return encrypteData;
    }

    public void setEncrypteData(String encrypteData) {
        this.encrypteData = encrypteData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
