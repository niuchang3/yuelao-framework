package org.yuelao.framework.starter.security.resource.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.yuelao.framework.starter.security.core.exception.TokenFormatException;
import org.yuelao.framework.starter.security.core.token.AbstractBasicAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BearerTokenAuthenticationFilter extends AbstractBearerTokenAuthenticationFilter {
	
	/**
	 * Token正则表达式
	 */
	private static final Pattern bearerTokenPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$",
			Pattern.CASE_INSENSITIVE);
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (!StringUtils.startsWithIgnoreCase(authorization, "bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		// 对authorization 信息进行正则匹配获取Token信息
		Matcher matcher = bearerTokenPattern.matcher(authorization);
		if (!matcher.matches()) {
			throw new TokenFormatException();
		}
		String token = matcher.group("token");
		AbstractBasicAuthenticationToken authentication = getTokenEncoder().decode(token);
		//未防止token被劫持，此处需要对派发的token时的ip和实际使用的ip进行校验
		
		
		//TODO 正常情况下此处应该还需要对用户信息进行校验，比如对账号停用，租户停用等情况。
		SecurityContextHolder.clearContext();
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
		filterChain.doFilter(request, response);
		
	}
}
