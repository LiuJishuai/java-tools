package com.jeyson.tools.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @Author: liujishuai
 * @Time: 2018/2/9 11:01
 * @Description:
 */
public class HttpUtils {
    public static String getMethod(String url) {
        HttpClient httpClient = new HttpClient();
        GetMethod get = new GetMethod(url);
        try {
            int e = httpClient.executeMethod(get);
            if(e==200){
                String response=get.getResponseBodyAsString();
                System.out.println("返回结果："+response);
                return response;
            }
            System.out.println("通信错误，错误码："+e);
            return null;
        } catch (Exception e) {
            System.out.println("GET请求异常："+e);
        }
        return null;
    }

    public static String postMethod(String url, NameValuePair[] parameters) {
        HttpClient httpClient = new HttpClient();
        PostMethod post = new PostMethod(url);
        post.addParameters(parameters);
        try {
            int e = httpClient.executeMethod(post);
            if(e==200){
                String response=post.getResponseBodyAsString();
                System.out.println("返回结果："+response);
            }
            System.out.println("通信错误，错误码："+e);
            return null;
        } catch (Exception e) {
            System.out.println("POST请求异常："+e);
        }
        return null;
    }
}
