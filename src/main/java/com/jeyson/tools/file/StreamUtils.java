package com.jeyson.tools.file;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @Author: liujishuai
 * @Time: 2018/2/2 16:37
 * @Description:
 */
public class StreamUtils {
    public static byte[] transferInputStream2Bytes(InputStream is) {
        byte[] data = null;

        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int byteRead;
            while((byteRead = is.read(buffer, 0, 1024)) != -1) {
                outStream.write(buffer, 0, byteRead);
            }

            outStream.flush();
            data = outStream.toByteArray();
        } catch (Exception e) {
            System.out.println(" error!"+ e.getMessage());
        }

        return data;
    }

    public static byte[] getInputStreamBytes(InputStream is) {
        try {
           return IOUtils.toByteArray(is);
        } catch (Exception e) {
            System.out.println(" error!"+ e.getMessage());
        }

        return null;
    }
    public static InputStream transferBytes2InputStream(byte[] bytes){
        return new ByteArrayInputStream(bytes);
    }
}
