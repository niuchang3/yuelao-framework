package org.yuelao.framework.oauth.authentication.configurers;

import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.yuelao.framework.oauth.authentication.properties.AuthenticationServerProperties;
import org.yuelao.framework.starter.security.core.configurers.DelegateHttpConfigurer;
import org.yuelao.framework.starter.security.resource.configurers.ResourceAuthenticationConfiguration;

public class DefaultBasicAuthenticationServerConfigurer {
	
	
	public static void applyDefaultBasicAuthenticationServer(HttpSecurity httpSecurity) throws Exception {
		DelegateHttpConfigurer delegateHttpConfigurer = new DelegateHttpConfigurer();
		
		ApplicationContext context = httpSecurity.getSharedObject(ApplicationContext.class);
		AuthenticationServerProperties properties = context.getBean(AuthenticationServerProperties.class);
		httpSecurity.formLogin().loginPage(properties.getLoginPage());
		
		//添加基础认证服务
		delegateHttpConfigurer.addConfigurers(BasicAuthenticationServerConfiguration.class, new BasicAuthenticationServerConfiguration());
		delegateHttpConfigurer.addConfigurers(ResourceAuthenticationConfiguration.class, new ResourceAuthenticationConfiguration());
		
		
		OrRequestMatcher authenticationRequest = new OrRequestMatcher(delegateHttpConfigurer.getRequestMatcher());
		
		
		httpSecurity.apply(delegateHttpConfigurer)
				.and()
				.csrf().ignoringRequestMatchers(authenticationRequest)
				.and()
				.requestMatcher(authenticationRequest)
				.authorizeRequests().anyRequest().authenticated();
		
		
	}
}
