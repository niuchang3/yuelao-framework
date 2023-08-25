package org.yuelao.framework.oauth.authentication;

import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.RequestMatcher;

public abstract class AbstractAuthenticationConfigurer {
	
	/**
	 * 配置初始化操作
	 *
	 * @param httpSecurity
	 */
	public abstract void init(HttpSecurity httpSecurity);
	
	/**
	 * 配置
	 *
	 * @param httpSecurity
	 */
	public abstract void configure(HttpSecurity httpSecurity);
	
	/**
	 * 请求匹配项
	 *
	 * @return
	 */
	public abstract RequestMatcher getRequestMatcher();
	
	protected ApplicationContext getContext(HttpSecurity httpSecurity) {
		return httpSecurity.getSharedObject(ApplicationContext.class);
	}
}
