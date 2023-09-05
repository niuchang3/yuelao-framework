package org.yuelao.framework.starter.security.core.token;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Data
public abstract class AbstractBasicAuthenticationToken implements Authentication {
	/**
	 * 存放用户权限集合
	 */
	protected Collection<GrantedAuthority> authorities;
	/**
	 * 通常存放认证时提供的账号信息
	 */
	private Object principal;
	/**
	 * 通常存放认证时添加的秘钥信息
	 */
	private Object credentials;
	/**
	 * 通常存放用户详情
	 */
	protected Object details;
	/**
	 * 是否认证
	 */
	private boolean authenticated = false;
	
	@Override
	public String getName() {
		return principal.toString();
	}
}
