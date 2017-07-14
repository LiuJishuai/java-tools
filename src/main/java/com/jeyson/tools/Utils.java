package com.jeyson.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by  liujishuai
 * Create Date: 2017/7/12 19:30
 * Description:
 */
public class Utils {
    //Log logger = LogFactory.getLog(getClass());
    Logger logger= LoggerFactory.getLogger(getClass());
    Log log=LogFactory.getLog(getClass());

    @Test
    public void testLogger() {
        String sk1="SKKK";
        String sk2="sss";
        String sk3="ssss";
        String sk4="11111";
        logger.info("Hello:{},{},{},{}",sk1,sk2,sk3,sk4);

    }
}
