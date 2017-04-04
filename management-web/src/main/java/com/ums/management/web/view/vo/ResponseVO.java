package com.ums.management.web.view.vo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ResponseVO {
	
	private static final String RESPONSE_KEY_MESSAGE = "error";
	private static final String RESPONSE_KEY_STACK = "stack";
	
	private boolean result = true;
	
	private Map<String, Object> data = new HashMap<>();
	
	private ResponseVO() {
		
	}
	
	public boolean isResult() {
		return result;
	}
	public Map<String, Object> getData() {
		return data;
	}

	public ResponseVO addData(String key, Object value) {
		this.data.put(key, value);
		return this;
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
}
