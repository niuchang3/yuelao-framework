package org.yuelao.framework.starter.security.core.token;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;


@Data
public abstract class AbstractBasicAuthenticationToken implements Authentication {
	/**
	 * 存放用户权限集合
	 */
	protected Collection<GrantedAuthority> authorities;
	/**
	 * 通常存放认证时提供的账号信息
	 */
	protected Object principal;
	/**
	 * 通常存放认证时添加的秘钥信息
	 */
	protected Object credentials;
	/**
	 * 通常存放用户详情
	 */
	protected Object details;
	/**
	 * Token类型
	 */
	protected String tokenType;
	/**
	 * 用户获取token时的ip信息
	 */
	protected String ip;
	/**
	 * 过期时间
	 */
	protected Date expires;
	/**
	 * 是否认证
	 */
	protected boolean authenticated = false;
	
	@Override
	public String getName() {
		return principal.toString();
	}
	
	
}
