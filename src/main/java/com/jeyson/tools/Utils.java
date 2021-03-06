package com.jeyson.tools;
import com.alibaba.fastjson.JSON;
import com.jeyson.tools.file.BufferImageUtil;
import com.jeyson.tools.file.FileUtils;
import com.jeyson.tools.http.*;
import com.jeyson.tools.jackson.JsonMapper;
import com.jeyson.tools.mail.*;
import com.jeyson.tools.page.PageInfo;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by  liujishuai
 * Create Date: 2017/7/12 19:30
 * Description:
 */
public class Utils {

    @Test
    public void testJSON() throws Exception {
        List<Long> list=new ArrayList<Long>();
        List<Long> list1=new ArrayList<Long>();
        list1.add(1L);
        list1.add(2L);

    }
    @Test
    public void testLogger() throws Exception {
        String sk= HttpUtils.getMethod("http://code.taobao.org/");
        System.out.println(sk);

    }

    @Test
    public void testMail() throws Exception {
        ReadMailUtils.resceive();
    }

    @Test
    public void testImage() throws Exception {
        File file=new File("C:\\Users\\jeyson\\Desktop\\image\\123.jpg");
       byte[] bytes= FileUtils.getByteByFile(file);
        System.out.println("byte："+bytes);
        FileUtils.getFileByBytes(bytes,new File("C:\\Users\\jeyson\\Desktop\\image\\123333.jpg"));
    }

    @Test
    public void testResizeImage() throws Exception {
        File file=new File("C:\\Users\\jeyson\\Desktop\\image\\123.jpg");
        InputStream inputStream= FileUtils.getInputStreamByFile(file);
        ByteArrayOutputStream outputStream= BufferImageUtil.resizeImage(inputStream,50,50,1.0f);
        FileUtils.getFileByBytes(outputStream.toByteArray(),new File("C:\\Users\\jeyson\\Desktop\\image\\111111.jpg"));
    }

    @Test
    public void  testJackson(){
        PageInfo pageInfo=new PageInfo();
        pageInfo.setList(null);
        pageInfo.setPageIndex(1);
        pageInfo.setPageNum(1);
        pageInfo.setTotalNum(12);
        String json=JsonMapper.toJson(pageInfo);
        PageInfo info=JsonMapper.fromJson(json,PageInfo.class);
        System.out.println("json:"+json);
        System.out.println("info:"+info);
    }

}
