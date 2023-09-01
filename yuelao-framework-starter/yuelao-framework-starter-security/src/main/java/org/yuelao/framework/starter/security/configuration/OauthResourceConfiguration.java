package org.yuelao.framework.starter.security.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class OauthResourceConfiguration extends AbstractHttpConfigurer<OauthResourceConfiguration, HttpSecurity> {
	
	@Override
	public void init(HttpSecurity builder) throws Exception {
		super.init(builder);
	}
	
	
	@Override
	public void configure(HttpSecurity builder) throws Exception {
		super.configure(builder);
	}
}
