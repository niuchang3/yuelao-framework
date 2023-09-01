package org.yuelao.framework.starter.security.converter;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractAuthenticationConverter implements AuthenticationConverter {
	
	
	/**
	 * 从Request Parameter 中获取
	 *
	 * @param request
	 * @return
	 */
	protected String extractRequestParameter(HttpServletRequest request, String name) {
		return request.getParameter(name);
	}
	
	/**
	 * 从请求头中获取
	 *
	 * @param request
	 * @return
	 */
	protected String extractRequestHeader(HttpServletRequest request, String name) {
		return request.getHeader(name);
	}
	
	
	/**
	 * 判断是否支持
	 *
	 * @param request
	 * @return
	 */
	protected abstract boolean supports(HttpServletRequest request);
	
	
	@Override
	public Authentication convert(HttpServletRequest request) {
		if (!supports(request)) {
			return null;
		}
		return doConvert(request);
	}
	
	/**
	 * @param request
	 * @return
	 */
	protected abstract Authentication doConvert(HttpServletRequest request) throws RuntimeException;
	
}
