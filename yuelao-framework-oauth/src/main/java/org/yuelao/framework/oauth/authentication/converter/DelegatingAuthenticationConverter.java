package org.yuelao.framework.oauth.authentication.converter;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 身份认证转换器-委派模式
 */
public class DelegatingAuthenticationConverter implements AuthenticationConverter {
	
	private final List<AbstractAuthenticationConverter> converters;
	
	public DelegatingAuthenticationConverter(List<AbstractAuthenticationConverter> converters) {
		this.converters = converters;
	}
	
	
	@Override
	public Authentication convert(HttpServletRequest request) {
		for (AbstractAuthenticationConverter converter : converters) {
			Authentication convert = converter.convert(request);
			if (!ObjectUtils.isEmpty(convert)) {
				return convert;
			}
		}
		return null;
	}
}
