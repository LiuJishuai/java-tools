package com.jeyson.tools;

import com.jeyson.tools.http.HttpUtils;
import com.jeyson.tools.mail.ReadMailUtils;
import org.apache.commons.httpclient.HttpClient;
import org.junit.Test;



/**
 * Created by  liujishuai
 * Create Date: 2017/7/12 19:30
 * Description:
 */
public class Utils {

    @Test
    public void testLogger() throws Exception {
        String sk=HttpUtils.getMethod("http://code.taobao.org/");
        System.out.println(sk);

    }
}
