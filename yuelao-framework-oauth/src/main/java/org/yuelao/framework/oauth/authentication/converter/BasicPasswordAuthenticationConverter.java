package org.yuelao.framework.oauth.authentication.converter;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.yuelao.framework.oauth.authentication.token.OauthGrantType;
import org.yuelao.framework.oauth.authentication.token.OauthPasswordGrantType;
import org.yuelao.framework.oauth.authentication.token.auth.BasicPasswordAuthenticationToken;
import org.yuelao.framework.starter.security.converter.AbstractAuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

public class BasicPasswordAuthenticationConverter extends AbstractAuthenticationConverter implements AuthenticationConverter {
	
	
	@Override
	protected boolean supports(HttpServletRequest request) {
		String grantType = extractRequestHeader(request, OauthGrantType.GRANT_TYPE_NAME);
		return OauthPasswordGrantType.GRANT_TYPE.getCode().equals(grantType);
	}
	
	@Override
	protected Authentication doConvert(HttpServletRequest request) {
		String userName = extractRequestParameter(request, OauthPasswordGrantType.USERNAME.getCode());
		String password = extractRequestParameter(request, OauthPasswordGrantType.PASSWORD.getCode());
		String uuid = extractRequestParameter(request, OauthPasswordGrantType.UUID.getCode());
		String captcha = extractRequestParameter(request, OauthPasswordGrantType.CAPTCHA.getCode());
		return new BasicPasswordAuthenticationToken(userName, password, uuid, captcha);
	}
	
	
	
}
