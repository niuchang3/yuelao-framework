package org.yuelao.framework.oauth.authentication.provider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.yuelao.framework.oauth.authentication.token.auth.BasicPhoneAuthenticationToken;

/**
 * 密码验证支持
 */
public class BasicPhoneAuthenticationProvider extends BasicAuthenticationProvider {
	
	
	/**
	 * 认证业务逻辑处理
	 *
	 * @param authentication the authentication request object.
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		BasicPhoneAuthenticationToken authenticationToken = (BasicPhoneAuthenticationToken) authentication;
		// 账号验证业务逻辑
		// 正常返回的Details 不应该包含密码信息
		UserDetails details = new User("张三", "", AuthorityUtils.createAuthorityList("ADMIN"));
		
		authenticationToken.eraseCredentials();
		authenticationToken.setDetails(details);
		authenticationToken.setAuthenticated(true);
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
		return authentication.isAssignableFrom(BasicPhoneAuthenticationToken.class);
	}
}
