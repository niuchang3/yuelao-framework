package org.yuelao.framework.oauth.authentication.converter;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.yuelao.framework.oauth.authentication.token.OauthGrantType;
import org.yuelao.framework.oauth.authentication.token.OauthPhoneGrantType;
import org.yuelao.framework.oauth.authentication.token.auth.BasicPhoneAuthenticationToken;
import org.yuelao.framework.starter.security.converter.AbstractAuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

public class BasicPhoneAuthenticationConverter extends AbstractAuthenticationConverter implements AuthenticationConverter {
	
	
	@Override
	protected Authentication doConvert(HttpServletRequest request) {
		String mobile = extractRequestParameter(request, OauthPhoneGrantType.MOBILE.getCode());
		String code = extractRequestParameter(request, OauthPhoneGrantType.SMS_CODE.getCode());
		return new BasicPhoneAuthenticationToken(mobile, code);
	}
	
	@Override
	protected boolean supports(HttpServletRequest request) {
		String grantType = extractRequestHeader(request, OauthGrantType.GRANT_TYPE_NAME);
		return OauthPhoneGrantType.GRANT_TYPE.getCode().equals(grantType);
	}
}
