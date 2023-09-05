package org.yuelao.framework.starter.security.error;


import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yuelao.common.core.constants.HttpStatusCode;
import org.yuelao.common.core.error.GlobalExceptionHandler;
import org.yuelao.common.core.web.ResultModel;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalSecurityExceptionHandler extends GlobalExceptionHandler {
	
	@ExceptionHandler(value = AuthenticationCredentialsNotFoundException.class)
	@ResponseBody
	public ResultModel AuthenticationCredentialsNotFoundException(HttpServletRequest request, AuthenticationCredentialsNotFoundException exception) {
		return ResultModel.failed(HttpStatusCode.INSUFFICIENT_PERMISSIONS, exception, HttpStatusCode.INSUFFICIENT_PERMISSIONS.getDescription(), request.getRequestURI());
	}
}
