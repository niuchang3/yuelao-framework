package org.yuelao.framework.starter.security.exception;

import org.springframework.security.core.AuthenticationException;

public class AccountAuthenticationException extends AuthenticationException {
	public AccountAuthenticationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public AccountAuthenticationException(String msg) {
		super(msg);
	}
}
