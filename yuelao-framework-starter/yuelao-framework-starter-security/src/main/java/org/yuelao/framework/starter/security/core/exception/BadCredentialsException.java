package org.yuelao.framework.starter.security.core.exception;

import org.yuelao.common.core.constants.CustomizationHttpStatus;

public class BadCredentialsException extends AbstractAuthenticationException {
	
	public BadCredentialsException() {
		super(CustomizationHttpStatus.BAD_CREDENTIALS_EXCEPTION);
	}
	
	public BadCredentialsException(Throwable cause) {
		super(CustomizationHttpStatus.BAD_CREDENTIALS_EXCEPTION, cause);
	}
}
