package org.yuelao.framework.starter.security.configuration;

import com.google.common.collect.Lists;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.yuelao.framework.starter.security.converter.AbstractAuthenticationConverter;
import org.yuelao.framework.starter.security.converter.DelegatingAuthenticationConverter;
import org.yuelao.framework.starter.security.converter.extend.BearerTokenAuthenticationConverter;
import org.yuelao.framework.starter.security.filter.BearerTokenAuthenticationFilter;
import org.yuelao.framework.starter.security.token.TokenEncoder;

import java.util.List;

public class OauthResourceConfiguration extends AbstractHttpConfigurer<OauthResourceConfiguration, HttpSecurity> {
	
	@Override
	public void init(HttpSecurity builder) throws Exception {
		builder.setSharedObject(RequestCache.class,new NullRequestCache());
		super.init(builder);
	}
	
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		ApplicationContext context = httpSecurity.getSharedObject(ApplicationContext.class);
		List<AbstractAuthenticationConverter> converters = Lists.newArrayList(new BearerTokenAuthenticationConverter(context.getBean(TokenEncoder.class)));
		DelegatingAuthenticationConverter delegatingAuthenticationConverter = new DelegatingAuthenticationConverter(converters);
		BearerTokenAuthenticationFilter filter = new BearerTokenAuthenticationFilter();
		filter.addAuthenticationConverter(delegatingAuthenticationConverter);
		httpSecurity.addFilterBefore(filter, FilterSecurityInterceptor.class);
	}
}
