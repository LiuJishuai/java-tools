package com.jeyson.tools.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: liujishuai
 * @Time: 2018/6/5 17:55
 * @Description:
 */
public class JsonMapper {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {

        }
        return "";
    }

    public static <T> T fromJson(String src, Class<T> clazz) {
        if (StringUtils.isEmpty(src)) {
            return null;
        }
        try {
           return  mapper.readValue(src, clazz);
        } catch (Exception e) {
        }
        return null;
    }

}
