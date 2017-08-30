package com.ums.management.web.view.vo;

import java.util.HashMap;
import java.util.Map;

import com.ums.management.core.view.model.ServiceResult;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ResponseVO {

    private static final String RESPONSE_KEY_MESSAGE = "error";
    private static final String RESPONSE_KEY_STACK = "stack";
    private static final String RESPONSE_KEY_CODE = "code";

    private boolean result = true;

    private Map<String, Object> data = new HashMap<>();

    private ResponseVO() {

    }

    public static ResponseVO buildSuccessResponse() {
        return new ResponseVO();
    }

    public static ResponseVO buildErrorResponse(String message) {
        ResponseVO response = new ResponseVO();
        response.result = false;
        response.data.put(RESPONSE_KEY_MESSAGE, message);
        return response;
    }

    public static ResponseVO buildErrorResponse(String message, Throwable t) {
        ResponseVO response = new ResponseVO();
        response.result = false;
        response.data.put(RESPONSE_KEY_MESSAGE, message);
        response.data.put(RESPONSE_KEY_STACK, ExceptionUtils.getRootCauseStackTrace(t));
        return response;
    }

    public static ResponseVO buildResponse(ServiceResult result) {
        ResponseVO response = new ResponseVO();
        response.result = result.getSuccess();
        response.addData(RESPONSE_KEY_MESSAGE, result.getReason());
        response.addData(RESPONSE_KEY_CODE, result.getCode());
        return response;
    }

    public boolean getResult() {
        return result;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseVO addData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
