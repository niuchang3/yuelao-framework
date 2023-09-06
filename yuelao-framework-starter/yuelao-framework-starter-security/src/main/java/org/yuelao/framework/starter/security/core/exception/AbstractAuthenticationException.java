package org.yuelao.framework.starter.security.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;
import org.yuelao.common.core.constants.HttpStatusCodeConverter;

public abstract class AbstractAuthenticationException extends AuthenticationException {
	
	@Getter
	@Setter
	protected Integer code;
	
	public AbstractAuthenticationException(HttpStatusCodeConverter httpStatusCode) {
		super(httpStatusCode.getDescription());
		this.code = httpStatusCode.getCode();
	}
	
	public AbstractAuthenticationException(HttpStatusCodeConverter httpStatusCode, Throwable cause) {
		super(httpStatusCode.getDescription(), cause);
		this.code = httpStatusCode.getCode();
	}
}
