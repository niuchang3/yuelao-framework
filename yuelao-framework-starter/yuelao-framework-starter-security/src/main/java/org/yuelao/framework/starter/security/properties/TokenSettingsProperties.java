package org.yuelao.framework.starter.security.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "yuelao.token")
@Data
public class TokenSettingsProperties {
	
	/**
	 * 发行单位
	 */
	private String issuer = "YueLao";
	/**
	 * 访问令牌过期时间，单位为分钟
	 */
	private Integer expire = 30;
	
	/**
	 * 刷新令牌过期时间，单位为分钟
	 */
	private Integer refreshExpire = 60;
	
}
