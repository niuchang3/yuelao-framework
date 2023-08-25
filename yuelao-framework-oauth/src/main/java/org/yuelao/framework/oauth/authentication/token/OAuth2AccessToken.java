package org.yuelao.framework.oauth.authentication.token;

import java.time.Instant;
import java.util.Set;

public class OAuth2AccessToken extends AbstractOAuth2Token {
	
	private final Set<String> scopes;
	
	public OAuth2AccessToken(String tokenValue) {
		this(tokenValue, null, null, null);
	}
	
	public OAuth2AccessToken(String tokenValue, Instant issuedAt, Instant expiresAt) {
		this(tokenValue, issuedAt, expiresAt, null);
	}
	
	public OAuth2AccessToken(String tokenValue, Instant issuedAt, Instant expiresAt, Set<String> scopes) {
		super(tokenValue, issuedAt, expiresAt);
		this.scopes = scopes;
	}
	
}
