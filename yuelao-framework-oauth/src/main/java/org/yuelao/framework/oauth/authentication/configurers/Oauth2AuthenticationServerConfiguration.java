package org.yuelao.framework.oauth.authentication.configurers;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.yuelao.framework.oauth.authentication.filter.AbstractAuthenticationFilter;
import org.yuelao.framework.oauth.authentication.provider.AbstractBasicAuthenticationProvider;

import java.util.List;
import java.util.Map;

public class Oauth2AuthenticationServerConfiguration extends BasicAuthenticationServerConfiguration {
	
	
	@Override
	public void init(HttpSecurity httpSecurity) {
		super.init(httpSecurity);
	}
	
	@Override
	public void configure(HttpSecurity httpSecurity) {
		super.configure(httpSecurity);
	}
	
	@Override
	protected List<AbstractBasicAuthenticationProvider> defaultAuthenticationProvider() {
		return super.defaultAuthenticationProvider();
	}
	
	
	@Override
	protected Map<Class<? extends AbstractAuthenticationFilter>, AbstractAuthenticationFilter> defaultFilter() {
		return super.defaultFilter();
	}
}
