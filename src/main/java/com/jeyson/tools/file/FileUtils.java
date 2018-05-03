package com.jeyson.tools.file;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Author: liujishuai
 * @Time: 2018/2/2 17:13
 * @Description:
 */
public class FileUtils {

    public static void getFileByInputStream(InputStream inputStream,File file){
        try {
            org.apache.commons.io.FileUtils.writeByteArrayToFile(file, IOUtils.toByteArray(inputStream));
        }catch (Exception e){
            System.out.println("FileUtils getFileByInputStream error "+e.getMessage());
        }
    }

    public static void getFileByBytes(byte[] bytes,File file){
        try {
            org.apache.commons.io.FileUtils.writeByteArrayToFile(file, bytes);
        }catch (Exception e){
            System.out.println("FileUtils getFileByInputStream error "+e.getMessage());
        }
    }

    public  static InputStream getInputStreamByFile(File file) {
        if(null==file){
            return null;
        }
        try {
           return org.apache.commons.io.FileUtils.openInputStream(file);
        }catch (Exception e){
            System.out.println("FileUtils getInputStreamByFile error "+e.getMessage());
        }
      return null;
    }

    public  static byte[] getByteByFile(File file) throws FileNotFoundException {
        if(null==file){
            return null;
        }
        try {
            return org.apache.commons.io.FileUtils.readFileToByteArray(file);
        }catch (Exception e){
            System.out.println("FileUtils getInputStreamByFile error "+e.getMessage());
        }
        return null;
    }
}
