package org.yuelao.framework.starter.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BearerTokenAuthenticationFilter extends OncePerRequestFilter {
	
	
	/**
	 * 认证转换器，对携带token的请求尝试进行转换
	 */
	private AuthenticationConverter authenticationConverter;
	
	
	public BearerTokenAuthenticationFilter addAuthenticationConverter(AuthenticationConverter authenticationConverter) {
		this.authenticationConverter = authenticationConverter;
		return this;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Authentication convert = null;
		try {
			convert = authenticationConverter.convert(request);
		} catch (Exception e) {
			//TODO token过期
			
			throw new RuntimeException(e);
		}
		if (ObjectUtils.isEmpty(convert)) {
			filterChain.doFilter(request, response);
			return;
		}
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(convert);
		SecurityContextHolder.setContext(context);
		filterChain.doFilter(request, response);
	}
}
