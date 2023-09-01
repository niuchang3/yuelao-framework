package org.yuelao.framework.oauth.authentication.provider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.yuelao.framework.oauth.authentication.service.UserService;

/**
 * 基础性认证空实现
 */
public abstract class BasicAuthenticationProvider implements AuthenticationProvider {
	
	
	@Setter
	@Getter
	protected UserService userService;
	
}
