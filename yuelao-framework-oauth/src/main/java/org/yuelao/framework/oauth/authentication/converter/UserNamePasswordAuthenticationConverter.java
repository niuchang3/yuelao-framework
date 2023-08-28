package org.yuelao.framework.oauth.authentication.converter;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.yuelao.framework.oauth.authentication.UserNamePasswordGrantType;
import org.yuelao.framework.oauth.authentication.token.password.UserNamePasswordAuthenticationToken;

import javax.servlet.http.HttpServletRequest;

public class UserNamePasswordAuthenticationConverter extends AbstractAuthenticationConverter implements AuthenticationConverter {
	
	
	@Override
	protected Authentication doConvert(HttpServletRequest request) {
		String userName = extractRequestParameter(request, UserNamePasswordGrantType.USERNAME);
		String password = extractRequestParameter(request, UserNamePasswordGrantType.PASSWORD);
		String uuid = extractRequestParameter(request, UserNamePasswordGrantType.UUID);
		String captcha = extractRequestParameter(request, UserNamePasswordGrantType.CAPTCHA);
		return new UserNamePasswordAuthenticationToken(userName, password, uuid, captcha);
	}
	
	
	@Override
	String getGrantType() {
		return UserNamePasswordGrantType.GRANT_TYPE.getCode();
	}
}
