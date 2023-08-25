package org.yuelao.framework.oauth.authentication;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.yuelao.framework.oauth.authentication.configurer.AuthenticationConfigurer;

import java.util.List;
import java.util.Map;

/**
 * 身份认证配置器-委派模式
 */
public class DelegatingAuthenticationConfiguration extends AbstractHttpConfigurer<DelegatingAuthenticationConfiguration, HttpSecurity> {
	
	/**
	 * 身份认证配置器
	 */
	private final Map<Class<? extends AbstractAuthenticationConfigurer>, AbstractAuthenticationConfigurer> configurers = defaultConfig();
	
	/**
	 * 匹配的端点
	 */
	private RequestMatcher endpointsMatcher;
	
	/**
	 * 创建默认的身份认证配置器
	 *
	 * @return
	 */
	protected Map<Class<? extends AbstractAuthenticationConfigurer>, AbstractAuthenticationConfigurer> defaultConfig() {
		Map<Class<? extends AbstractAuthenticationConfigurer>, AbstractAuthenticationConfigurer> configurers = Maps.newLinkedHashMap();
		configurers.put(AbstractAuthenticationConfigurer.class, new AuthenticationConfigurer());
		return configurers;
	}
	
	/**
	 * 增加身份认证配置器
	 *
	 * @param type
	 * @param configurer
	 * @return
	 */
	public DelegatingAuthenticationConfiguration addConfigurer(Class<? extends AbstractAuthenticationConfigurer> type, AbstractAuthenticationConfigurer configurer) {
		this.configurers.put(type, configurer);
		return this;
	}
	
	/**
	 * 调用初始 AbstractAuthenticationConfigurer.init 方法进行初始化
	 *
	 * @param builder
	 * @throws Exception
	 */
	@Override
	public void init(HttpSecurity builder) throws Exception {
		List<RequestMatcher> requestMatchers = Lists.newArrayList();
		configurers.values().forEach(config -> {
			config.init(builder);
			requestMatchers.add(config.getRequestMatcher());
		});
		endpointsMatcher = new OrRequestMatcher(requestMatchers);
	}
	
	/**
	 * 调用初始 AbstractAuthenticationConfigurer.configure 方法进行配置
	 *
	 * @param builder
	 * @throws Exception
	 */
	@Override
	public void configure(HttpSecurity builder) throws Exception {
		configurers.values().forEach(config -> config.configure(builder));
	}
	
	/**
	 * request 请求匹配
	 *
	 * @return
	 */
	public RequestMatcher getEndpointsMatcher() {
		return (request) -> this.endpointsMatcher.matches(request);
	}
}
