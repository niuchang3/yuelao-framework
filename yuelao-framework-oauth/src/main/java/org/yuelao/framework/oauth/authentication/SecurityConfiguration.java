package org.yuelao.framework.oauth.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.yuelao.framework.oauth.authentication.configurers.DefaultBasicAuthenticationServerConfigurer;

@Configuration
public class SecurityConfiguration {
	
	
	@Bean
	public SecurityFilterChain defaultAuthenticationServer(HttpSecurity http) throws Exception {
//		DefaultAuthenticationServerConfig.applyDefaultBasicSecurity(http);
		
		DefaultBasicAuthenticationServerConfigurer.applyDefaultBasicAuthenticationServer(http);
		DefaultSecurityFilterChain build = http.build();
		return build;
	}
	
}
