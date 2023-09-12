package org.yuelao.framework.oauth.authentication;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yuelao.framework.starter.security.core.token.AbstractBasicAuthenticationToken;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasicPasswordAuthenticationToken extends AbstractBasicAuthenticationToken {
	
	private String uuid;
	
	private String captcha;
}
