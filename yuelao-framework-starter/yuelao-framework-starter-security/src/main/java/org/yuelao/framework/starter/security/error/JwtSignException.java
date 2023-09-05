package org.yuelao.framework.starter.security.error;

public class JwtSignException extends JwtException {
	
	
	public JwtSignException(String msg) {
		super(msg);
	}
	
	public JwtSignException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
