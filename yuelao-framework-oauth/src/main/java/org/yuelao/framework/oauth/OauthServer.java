package org.yuelao.framework.oauth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@ConfigurationPropertiesScan("org.yuelao.framework.oauth.authentication.properties")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class OauthServer {
	public static void main(String[] args) {
		SpringApplication.run(OauthServer.class);
	}
}