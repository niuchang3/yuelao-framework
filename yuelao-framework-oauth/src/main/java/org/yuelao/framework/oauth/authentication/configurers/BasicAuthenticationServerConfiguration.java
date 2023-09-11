package org.yuelao.framework.oauth.authentication.configurers;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.yuelao.framework.oauth.authentication.converter.BasicPasswordAuthenticationConverter;
import org.yuelao.framework.oauth.authentication.filter.AbstractAuthenticationFilter;
import org.yuelao.framework.oauth.authentication.filter.BasicPasswordAuthenticationFilter;
import org.yuelao.framework.starter.security.core.handler.AccessDeniedHandlerImpl;
import org.yuelao.framework.starter.security.core.handler.AuthenticationEntryPointHandler;
import org.yuelao.framework.oauth.authentication.properties.AuthenticationServerProperties;
import org.yuelao.framework.oauth.authentication.provider.AbstractBasicAuthenticationProvider;
import org.yuelao.framework.oauth.authentication.provider.BasicPasswordAuthenticationProvider;
import org.yuelao.framework.oauth.upms.service.OauthUserService;
import org.yuelao.framework.starter.security.core.configurers.AbstractConfiguration;
import org.yuelao.framework.starter.security.core.encoder.TokenEncoder;

import java.util.List;
import java.util.Map;

/**
 * 基础认证服务配置
 */
public class BasicAuthenticationServerConfiguration extends AbstractConfiguration {
	
	
	/**
	 * 过滤器
	 */
	@Getter
	protected Map<Class<? extends AbstractAuthenticationFilter>, AbstractAuthenticationFilter> filters = defaultFilter();
	
	/**
	 * 身份验证提供程序
	 */
	@Getter
	protected List<AbstractBasicAuthenticationProvider> authenticationProviders = defaultAuthenticationProvider();
	
	
	public <T extends AbstractBasicAuthenticationProvider> BasicAuthenticationServerConfiguration addAuthenticationProvider(T authenticationProvider) {
		authenticationProviders.add(authenticationProvider);
		return this;
	}
	
	/**
	 * 添加过滤器
	 *
	 * @param key
	 * @param filter
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public <K extends AbstractAuthenticationFilter, V extends AbstractAuthenticationFilter> BasicAuthenticationServerConfiguration addFilters(Class<K> key, V filter) {
		this.filters.put(key, filter);
		return this;
	}
	
	/**
	 * 为Filter添加 消息转换器
	 *
	 * @param key
	 * @param converter
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public <K extends AbstractAuthenticationFilter, V extends AuthenticationConverter> BasicAuthenticationServerConfiguration addConverter(Class<K> key, V converter) {
		this.filters.get(key).addConverter(converter);
		return this;
	}
	
	/**
	 * 基础认证服务 初始化操作
	 *
	 * @param httpSecurity
	 */
	@Override
	public void init(HttpSecurity httpSecurity) {
		
		try {
			ApplicationContext context = httpSecurity.getSharedObject(ApplicationContext.class);
			HttpMessageConverter httpMessageConverter = context.getBean(MappingJackson2HttpMessageConverter.class);
			httpSecurity
					.exceptionHandling()
					.authenticationEntryPoint(new AuthenticationEntryPointHandler(httpMessageConverter))
					.accessDeniedHandler(new AccessDeniedHandlerImpl(httpMessageConverter));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		ApplicationContext context = httpSecurity.getSharedObject(ApplicationContext.class);
		OauthUserService oauthUserService = context.getBean(OauthUserService.class);
		PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
		StringRedisTemplate redisTemplate = context.getBean(StringRedisTemplate.class);
		
		authenticationProviders.forEach(authenticationProvider -> {
			//将身份认证提供程序添加到Security内
			authenticationProvider.setOauthUserService(oauthUserService);
			//将密码编码器添加到基础认证中
			authenticationProvider.setPasswordEncoder(passwordEncoder);
			
			authenticationProvider.setRedisTemplate(redisTemplate);
			httpSecurity.authenticationProvider(authenticationProvider);
		});
		
		
	}
	
	/**
	 * 基础认证服务 配置操作
	 *
	 * @param httpSecurity
	 */
	@Override
	public void configure(HttpSecurity httpSecurity) {
		AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
		ApplicationContext context = httpSecurity.getSharedObject(ApplicationContext.class);
		TokenEncoder tokenEncoder = context.getBean(TokenEncoder.class);
		
		AuthenticationServerProperties properties = context.getBean(AuthenticationServerProperties.class);
		HttpMessageConverter httpMessageConverter = context.getBean(MappingJackson2HttpMessageConverter.class);
		
		filters.values().forEach(filter -> {
			filter.setAuthenticationServerProperties(properties);
			//为过滤器添加token编码器
			filter.setTokenEncoder(tokenEncoder);
			// 为过滤器添加认证管理器
			filter.addAuthenticationManager(authenticationManager);
			// 将消息转换器添加到Filter中
			filter.setMessageConverter(httpMessageConverter);
			//将基础认证过滤器添加到 FilterSecurityInterceptor 拦截器之前
			httpSecurity.addFilterBefore(filter, FilterSecurityInterceptor.class);
			
		});
	}
	
	
	protected Map<Class<? extends AbstractAuthenticationFilter>, AbstractAuthenticationFilter> defaultFilter() {
		
		Map<Class<? extends AbstractAuthenticationFilter>, AbstractAuthenticationFilter> defaultFilter = Maps.newLinkedHashMap();
		BasicPasswordAuthenticationFilter passwordAuthenticationFilter = new BasicPasswordAuthenticationFilter(new AntPathRequestMatcher("/login", HttpMethod.POST.name()));
		passwordAuthenticationFilter.addConverter(new BasicPasswordAuthenticationConverter());
		defaultFilter.put(BasicPasswordAuthenticationFilter.class, passwordAuthenticationFilter);
		return defaultFilter;
	}
	
	protected List<AbstractBasicAuthenticationProvider> defaultAuthenticationProvider() {
		List<AbstractBasicAuthenticationProvider> authenticationProviders = Lists.newArrayList();
		authenticationProviders.add(new BasicPasswordAuthenticationProvider());
		return authenticationProviders;
	}
	
	
	@Override
	public List<RequestMatcher> getRequestMatcher() {
		List<RequestMatcher> requestMatchers = Lists.newArrayList();
		for (AbstractAuthenticationFilter filter : getFilters().values()) {
			requestMatchers.add(filter.getRequestMatcher());
		}
		return requestMatchers;
	}
	
}
