package org.yuelao.framework.starter.security.core.configurers;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

public abstract class AbstractConfiguration {
	
	public abstract void init(HttpSecurity httpSecurity);
	
	public abstract void configure(HttpSecurity httpSecurity);
	
	
	public abstract List<RequestMatcher> getRequestMatcher();
	
}
