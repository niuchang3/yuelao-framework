package org.yuelao.framework.starter.security.core.exception;

import org.yuelao.common.core.constants.CustomizationHttpStatus;

public class TokenExpiresException extends AbstractAuthenticationException {
	
	public TokenExpiresException() {
		super(CustomizationHttpStatus.TOKEN_EXPIRES);
	}
	
	public TokenExpiresException(Throwable cause) {
		super(CustomizationHttpStatus.TOKEN_EXPIRES, cause);
	}
}
