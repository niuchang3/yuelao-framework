package org.yuelao.framework.starter.security.resource.configurers;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.yuelao.framework.starter.security.core.configurers.DelegateHttpConfigurer;

public class DefaultResourceServerConfigurer {
	
	public static void applyDefaultResourceServer(HttpSecurity httpSecurity) throws Exception {
		DelegateHttpConfigurer delegateHttpConfigurer = new DelegateHttpConfigurer();
		delegateHttpConfigurer.addConfigurers(ResourceAuthenticationConfiguration.class, new ResourceAuthenticationConfiguration());
		OrRequestMatcher authenticationRequest = new OrRequestMatcher(delegateHttpConfigurer.getRequestMatcher());
		
		httpSecurity.apply(delegateHttpConfigurer)
				.and()
				.csrf().ignoringRequestMatchers(authenticationRequest)
				.and()
				.requestMatcher(authenticationRequest)
				.authorizeRequests()
				.anyRequest().authenticated();
	}
}
