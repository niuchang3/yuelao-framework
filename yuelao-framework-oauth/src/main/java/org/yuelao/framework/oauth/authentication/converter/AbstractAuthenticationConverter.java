package org.yuelao.framework.oauth.authentication.converter;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.yuelao.framework.oauth.authentication.OauthGrantType;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractAuthenticationConverter implements AuthenticationConverter {
	
	
	/**
	 * 从Request Parameter 中获取
	 *
	 * @param request
	 * @return
	 */
	protected String extractRequestParameter(HttpServletRequest request, OauthGrantType oauthGrantType) {
		return request.getParameter(oauthGrantType.getCode());
	}
	
	/**
	 * 从请求头中获取
	 *
	 * @param request
	 * @return
	 */
	protected String extractRequestHeader(HttpServletRequest request, OauthGrantType oauthGrantType) {
		return request.getHeader(oauthGrantType.getCode());
	}
	
	
	/**
	 * 从请求头中获取授权类型
	 *
	 * @param request
	 * @return
	 */
	protected String extractGrantType(HttpServletRequest request) {
		return request.getHeader(OauthGrantType.GRANT_TYPE_NAME);
	}
	
	
	abstract String getGrantType();
	
	
	/**
	 * 判断是否支持
	 *
	 * @param request
	 * @return
	 */
	protected boolean supports(HttpServletRequest request) {
		String grantType = extractGrantType(request);
		return getGrantType().equals(grantType);
	}
	
	
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
	protected abstract Authentication doConvert(HttpServletRequest request);
}
