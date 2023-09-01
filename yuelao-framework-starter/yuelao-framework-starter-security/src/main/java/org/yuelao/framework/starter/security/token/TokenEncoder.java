package org.yuelao.framework.starter.security.token;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public interface TokenEncoder<T extends OAuth2Token> {
	
	
	/**
	 * 对认证信息编码
	 *
	 * @param authentication
	 * @return
	 */
	T encode(Authentication authentication);
	
	/**
	 * 对认证信息解码
	 *
	 * @return
	 */
	Authentication decode(String token) throws ParseException;
	
	
	String extractToken(HttpServletRequest request);
}
