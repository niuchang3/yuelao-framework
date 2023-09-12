package org.yuelao.upms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.yuelao.framework.oauth.OauthServerConfigurer;
import org.yuelao.framework.oauth.authentication.configurers.DefaultBasicAuthenticationServerConfigurer;


@SpringBootApplication
public class UpmsServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UpmsServiceApplication.class);
	}
	
	
	@Bean
	public SecurityFilterChain defaultAuthenticationServer(HttpSecurity http) throws Exception {
//		DefaultAuthenticationServerConfig.applyDefaultBasicSecurity(http);
		
		DefaultBasicAuthenticationServerConfigurer.applyDefaultBasicAuthenticationServer(http);
		DefaultSecurityFilterChain build = http.build();
		
		
		return build;
	}
}
