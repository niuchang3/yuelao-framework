package org.yuelao.framework.starter.security.core.exception;

import org.yuelao.common.core.constants.CustomizationHttpStatus;

public class TokenFormatException extends AbstractAuthenticationException {
	
	
	public TokenFormatException() {
		super(CustomizationHttpStatus.TOKEN_FORMAT_EXCEPTION);
	}
	
	public TokenFormatException(Throwable cause) {
		super(CustomizationHttpStatus.TOKEN_FORMAT_EXCEPTION, cause);
	}
	
}
