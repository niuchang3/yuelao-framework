package org.yuelao.framework.oauth.authentication.configuration;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.yuelao.framework.oauth.authentication.converter.BasicPasswordAuthenticationConverter;
import org.yuelao.framework.oauth.authentication.converter.BasicPhoneAuthenticationConverter;
import org.yuelao.framework.starter.security.converter.DelegatingAuthenticationConverter;
import org.yuelao.framework.oauth.authentication.filter.BasicAuthenticationEndpointFilter;
import org.yuelao.framework.oauth.authentication.provider.BasicAuthenticationProvider;
import org.yuelao.framework.oauth.authentication.provider.BasicPasswordAuthenticationProvider;
import org.yuelao.framework.oauth.authentication.provider.BasicPhoneAuthenticationProvider;
import org.yuelao.framework.oauth.authentication.service.UserService;
import org.yuelao.framework.starter.security.converter.AbstractAuthenticationConverter;
import org.yuelao.framework.starter.security.token.TokenEncoder;

import java.util.List;

public class BasicAuthenticationConfiguration extends AbstractHttpConfigurer<BasicAuthenticationConfiguration, HttpSecurity> {
	
	/**
	 * 认证服务匹配的接口
	 */
	@Getter
	@Setter
	private RequestMatcher requestMatcher = new AntPathRequestMatcher("/login");
	
	/**
	 * 认证请求转换器
	 */
	private List<AbstractAuthenticationConverter> authenticationConverters = defaultAuthenticationConverters();
	/**
	 * 身份验证提供程序
	 */
	private List<AuthenticationProvider> authenticationProviders = defaultAuthenticationProviders();
	
	/**
	 * 默认的认证请求转换器
	 *
	 * @return
	 */
	private List<AbstractAuthenticationConverter> defaultAuthenticationConverters() {
		List<AbstractAuthenticationConverter> converters = Lists.newArrayList();
		converters.add(new BasicPasswordAuthenticationConverter());
		converters.add(new BasicPhoneAuthenticationConverter());
		return converters;
	}
	
	/**
	 * 默认的身份认证端点
	 *
	 * @return
	 */
	private List<AuthenticationProvider> defaultAuthenticationProviders() {
		List<AuthenticationProvider> providers = Lists.newArrayList();
		providers.add(new BasicPasswordAuthenticationProvider());
		providers.add(new BasicPhoneAuthenticationProvider());
		return providers;
	}
	
	
	@Override
	public void init(HttpSecurity httpSecurity) throws Exception {
		authenticationProviders.forEach(provider -> {
			if (provider instanceof BasicAuthenticationProvider) {
				BasicAuthenticationProvider basicAuthenticationProvider = (BasicAuthenticationProvider) provider;
				ApplicationContext context = getContext(httpSecurity);
				UserService userDetailsService = context.getBean(UserService.class);
				basicAuthenticationProvider.setUserService(userDetailsService);
			}
			httpSecurity.authenticationProvider(provider);
		});
	}
	
	@Override
	public void configure(HttpSecurity httpSecurity) {
		ApplicationContext context = getContext(httpSecurity);
		BasicAuthenticationEndpointFilter filter = new BasicAuthenticationEndpointFilter();
		filter.addAuthenticationManager(httpSecurity.getSharedObject(AuthenticationManager.class))
				.addAuthenticationRequestMatcher(requestMatcher)
				.addAuthenticationConverter(new DelegatingAuthenticationConverter(authenticationConverters))
				.addApplicationContext(context)
				.addHttpMessageConverter(context.getBean(MappingJackson2HttpMessageConverter.class))
				.addTokenEncoder(context.getBean(TokenEncoder.class));
		httpSecurity.addFilterBefore(filter, FilterSecurityInterceptor.class);
	}
	
	/**
	 * 获取ApplicationContext
	 *
	 * @param httpSecurity
	 * @return
	 */
	protected ApplicationContext getContext(HttpSecurity httpSecurity) {
		return httpSecurity.getSharedObject(ApplicationContext.class);
	}
	
	
	/**
	 * 添加身份认证转换器
	 *
	 * @param authenticationConverter
	 * @return
	 */
	public BasicAuthenticationConfiguration addAuthenticationConverter(AbstractAuthenticationConverter authenticationConverter) {
		authenticationConverters.add(authenticationConverter);
		return this;
	}
	
	/**
	 * 添加身份认证提供程序
	 *
	 * @param provider
	 * @return
	 */
	public BasicAuthenticationConfiguration addAuthenticationProvider(AuthenticationProvider provider) {
		authenticationProviders.add(provider);
		return this;
	}
}
