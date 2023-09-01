package org.yuelao.framework.oauth.authentication.token.auth;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 用戶名密碼认证Token
 */

@Getter
public class BasicPasswordAuthenticationToken extends AbstractAuthenticationToken {
	/**
	 * 通常放账号信息
	 */
	private final Object principal;
	/**
	 * 通常放密码信息
	 */
	private Object credentials;
	/**
	 * 二维码生成时的uuid,用于判断是否为当前用户所生成的
	 */
	private String uuid;
	
	private String captcha;
	
	public BasicPasswordAuthenticationToken(Object principal, Object credentials, String uuid, String captcha) {
		this(Lists.newArrayList(), principal, credentials, uuid, captcha);
	}
	
	public BasicPasswordAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials, String uuid, String captcha) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		this.uuid = uuid;
		this.captcha = captcha;
	}
	
	@Override
	public Object getCredentials() {
		return credentials;
	}
	
	@Override
	public Object getPrincipal() {
		return principal;
	}
	
	
	/**
	 * 清理凭证
	 */
	@Override
	public void eraseCredentials() {
		this.credentials = null;
		this.uuid = null;
		this.captcha = null;
	}
	
	
}
