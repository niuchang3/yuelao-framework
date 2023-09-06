package org.yuelao.framework.starter.security.core.exception;

import org.yuelao.common.core.constants.CustomizationHttpStatus;

public class TokenParseException extends AbstractAuthenticationException {
	
	public TokenParseException() {
		super(CustomizationHttpStatus.TOKEN_PARSE_EXCEPTION);
	}
	
	public TokenParseException(Throwable cause) {
		super(CustomizationHttpStatus.TOKEN_PARSE_EXCEPTION, cause);
	}
}
