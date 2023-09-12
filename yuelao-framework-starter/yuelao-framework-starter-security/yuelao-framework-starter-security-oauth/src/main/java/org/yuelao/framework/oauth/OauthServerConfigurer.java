package org.yuelao.framework.oauth;


import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.yuelao.framework.oauth.authentication.configurers.DefaultBasicAuthenticationServerConfigurer;

@Configuration
@ComponentScan("org.yuelao.framework.oauth.**")
@ConfigurationPropertiesScan("org.yuelao.framework.oauth.authentication.properties")
public class OauthServerConfigurer {

}