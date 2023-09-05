package org.yuelao.framework.starter.security.error;

import org.springframework.security.core.AuthenticationException;

public class JwtException extends AuthenticationException {
	
	public JwtException(String msg) {
		super(msg);
	}
	
	public JwtException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
