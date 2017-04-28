package com.ums.management.core.utility;

/**
 * Created by Rex on 2017/4/28.
 */
public class CommonException extends RuntimeException {
    public CommonException(String msg) {
        super(msg);
    }

    public CommonException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
