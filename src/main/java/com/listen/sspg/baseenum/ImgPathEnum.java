package com.listen.sspg.baseenum;

/**
 * 图片路径枚举
 * @author Listen
 * @date 2019/2/28
 */
public enum ImgPathEnum {

    /**
     * 顶部图片路径
     */
    HeadImg("D:\\WXFile\\headImg");


    private final String path;

    ImgPathEnum(final String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }

    public static String set(int code){
        String path;
        switch (code){
            case 1 : path = ImgPathEnum.HeadImg.getPath();break;
            default: path = null;
        }
        return path;
    }
}
