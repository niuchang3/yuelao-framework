package org.yuelao.framework.oauth.authentication.configuration;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.yuelao.framework.oauth.authentication.exception.AccessDeniedHandlerImpl;
import org.yuelao.framework.oauth.authentication.exception.AuthenticationEntryPointImpl;
import org.yuelao.framework.starter.security.configuration.OauthResourceConfiguration;


public class DefaultAuthenticationServerConfig {
	
	
	public static void applyDefaultBasicSecurity(HttpSecurity http) throws Exception {
		// 从HttpSecurity中移除 默认的登录页配置
		http.removeConfigurer(DefaultLoginPageConfigurer.class);
		// 移除默认的退出登录
		http.removeConfigurer(LogoutConfigurer.class);
		// 定义异常处理
		http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl());
		http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl());
		// 添加基础认证Configuration
		BasicAuthenticationConfiguration configuration = new BasicAuthenticationConfiguration();
		OauthResourceConfiguration resourceConfiguration = new OauthResourceConfiguration();
		
		http
				.apply(resourceConfiguration)
				.and()
				.apply(configuration)
				.and()
				// 关闭csrf校验
				.csrf().disable();
//				.and()
		// 匹配端点
//				.requestMatcher(configuration.getRequestMatcher());
		
		http.authorizeRequests().anyRequest().authenticated();
	}
}
