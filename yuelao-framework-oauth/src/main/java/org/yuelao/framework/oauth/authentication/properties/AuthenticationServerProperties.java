package org.yuelao.framework.oauth.authentication.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@ConfigurationProperties(prefix = "yuelao.authentication")
public class AuthenticationServerProperties {
	/**
	 * 重定向的登录页
	 */
	private String loginPage;
	
	@NestedConfigurationProperty
	private TokenSettings tokenSettings = new TokenSettings();
	
	@Data
	public class TokenSettings {
		/**
		 * accessToken 过期时间 单位为分钟数
		 */
		private Integer expires;
		/**
		 * refreshToken过期时间，单位为分钟数
		 */
		private Integer refreshExpires;
	}
}
