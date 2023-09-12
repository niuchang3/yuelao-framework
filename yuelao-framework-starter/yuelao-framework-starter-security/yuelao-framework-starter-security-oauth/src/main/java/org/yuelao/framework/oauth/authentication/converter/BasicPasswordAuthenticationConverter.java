package org.yuelao.framework.oauth.authentication.converter;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.yuelao.framework.oauth.authentication.BasicPasswordAuthenticationToken;
import org.yuelao.framework.oauth.authentication.GrantType;

import javax.servlet.http.HttpServletRequest;

public class BasicPasswordAuthenticationConverter implements AuthenticationConverter {
	
	
	@Override
	public Authentication convert(HttpServletRequest request) {
		String grantType = request.getParameter(GrantType.GRANT_TYPE_NAME);
		if (!GrantType.PasswordParams.password.name().equals(grantType)) {
			return null;
		}
		
		BasicPasswordAuthenticationToken authenticationToken = new BasicPasswordAuthenticationToken();
		authenticationToken.setUuid(request.getParameter(GrantType.PasswordParams.uuid.name()));
		authenticationToken.setCaptcha(request.getParameter(GrantType.PasswordParams.captcha.name()));
		authenticationToken.setPrincipal(request.getParameter(GrantType.PasswordParams.account.name()));
		authenticationToken.setCredentials(request.getParameter(GrantType.PasswordParams.password.name()));
		return authenticationToken;
	}
	
	
}
