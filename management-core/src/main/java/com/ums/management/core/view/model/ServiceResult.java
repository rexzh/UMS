package com.ums.management.core.view.model;

public class ServiceResult<TResult> {
    private boolean success;
    private int code;
    private String reason;
    private TResult result;


    public ServiceResult(TResult result) {
        this.setResult(result);
        this.setCode(200);
        this.setReason("");
        this.setSuccess(true);
    }

    public ServiceResult(int code, String reason) {
        this.setCode(code);
        this.setReason(reason);
        this.setSuccess(false);
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TResult getResult() {
        return result;
    }

    public void setResult(TResult result) {
        this.result = result;
    }
}
