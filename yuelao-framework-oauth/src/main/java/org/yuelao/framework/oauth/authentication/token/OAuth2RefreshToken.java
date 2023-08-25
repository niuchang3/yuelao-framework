package org.yuelao.framework.oauth.authentication.token;

import java.time.Instant;

public class OAuth2RefreshToken extends AbstractOAuth2Token {
	
	public OAuth2RefreshToken(String tokenValue) {
		super(tokenValue);
	}
	
	public OAuth2RefreshToken(String tokenValue, Instant issuedAt, Instant expiresAt) {
		super(tokenValue, issuedAt, expiresAt);
	}
}
