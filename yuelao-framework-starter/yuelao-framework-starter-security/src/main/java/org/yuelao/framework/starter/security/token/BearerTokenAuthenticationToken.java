package org.yuelao.framework.starter.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class BearerTokenAuthenticationToken extends AbstractAuthenticationToken {
	
	/**
	 * 通常放账号信息
	 */
	private final Object principal;
	/**
	 * 通常放密码信息
	 */
	private Object credentials;
	
	public BearerTokenAuthenticationToken(Object principal, Object credentials) {
		this(principal, credentials, null);
	}
	
	public BearerTokenAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
	}
	
	@Override
	public Object getCredentials() {
		return credentials;
	}
	
	@Override
	public Object getPrincipal() {
		return principal;
	}
}
