package org.yuelao.framework.starter.security.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

public class BearerTokenAuthenticationToken extends AbstractAuthenticationToken {
	
	@Getter
	@Setter
	private String tokenType;
	/**
	 * 过期时间
	 */
	@Getter
	@Setter
	private Date expires;
	/**
	 * 通常放账号信息
	 */
	private Object principal;
	/**
	 * 通常放密码信息
	 */
	private Object credentials;
	
	
	public BearerTokenAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
	}
	
	public BearerTokenAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials) {
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
