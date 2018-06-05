package com.jeyson.tools.collection.source;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;

/**
 * @Author: liujishuai
 * @Time: 2018/5/29 20:47
 * @Description:
 */
public class HashMapUnit {

    @Test
    public void  testHashCode(){
        HashMap map=new HashMap(3);
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");
        System.out.println(JSON.toJSONString(map));
    }
}
