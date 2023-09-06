package org.yuelao.framework.oauth.authentication.handler;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
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
		
		ResultModel<Object> failed;
		if (authException instanceof AbstractAuthenticationException) {
			AbstractAuthenticationException exception = (AbstractAuthenticationException) authException;
			failed = ResultModel.failed(exception.getCode(), authException, authException.getMessage(), request.getRequestURI());
		} else {
			failed = ResultModel.failed(CustomizationHttpStatus.ERROR.getCode(), authException, authException.getMessage(), request.getRequestURI());
		}
		ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
		messageConverter.write(failed, MediaType.APPLICATION_JSON, httpResponse);
	}
}
