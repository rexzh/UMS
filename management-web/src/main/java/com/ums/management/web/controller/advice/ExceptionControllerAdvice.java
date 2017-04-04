package com.ums.management.web.controller.advice;

import javax.servlet.http.HttpServletRequest;

import com.ums.management.web.view.vo.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations=RestController.class)
public class ExceptionControllerAdvice {

	private static Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseVO handle(HttpServletRequest request, Exception exception) {
		logger.error("Failed to handle request: " + request.getRequestURI(), exception);
		
		return ResponseVO.buildErrorResponse("Server Side Error", exception);
	}
}
