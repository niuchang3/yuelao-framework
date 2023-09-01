package org.yuelao.framework.starter.security.exception;

import org.springframework.security.core.AuthenticationException;


/**
 * JWT 非法令牌
 */
public class JwtTokenException extends AuthenticationException {
	
	
	public JwtTokenException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public JwtTokenException(String msg) {
		super(msg);
	}
}
