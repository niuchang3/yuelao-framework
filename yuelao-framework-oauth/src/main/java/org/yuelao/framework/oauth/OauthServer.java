package org.yuelao.framework.oauth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class OauthServer {
	public static void main(String[] args) {
		SpringApplication.run(OauthServer.class);
	}
}