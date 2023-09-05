package org.yuelao.framework.starter.security.core.encoder;

import org.springframework.security.core.Authentication;

import java.text.ParseException;
import java.util.Date;

public interface TokenEncoder {
	
	
	/**
	 * 对Authentication.details 进行编码生成加密TokenValue
	 *
	 * @param tokenType
	 * @param expires
	 * @param authentication
	 * @return
	 */
	String encode(String tokenType, Date expires, Authentication authentication);
	
	/**
	 * 对认证信息解码
	 *
	 * @return
	 */
	Authentication decode(String token) throws ParseException;
}
