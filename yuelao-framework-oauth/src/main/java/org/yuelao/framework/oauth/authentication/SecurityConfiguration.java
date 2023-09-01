package org.yuelao.framework.oauth.authentication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.yuelao.framework.oauth.authentication.configuration.DefaultAuthenticationServerConfig;
import org.yuelao.framework.oauth.authentication.service.UserService;
import org.yuelao.framework.oauth.authentication.service.impl.MemoryUserServiceImpl;

@Configuration
public class SecurityConfiguration {
	
	
	@ConditionalOnMissingBean
	@Bean
	public UserService userDetailsService() {
		return new MemoryUserServiceImpl();
	}
	
	@Bean
	public SecurityFilterChain defaultAuthenticationServer(HttpSecurity http) throws Exception {
		DefaultAuthenticationServerConfig.applyDefaultBasicSecurity(http);
		DefaultSecurityFilterChain build = http.build();
		return build;
	}
	
}
