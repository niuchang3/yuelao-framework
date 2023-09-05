package org.yuelao.framework.starter.security.resource.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yuelao.framework.starter.security.core.token.AbstractBasicAuthenticationToken;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BearerTokenAuthenticationToken extends AbstractBasicAuthenticationToken {
	
	private String tokenType;
}
