package org.yuelao.framework.starter.security.resource.configurers;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.yuelao.framework.starter.security.core.configurers.AbstractConfiguration;
import org.yuelao.framework.starter.security.core.encoder.TokenEncoder;
import org.yuelao.framework.starter.security.resource.filter.AbstractBearerTokenAuthenticationFilter;
import org.yuelao.framework.starter.security.resource.filter.BearerTokenAuthenticationFilter;

import java.util.List;

public class ResourceAuthenticationConfiguration extends AbstractConfiguration {
	
	@Getter
	protected List<? extends AbstractBearerTokenAuthenticationFilter> filters = defaultFilter();
	
	
	@Override
	public void init(HttpSecurity httpSecurity) {
		try {
			httpSecurity.requestCache().disable();
			httpSecurity.securityContext().securityContextRepository(new NullSecurityContextRepository());
//			httpSecurity.anonymous().disable();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		ApplicationContext context = httpSecurity.getSharedObject(ApplicationContext.class);
		TokenEncoder tokenEncoder = context.getBean(TokenEncoder.class);
		filters.forEach(filter -> {
			filter.setTokenEncoder(tokenEncoder);
			httpSecurity.addFilterBefore(filter, FilterSecurityInterceptor.class);
		});
	}
	
	@Override
	public void configure(HttpSecurity httpSecurity) {
	
	}
	
	@Override
	public List<RequestMatcher> getRequestMatcher() {
		return Lists.newArrayList(new AntPathRequestMatcher("/**"));
	}
	
	private List<? extends AbstractBearerTokenAuthenticationFilter> defaultFilter() {
		List<AbstractBearerTokenAuthenticationFilter> bearerFilter = Lists.newArrayList();
		bearerFilter.add(new BearerTokenAuthenticationFilter());
		return bearerFilter;
	}
}
