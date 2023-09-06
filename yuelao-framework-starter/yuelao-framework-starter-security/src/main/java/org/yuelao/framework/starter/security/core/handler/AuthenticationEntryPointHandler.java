package org.yuelao.framework.starter.security.core.handler;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.ObjectUtils;
import org.yuelao.common.core.constants.CustomizationHttpStatus;
import org.yuelao.common.core.web.ResultModel;
import org.yuelao.framework.starter.security.core.exception.AbstractAuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 认证时产生的异常
 */
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
	
	
	private HttpMessageConverter<Object> messageConverter;
	
	
	public AuthenticationEntryPointHandler(HttpMessageConverter<Object> messageConverter) {
		this.messageConverter = messageConverter;
	}
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		
		ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
		messageConverter.write(conversion(authException, request), MediaType.APPLICATION_JSON, httpResponse);
	}
	
	
	private ResultModel conversion(AuthenticationException authException, HttpServletRequest request) {
		ResultModel<Object> failed = null;
		if (authException instanceof AbstractAuthenticationException) {
			AbstractAuthenticationException exception = (AbstractAuthenticationException) authException;
			failed = ResultModel.failed(exception.getCode(), authException, authException.getMessage(), request.getRequestURI());
		}
		if (authException instanceof AuthenticationCredentialsNotFoundException) {
			failed = ResultModel.failed(CustomizationHttpStatus.NOT_CERTIFIED, authException, CustomizationHttpStatus.NOT_CERTIFIED.getDescription(), request.getRequestURI());
		}
		
		if (ObjectUtils.isEmpty(failed)) {
			failed = ResultModel.failed(CustomizationHttpStatus.ERROR.getCode(), authException, authException.getMessage(), request.getRequestURI());
		}
		return failed;
	}
}
