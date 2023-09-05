package org.yuelao.framework.oauth.authentication.filter;

import org.springframework.security.web.util.matcher.RequestMatcher;

public class BasicPasswordAuthenticationFilter extends AbstractAuthenticationFilter {
	
	
	public BasicPasswordAuthenticationFilter(RequestMatcher requestMatcher) {
		super(requestMatcher);
	}
}
