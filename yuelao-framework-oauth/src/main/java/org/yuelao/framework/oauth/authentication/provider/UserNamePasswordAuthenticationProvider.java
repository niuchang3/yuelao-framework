package org.yuelao.framework.oauth.authentication.provider;

import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.yuelao.framework.oauth.authentication.token.UserNamePasswordAuthenticationToken;

/**
 * 用户名密码验证支持程序
 */
public class UserNamePasswordAuthenticationProvider implements AuthenticationProvider {
	
	
	@Setter
	private UserDetailsService userDetailsService;
	
	/**
	 * 认证业务逻辑处理
	 *
	 * @param authentication the authentication request object.
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 假装认证成功
		
		authentication.setAuthenticated(false);
		return authentication;
	}
	
	/**
	 * 判断是否支持
	 *
	 * @param authentication
	 * @return
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UserNamePasswordAuthenticationToken.class);
	}
}
