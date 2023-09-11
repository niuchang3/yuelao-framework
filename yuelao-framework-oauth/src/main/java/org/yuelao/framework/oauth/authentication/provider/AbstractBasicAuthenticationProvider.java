package org.yuelao.framework.oauth.authentication.provider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yuelao.framework.oauth.upms.service.OauthUserService;

public abstract class AbstractBasicAuthenticationProvider implements AuthenticationProvider {
	
	@Getter
	@Setter
	protected OauthUserService oauthUserService;
	
	@Getter
	@Setter
	private PasswordEncoder passwordEncoder;
	
	@Setter
	@Getter
	private StringRedisTemplate redisTemplate;
}
