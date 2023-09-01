package org.yuelao.framework.starter.security.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import org.yuelao.framework.starter.security.token.TokenEncoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BearerTokenAuthenticationFilter extends OncePerRequestFilter {
	
	private TokenEncoder tokenEncoder;
	
	
	public BearerTokenAuthenticationFilter addTokenEncoder(TokenEncoder tokenEncoder) {
		
		this.tokenEncoder = tokenEncoder;
		return this;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		request.getHeader("");
	}
}
