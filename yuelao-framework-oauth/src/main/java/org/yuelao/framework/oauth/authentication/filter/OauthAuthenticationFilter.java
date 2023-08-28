package org.yuelao.framework.oauth.authentication.filter;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.yuelao.framework.oauth.authentication.token.TokenGenerator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class OauthAuthenticationFilter extends OncePerRequestFilter {
	
	
	private AuthenticationManager authenticationManager;
	
	private RequestMatcher authenticationRequestMatcher;
	
	private AuthenticationConverter converter = null;
	
	private ApplicationContext context = null;
	
	
	private HttpMessageConverter<Object> messageConverter;
	
	
	private TokenGenerator tokenGenerator;
	
	
	public OauthAuthenticationFilter addAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		return this;
	}
	
	public OauthAuthenticationFilter addAuthenticationRequestMatcher(RequestMatcher authenticationRequestMatcher) {
		this.authenticationRequestMatcher = authenticationRequestMatcher;
		return this;
	}
	
	public OauthAuthenticationFilter addAuthenticationConverter(AuthenticationConverter authenticationConverter) {
		converter = authenticationConverter;
		return this;
	}
	
	public OauthAuthenticationFilter addApplicationContext(ApplicationContext context) {
		this.context = context;
		return this;
	}
	
	public OauthAuthenticationFilter addHttpMessageConverter(HttpMessageConverter messageConverter) {
		this.messageConverter = messageConverter;
		return this;
	}
	
	public OauthAuthenticationFilter addTokenGenerator(TokenGenerator tokenGenerator) {
		this.tokenGenerator = tokenGenerator;
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
			Authentication authenticate = authenticationManager.authenticate(authentication);
			if (authenticate.isAuthenticated()) {
				// TODO： 此处扩展认证成功处理
				
				Map<String, String> res = Maps.newHashMap();
				res.put("token", UUID.randomUUID().toString());
				messageConverter.write(res, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
			} else {
				throw new UsernameNotFoundException("账号异常");
			}
		} catch (AuthenticationException e) {
			// TODO： 此处扩展认证失败处理
			throw e;
		}
		
	}
}
