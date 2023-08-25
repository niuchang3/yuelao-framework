package org.yuelao.framework.oauth.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.yuelao.framework.oauth.authentication.AuthorizationServerConfiguration;

@Configuration
public class SecurityConfiguration {
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager();
	}
	
	@Bean
	public SecurityFilterChain defaultAuthenticationServer(HttpSecurity http) throws Exception {
		AuthorizationServerConfiguration.applyDefaultBasicSecurity(http);
		DefaultSecurityFilterChain build = http.build();
		
		return build;
	}
	
	
}
