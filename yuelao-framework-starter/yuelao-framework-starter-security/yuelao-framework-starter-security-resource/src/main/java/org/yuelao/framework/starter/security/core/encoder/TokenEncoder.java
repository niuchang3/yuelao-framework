package org.yuelao.framework.starter.security.core.encoder;

import org.yuelao.framework.starter.security.core.token.AbstractBasicAuthenticationToken;

public interface TokenEncoder {
	
	
	/**
	 * AbstractBasicAuthenticationToken.details 进行编码生成加密TokenValue
	 *
	 * @param authentication
	 * @return
	 */
	String encode(AbstractBasicAuthenticationToken authentication);
	
	/**
	 * 对认证信息解码
	 *
	 * @return
	 */
	AbstractBasicAuthenticationToken decode(String token);
}
