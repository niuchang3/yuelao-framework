package org.yuelao.framework.oauth.authentication.token.auth;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class BasicPhoneAuthenticationToken extends AbstractAuthenticationToken {
	
	/**
	 * 存放手机号码
	 */
	private final Object principal;
	/**
	 * 短信验证码
	 */
	private Object credentials;
	
	
	public BasicPhoneAuthenticationToken(Object principal, Object credentials) {
		this(principal,credentials,null);
	}
	
	public BasicPhoneAuthenticationToken(Object principal, Object credentials,Collection<? extends GrantedAuthority> authorities) {
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
	
	@Override
	public void eraseCredentials() {
		this.credentials = null;
	}
}
