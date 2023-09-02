package org.yuelao.framework.starter.security.converter.extend;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.yuelao.framework.starter.security.converter.AbstractAuthenticationConverter;
import org.yuelao.framework.starter.security.token.TokenEncoder;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BearerTokenAuthenticationConverter extends AbstractAuthenticationConverter {
	
	private static final Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$", Pattern.CASE_INSENSITIVE);
	
	
	private final TokenEncoder tokenEncoder;
	
	
	public BearerTokenAuthenticationConverter(TokenEncoder tokenEncoder) {
		this.tokenEncoder = tokenEncoder;
	}
	
	@Override
	protected boolean supports(HttpServletRequest request) {
		String authorization = extractRequestHeader(request, HttpHeaders.AUTHORIZATION);
		return StringUtils.startsWithIgnoreCase(authorization, "bearer");
	}
	
	
	@Override
	protected Authentication doConvert(HttpServletRequest request) {
		String authorization = extractRequestHeader(request, HttpHeaders.AUTHORIZATION);
		Matcher matcher = authorizationPattern.matcher(authorization);
		if (!matcher.matches()) {
			return null;
		}
		String token = matcher.group("token");
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		try {
			Authentication decode = tokenEncoder.decode(token);
			//TODO 此处未做token过期校验
			
			return decode;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
