package org.yuelao.framework.starter.security.core.handler;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.util.ObjectUtils;
import org.yuelao.common.core.constants.CustomizationHttpStatus;
import org.yuelao.common.core.web.ResultModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	
	private HttpMessageConverter<Object> messageConverter;
	
	public AccessDeniedHandlerImpl(HttpMessageConverter<Object> messageConverter) {
		this.messageConverter = messageConverter;
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		ResultModel<Object> failed = null;
		if (accessDeniedException.getClass().isAssignableFrom(AccessDeniedException.class)) {
			failed = ResultModel.failed(CustomizationHttpStatus.UNAUTHORIZED.getCode(), accessDeniedException, CustomizationHttpStatus.UNAUTHORIZED.getDescription(), request.getRequestURI());
		}
		
		if (ObjectUtils.isEmpty(failed)) {
			failed = ResultModel.failed(CustomizationHttpStatus.ERROR.getCode(), accessDeniedException, accessDeniedException.getMessage(), request.getRequestURI());
		}
		ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
		messageConverter.write(failed, MediaType.APPLICATION_JSON, httpResponse);
	}
}
