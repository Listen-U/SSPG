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
    HeadImg{
        @Override
        public String get() {
            return "D:\\WXFile\\headImg";
        }
    };

    public static String set(int code){
        String path = null;
        switch (code){
            case 1 : path = ImgPathEnum.HeadImg.get();break;
            default: path = null;
        }
        return path;
    }
    public abstract String get();
}
