package org.yuelao.framework.oauth.authentication.token;

import lombok.Data;

import java.time.Instant;

@Data
public abstract class AbstractOAuth2Token implements OAuth2Token {
	
	private final String tokenValue;
	
	private final Instant issuedAt;
	
	private final Instant expiresAt;
	
	public AbstractOAuth2Token(String tokenValue) {
		this(tokenValue, null, null);
	}
	
	public AbstractOAuth2Token(String tokenValue, Instant issuedAt, Instant expiresAt) {
		this.tokenValue = tokenValue;
		this.issuedAt = issuedAt;
		this.expiresAt = expiresAt;
	}
}
