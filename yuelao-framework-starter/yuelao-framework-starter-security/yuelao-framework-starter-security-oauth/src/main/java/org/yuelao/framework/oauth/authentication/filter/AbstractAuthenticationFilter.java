package org.yuelao.framework.oauth.authentication.filter;

import cn.hutool.extra.servlet.ServletUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.yuelao.common.core.web.ResultModel;
import org.yuelao.framework.oauth.authentication.properties.AuthenticationServerProperties;
import org.yuelao.framework.starter.security.core.converter.DelegatingAuthenticationConverter;
import org.yuelao.framework.starter.security.core.encoder.TokenEncoder;
import org.yuelao.framework.starter.security.core.token.AbstractBasicAuthenticationToken;
import org.yuelao.framework.starter.security.core.token.AccessTokenResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public abstract class AbstractAuthenticationFilter extends OncePerRequestFilter {
	
	@Getter
	@Setter
	public RequestMatcher requestMatcher;
	
	@Getter
	@Setter
	protected TokenEncoder tokenEncoder;
	
	public AbstractAuthenticationFilter(RequestMatcher requestMatcher) {
		this.requestMatcher = requestMatcher;
	}
	
	@Getter
	protected DelegatingAuthenticationConverter delegatingConverter = new DelegatingAuthenticationConverter();
	
	@Getter
	protected AuthenticationManager authenticationManager;
	
	@Getter
	@Setter
	protected AuthenticationServerProperties authenticationServerProperties;
	
	@Getter
	@Setter
	private HttpMessageConverter<Object> messageConverter;
	
	
	public <T extends AuthenticationConverter> AbstractAuthenticationFilter addConverter(T converter) {
		this.delegatingConverter.addConverter(converter);
		return this;
	}
	
	/**
	 * 添加身份验证管理器
	 *
	 * @param authenticationManager
	 * @return
	 */
	public AbstractAuthenticationFilter addAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		return this;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (!requestMatcher.matches(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		Authentication authentication = delegatingConverter.convert(request);
		if (ObjectUtils.isEmpty(authentication)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		
		Authentication authenticate = authenticationManager.authenticate(authentication);
		if (ObjectUtils.isEmpty(authenticate)) {
			return;
		}
		
		// 开始派发token
		AbstractBasicAuthenticationToken abstractBasicAuthenticationToken = (AbstractBasicAuthenticationToken) authenticate;
		
		Calendar accessTokenNow = getNow();
		accessTokenNow.add(Calendar.MINUTE, authenticationServerProperties.getTokenSettings().getExpires());
		abstractBasicAuthenticationToken.setIp(ServletUtil.getClientIP(request));
		abstractBasicAuthenticationToken.setTokenType("accessToken");
		abstractBasicAuthenticationToken.setExpires(accessTokenNow.getTime());
		String accessToken = getTokenEncoder().encode(abstractBasicAuthenticationToken);
		
		Calendar refreshTokenNow = getNow();
		refreshTokenNow.add(Calendar.MINUTE, authenticationServerProperties.getTokenSettings().getRefreshExpires());
		abstractBasicAuthenticationToken.setTokenType("refreshToken");
		abstractBasicAuthenticationToken.setExpires(refreshTokenNow.getTime());
		
		
		String refreshToken = getTokenEncoder().encode(abstractBasicAuthenticationToken);
		AccessTokenResponse tokenResponse = new AccessTokenResponse();
		tokenResponse.setExpires(accessTokenNow.getTime());
		tokenResponse.setRefreshExpires(refreshTokenNow.getTime());
		tokenResponse.setTokenType("Bearer");
		tokenResponse.setAccessToken(accessToken);
		tokenResponse.setRefreshToken(refreshToken);
		
		ResultModel<AccessTokenResponse> resultModel = ResultModel.success(tokenResponse);
		ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
		messageConverter.write(resultModel, MediaType.APPLICATION_JSON, httpResponse);
		
	}
	
	
	private Calendar getNow() {
		return Calendar.getInstance();
	}
	
}
