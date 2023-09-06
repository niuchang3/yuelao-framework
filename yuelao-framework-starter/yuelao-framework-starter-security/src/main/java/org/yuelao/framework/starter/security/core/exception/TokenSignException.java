package org.yuelao.framework.starter.security.core.exception;

import org.yuelao.common.core.constants.CustomizationHttpStatus;

public class TokenSignException extends AbstractAuthenticationException {
	
	
	public TokenSignException() {
		super(CustomizationHttpStatus.TOKEN_SIGN_EXCEPTION);
	}
	
	public TokenSignException(Throwable cause) {
		super(CustomizationHttpStatus.TOKEN_SIGN_EXCEPTION, cause);
	}
	
}
