package org.yuelao.framework.starter.security.core.exception;

import org.yuelao.common.core.constants.CustomizationHttpStatus;

public class UsernameNotFoundException extends AbstractAuthenticationException {
	
	public UsernameNotFoundException() {
		super(CustomizationHttpStatus.USER_NOT_FOUND_EXCEPTION);
	}
	
	public UsernameNotFoundException(Throwable cause) {
		super(CustomizationHttpStatus.USER_NOT_FOUND_EXCEPTION, cause);
	}
}
