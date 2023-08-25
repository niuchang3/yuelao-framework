package org.yuelao.framework.oauth.authentication.configurer;

import com.google.common.collect.Lists;
import org.springframework.context.ApplicationContext;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.yuelao.framework.oauth.authentication.AbstractAuthenticationConfigurer;
import org.yuelao.framework.oauth.authentication.converter.AbstractAuthenticationConverter;
import org.yuelao.framework.oauth.authentication.converter.DelegatingAuthenticationConverter;
import org.yuelao.framework.oauth.authentication.converter.UserNamePasswordAuthenticationConverter;
import org.yuelao.framework.oauth.authentication.token.UserNamePasswordAuthenticationToken;
import org.yuelao.framework.oauth.authentication.filter.OauthAuthenticationFilter;
import org.yuelao.framework.oauth.authentication.provider.UserNamePasswordAuthenticationProvider;

import java.util.List;

public class AuthenticationConfigurer extends AbstractAuthenticationConfigurer {
	
	
	private RequestMatcher requestMatcher = new AntPathRequestMatcher("/oauth2/token");
	
	/**
	 * 认证请求转换器
	 */
	private List<AbstractAuthenticationConverter> authenticationConverters = defaultAuthenticationConverters();
	/**
	 * 身份验证提供程序
	 */
	private List<AuthenticationProvider> authenticationProviders = defaultAuthenticationProviders();
	
	
	@Override
	public void init(HttpSecurity httpSecurity) {
		authenticationProviders.forEach(provider -> {
			if (provider.supports(UserNamePasswordAuthenticationToken.class)) {
				ApplicationContext sharedObject = httpSecurity.getSharedObject(ApplicationContext.class);
				UserDetailsService userDetailsService = sharedObject.getBean(UserDetailsService.class);
				UserNamePasswordAuthenticationProvider userNamePasswordAuthenticationProvider = (UserNamePasswordAuthenticationProvider) provider;
				userNamePasswordAuthenticationProvider.setUserDetailsService(userDetailsService);
			}
			httpSecurity.authenticationProvider(provider);
		});
		
		
	}
	
	@Override
	public void configure(HttpSecurity httpSecurity) {
		ApplicationContext sharedObject = httpSecurity.getSharedObject(ApplicationContext.class);
		HttpMessageConverter converter = sharedObject.getBean(MappingJackson2HttpMessageConverter.class);
		
		
		OauthAuthenticationFilter filter = new OauthAuthenticationFilter();
		filter.addAuthenticationManager(httpSecurity.getSharedObject(AuthenticationManager.class))
				.addAuthenticationRequestMatcher(requestMatcher)
				.addAuthenticationConverter(new DelegatingAuthenticationConverter(authenticationConverters))
				.addApplicationContext(getContext(httpSecurity))
				.addHttpMessageConverter(converter);
		
		
		httpSecurity.addFilterAfter(filter, FilterSecurityInterceptor.class);
	}
	
	
	@Override
	public RequestMatcher getRequestMatcher() {
		return requestMatcher;
	}
	
	
	private List<AbstractAuthenticationConverter> defaultAuthenticationConverters() {
		List<AbstractAuthenticationConverter> converters = Lists.newArrayList();
		converters.add(new UserNamePasswordAuthenticationConverter());
		return converters;
	}
	
	private List<AuthenticationProvider> defaultAuthenticationProviders() {
		List<AuthenticationProvider> providers = Lists.newArrayList();
		providers.add(new UserNamePasswordAuthenticationProvider());
		return providers;
	}
}
