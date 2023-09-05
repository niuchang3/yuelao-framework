package org.yuelao.framework.oauth.authentication.provider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yuelao.framework.oauth.upms.service.UserService;

public abstract class AbstractBasicAuthenticationProvider implements AuthenticationProvider {
	
	@Getter
	@Setter
	protected UserService userService;
	
	@Getter
	@Setter
	private PasswordEncoder passwordEncoder;
}
