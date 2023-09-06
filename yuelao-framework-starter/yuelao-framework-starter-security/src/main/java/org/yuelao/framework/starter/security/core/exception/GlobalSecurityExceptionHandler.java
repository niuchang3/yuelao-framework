package org.yuelao.framework.starter.security.core.exception;


import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yuelao.common.core.constants.CustomizationHttpStatus;
import org.yuelao.common.core.error.GlobalExceptionHandler;
import org.yuelao.common.core.web.ResultModel;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalSecurityExceptionHandler extends GlobalExceptionHandler {

	
	@ExceptionHandler(value = AuthenticationCredentialsNotFoundException.class)
	@ResponseBody
	public ResultModel authenticationCredentialsNotFoundException(HttpServletRequest request, AuthenticationCredentialsNotFoundException exception){
		return ResultModel.failed(CustomizationHttpStatus.NOT_CERTIFIED.getCode(),exception,CustomizationHttpStatus.NOT_CERTIFIED.getDescription(),request.getRequestURI());
	}
}
