package org.yuelao.framework.oauth.authentication.provider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.ObjectUtils;
import org.yuelao.framework.oauth.authentication.BasicPasswordAuthenticationToken;
import org.yuelao.framework.oauth.authentication.GrantType;
import org.yuelao.framework.oauth.utils.RedisUtils;
import org.yuelao.framework.starter.security.core.exception.BadCredentialsException;
import org.yuelao.framework.starter.security.core.exception.CaptchaException;
import org.yuelao.framework.starter.security.core.exception.ProhibitedUserException;
import org.yuelao.framework.starter.security.core.exception.UsernameNotFoundException;
import org.yuelao.framework.starter.security.user.UserDetail;

/**
 * Basic 密码认证
 */
public class BasicPasswordAuthenticationProvider extends AbstractBasicAuthenticationProvider {
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		BasicPasswordAuthenticationToken passwordAuthenticationToken = (BasicPasswordAuthenticationToken) authentication;
		String key = RedisUtils.genKey(GrantType.PasswordParams.captcha.name(), passwordAuthenticationToken.getUuid());
		String captcha = (String) getRedisTemplate().opsForValue().get(key);
		
		if(ObjectUtils.isEmpty(captcha)){
			throw new CaptchaException();
		}
		if(!captcha.equals(passwordAuthenticationToken.getCaptcha())){
			throw new CaptchaException();
		}
		
		UserDetail userDetail = getOauthUserService().loadUserByAccount(passwordAuthenticationToken.getName());
		if (ObjectUtils.isEmpty(userDetail)) {
			throw new UsernameNotFoundException();
		}
		
		if (!getPasswordEncoder().matches((CharSequence) passwordAuthenticationToken.getCredentials(), userDetail.getPassword())) {
			throw new BadCredentialsException();
		}
		if (!userDetail.isEnabled()) {
			throw new ProhibitedUserException();
		}
		
		BasicPasswordAuthenticationToken authenticationToken = new BasicPasswordAuthenticationToken();
		userDetail.setPassword(null);
		authenticationToken.setAuthorities(userDetail.getAuthorities());
		authenticationToken.setPrincipal(passwordAuthenticationToken.getPrincipal());
		authenticationToken.setDetails(userDetail);
		authenticationToken.setAuthenticated(true);
		
		
		return authenticationToken;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(BasicPasswordAuthenticationToken.class);
	}
}
