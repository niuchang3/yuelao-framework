package org.yuelao.framework.starter.security.resource.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.yuelao.framework.starter.security.core.encoder.TokenEncoder;

public abstract class AbstractBearerTokenAuthenticationFilter extends OncePerRequestFilter {
	
	@Getter
	@Setter
	private TokenEncoder tokenEncoder;
}
