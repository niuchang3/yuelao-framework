package org.yuelao.framework.starter.security.core.exception;

import org.yuelao.common.core.constants.CustomizationHttpStatus;

public class ProhibitedUserException extends AbstractAuthenticationException {
	
	public ProhibitedUserException() {
		super(CustomizationHttpStatus.PROHIBITED_USE_EXCEPTION);
	}
	
	public ProhibitedUserException(Throwable cause) {
		super(CustomizationHttpStatus.PROHIBITED_USE_EXCEPTION, cause);
	}
}
