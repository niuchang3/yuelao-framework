package org.yuelao.framework.starter.security.token;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OAuth2Token {
	
	private String tokenType;
	
	private String accessToken;
	
	private Long expires;
	
	private String refreshToken;
	
	private Long refreshExpires;
}
