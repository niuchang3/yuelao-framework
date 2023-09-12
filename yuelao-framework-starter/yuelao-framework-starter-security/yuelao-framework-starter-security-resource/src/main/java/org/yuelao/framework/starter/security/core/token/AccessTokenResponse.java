package org.yuelao.framework.starter.security.core.token;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AccessTokenResponse {
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	private Date expires;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	private Date refreshExpires;
	
	private String tokenType;
	
	private String accessToken;
	
	private String refreshToken;
	
	
}
