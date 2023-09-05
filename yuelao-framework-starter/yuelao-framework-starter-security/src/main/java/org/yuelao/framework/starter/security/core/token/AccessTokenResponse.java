package org.yuelao.framework.starter.security.core.token;

import lombok.Data;

import java.util.Date;

@Data
public class AccessTokenResponse {
	
	private Date expires;
	
	private Date refreshExpires;
	
	private String tokenType;
	
	private String accessToken;
	
	private String refreshToken;
	
	
}
