package org.yuelao.framework.oauth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RestController;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties
@SpringBootApplication
public class OauthServer {
	public static void main(String[] args) {
		SpringApplication.run(OauthServer.class);
	}
}