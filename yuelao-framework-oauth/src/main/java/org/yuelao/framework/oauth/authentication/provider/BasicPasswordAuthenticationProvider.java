package org.yuelao.framework.oauth.authentication.provider;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;
import org.yuelao.framework.oauth.authentication.BasicPasswordAuthenticationToken;
import org.yuelao.framework.starter.security.user.UserInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Basic 密码认证
 */
public class BasicPasswordAuthenticationProvider extends AbstractBasicAuthenticationProvider {
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		BasicPasswordAuthenticationToken passwordAuthenticationToken = (BasicPasswordAuthenticationToken) authentication;
		UserInfo userInfo = getUserService().loadUserByAccount(passwordAuthenticationToken.getName());
		if(ObjectUtils.isEmpty(userInfo)){
			throw new UsernameNotFoundException("账号信息不存在。");
		}
		
		if(!getPasswordEncoder().matches((CharSequence) passwordAuthenticationToken.getCredentials(), userInfo.getPassword())){
			throw new BadCredentialsException("密码错误。");
		}
		
		BasicPasswordAuthenticationToken authenticationToken = new BasicPasswordAuthenticationToken();
		List<GrantedAuthority> authorities = userInfo.getAuthorities().stream().map(str -> new SimpleGrantedAuthority(str)).collect(Collectors.toList());
		userInfo.setPassword(null);
		authenticationToken.setAuthorities(authorities);
		authenticationToken.setPrincipal(passwordAuthenticationToken.getPrincipal());
		authenticationToken.setDetails(userInfo);
		authenticationToken.setAuthenticated(true);
		
		
		return authenticationToken;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(BasicPasswordAuthenticationToken.class);
	}
}
