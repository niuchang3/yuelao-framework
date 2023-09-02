package org.yuelao.framework.oauth.authentication.provider;

import com.google.common.collect.Lists;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.CollectionUtils;
import org.yuelao.framework.oauth.authentication.token.auth.BasicPasswordAuthenticationToken;
import org.yuelao.framework.starter.security.user.UserInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 密码验证支持
 */
public class BasicPasswordAuthenticationProvider extends BasicAuthenticationProvider {
	
	
	/**
	 * 认证业务逻辑处理
	 *
	 * @param authentication the authentication request object.
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		BasicPasswordAuthenticationToken authenticationToken = (BasicPasswordAuthenticationToken) authentication;
		// 账号验证业务逻辑
		// 正常返回的Details 不应该包含密码信息
		UserInfo details = new UserInfo("", "zhangsan", Lists.newArrayList("ADMIN"));
		
		List<String> authorities = details.getAuthorities();
		if (CollectionUtils.isEmpty(authorities)) {
			authorities = Lists.newArrayList();
		}
		
		List<SimpleGrantedAuthority> collect = authorities.stream().map(str -> new SimpleGrantedAuthority(str)).collect(Collectors.toList());
		
		BasicPasswordAuthenticationToken resToken = new BasicPasswordAuthenticationToken(collect, details.getUsername(), null, null, null);
		resToken.setDetails(details);
		resToken.setAuthenticated(true);
		return resToken;
	}
	
	/**
	 * 判断是否支持
	 *
	 * @param authentication
	 * @return
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(BasicPasswordAuthenticationToken.class);
	}
}
