package com.ums.management.core.view.model;

import javax.xml.ws.Service;

public class ServiceResult<TResult> {
    private boolean success;
    private int code;
    private String reason;
    private TResult result;


    public ServiceResult(TResult result) {
        this.result = result;
        this.code = 200;
        this.reason = "";
        this.success = true;
    }

    public ServiceResult(int code, String reason) {
        this.code = code;
        this.reason = reason;
        this.success = false;
    }
}
