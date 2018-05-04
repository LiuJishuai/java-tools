package com.jeyson.tools;
import com.jeyson.tools.file.BufferImageUtil;
import com.jeyson.tools.file.FileUtils;
import com.jeyson.tools.http.*;
import com.jeyson.tools.mail.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;


/**
 * Created by  liujishuai
 * Create Date: 2017/7/12 19:30
 * Description:
 */
public class Utils {

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

}
