package org.yuelao.framework.oauth.authentication;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.yuelao.framework.oauth.authentication.exception.CustomAccessDeniedHandler;
import org.yuelao.framework.oauth.authentication.exception.CustomAuthenticationEntryPoint;


public class AuthorizationServerConfiguration {
	
	public static void applyDefaultBasicSecurity(HttpSecurity http) throws Exception {
		
		ExceptionHandlingConfigurer configurer = http.getConfigurer(ExceptionHandlingConfigurer.class);
		
		
		// 从HttpSecurity中移除 默认的登录页配置
		http.removeConfigurer(DefaultLoginPageConfigurer.class);
		// 移除默认的退出登录
		http.removeConfigurer(LogoutConfigurer.class);
		// 定义异常处理
		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
		
		
		//移除异常处理配置
//		http.removeConfigurer(ExceptionHandlingConfigurer.class);
		// 添加基础认证Configuration
		DelegatingAuthenticationConfiguration configuration = new DelegatingAuthenticationConfiguration();
		http.apply(configuration)
				.and()
				// 关闭csrf校验
				.csrf().ignoringRequestMatchers(configuration.getEndpointsMatcher())
				.and()
				// 匹配端点
				.requestMatcher(configuration.getEndpointsMatcher());
	}
}
