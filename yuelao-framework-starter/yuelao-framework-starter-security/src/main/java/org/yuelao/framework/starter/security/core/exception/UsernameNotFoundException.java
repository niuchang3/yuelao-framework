package org.yuelao.framework.starter.security.core.exception;

import org.yuelao.common.core.constants.CustomizationHttpStatus;
import org.yuelao.common.core.constants.HttpStatusCodeConverter;

public class UsernameNotFoundException extends AbstractAuthenticationException{
	
	public UsernameNotFoundException() {
		super(CustomizationHttpStatus.TOKEN_FORMAT_EXCEPTION);
	}
	
	public UsernameNotFoundException(HttpStatusCodeConverter httpStatusCode, Throwable cause) {
		super(httpStatusCode, cause);
	}
}
