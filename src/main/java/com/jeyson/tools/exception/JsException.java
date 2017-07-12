package com.jeyson.tools.exception;

import java.io.Serializable;

/**
 * Created by  liujishuai
 * Create Date: 2017/7/12 19:36
 * Description: 定制Exception
 */
public class JsException extends RuntimeException implements Serializable {
    public JsException(String message) {
        super(message, new Throwable(message));
    }

    public JsException(Throwable cause) {
        super(cause);
    }

    public JsException(String message, Throwable cause) {
        super(message, cause);
    }
}
