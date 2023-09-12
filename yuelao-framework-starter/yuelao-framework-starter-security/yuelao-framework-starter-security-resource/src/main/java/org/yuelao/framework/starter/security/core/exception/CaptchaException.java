package org.yuelao.framework.starter.security.core.exception;

import org.yuelao.common.core.constants.CustomizationHttpStatus;

public class CaptchaException extends AbstractAuthenticationException {
	
	public CaptchaException() {
		super(CustomizationHttpStatus.CAPTCHA_EXCEPTION);
	}
	
	public CaptchaException(Throwable cause) {
		super(CustomizationHttpStatus.CAPTCHA_EXCEPTION, cause);
	}
	
	public CaptchaException(String message) {
		super(CustomizationHttpStatus.CAPTCHA_EXCEPTION, message);
	}
}
