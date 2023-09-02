package org.yuelao.framework.oauth.authentication.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.yuelao.common.core.web.ResultModel;
import org.yuelao.framework.starter.security.exception.AccountAuthenticationException;
import org.yuelao.framework.starter.security.token.OAuth2Token;
import org.yuelao.framework.starter.security.token.TokenEncoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基本身份认证过滤器
 */
@Slf4j
public class BasicAuthenticationEndpointFilter extends OncePerRequestFilter {
	
	
	/**
	 * 身份认证管理器
	 */
	private AuthenticationManager authenticationManager;
	
	/**
	 * URL匹配器
	 */
	private RequestMatcher authenticationRequestMatcher;
	/**
	 * 认证请求转换器
	 */
	private AuthenticationConverter converter;
	
	/**
	 * HttpMessage转换器
	 */
	private HttpMessageConverter<Object> messageConverter;
	
	/**
	 * Token编码器
	 */
	private TokenEncoder tokenEncoder;
	
	/**
	 * Application后期要扩展 各种事件使用
	 */
	private ApplicationContext context;
	
	
	public BasicAuthenticationEndpointFilter addAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		return this;
	}
	
	public BasicAuthenticationEndpointFilter addAuthenticationRequestMatcher(RequestMatcher authenticationRequestMatcher) {
		this.authenticationRequestMatcher = authenticationRequestMatcher;
		return this;
	}
	
	public BasicAuthenticationEndpointFilter addAuthenticationConverter(AuthenticationConverter authenticationConverter) {
		converter = authenticationConverter;
		return this;
	}
	
	public BasicAuthenticationEndpointFilter addApplicationContext(ApplicationContext context) {
		this.context = context;
		return this;
	}
	
	public BasicAuthenticationEndpointFilter addHttpMessageConverter(HttpMessageConverter messageConverter) {
		this.messageConverter = messageConverter;
		return this;
	}
	
	public BasicAuthenticationEndpointFilter addTokenEncoder(TokenEncoder tokenEncoder) {
		this.tokenEncoder = tokenEncoder;
		return this;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (!authenticationRequestMatcher.matches(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		Authentication authentication = converter.convert(request);
		if (ObjectUtils.isEmpty(authentication)) {
			filterChain.doFilter(request, response);
			return;
		}
		try {
			Authentication authenticateOutcome = authenticationManager.authenticate(authentication);
			if (!authenticateOutcome.isAuthenticated()) {
				throw new AccountAuthenticationException("认证失败，请确认信息是否正确。");
			}
			// TODO： 此处扩展认证成功处理
			OAuth2Token encode = tokenEncoder.encode(authenticateOutcome);
			messageConverter.write(ResultModel.success(encode), MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
		} catch (AuthenticationException e) {
			// TODO：身份验证异常处理
			throw e;
		}
		
	}
}
