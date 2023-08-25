package org.yuelao.framework.oauth.authentication.token;

import java.time.Instant;

public interface OAuth2Token {
	
	/**
	 * tokenValue
	 *
	 * @return
	 */
	String getTokenValue();
	
	/**
	 * token发行时间
	 *
	 * @return
	 */
	Instant getIssuedAt();
	
	/**
	 * Token过期时间
	 *
	 * @return
	 */
	Instant getExpiresAt();
}
