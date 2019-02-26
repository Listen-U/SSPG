package com.listen.sspg.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * @className FastJsonUtil
 * @author Listen
 * @date 2019/2/26
 **/
public class FastJsonUtil {
    private static final SerializeConfig config;

    static {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        config = new SerializeConfig();
        // 日期格式化
        config.put(java.util.Date.class, new SimpleDateFormatSerializer(dateFormat));
    }
    private static final SerializerFeature[] features = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty,
            // Boolean字段如果为null,输出为false,而非null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 结果是否格式化,默认为false
            SerializerFeature.PrettyFormat
    };

    /**
     * JsonStr-->Object
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static Object jsonStrToObject(String jsonStr, Class clazz) {
        Object obj = null;
        if (jsonStr != null && !"".equals(jsonStr)) {
            obj = JSON.parseObject(jsonStr, clazz);
        }
        return obj;
    }

    /**
     * Object-->JSON 对象转JSON字符串
     * @param obj
     * @return
     */
    public static String objectToJsonStr(Object obj) {
        String jsonStr = null;
        if (obj != null) {
            jsonStr = JSON.toJSONString(obj, config, features);
        }
        return jsonStr;
    }
}
