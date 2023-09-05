package org.yuelao.framework.starter.security;


import cn.hutool.crypto.PemUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yuelao.framework.starter.security.core.encoder.JwtTokenEncoder;
import org.yuelao.framework.starter.security.core.encoder.TokenEncoder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@ComponentScan("org.yuelao.framework.starter.security.**")
public class SecurityResourceConfig {
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	/**
	 * 读取私钥证书
	 * 证书位置后续可以写到具体的配置文件内
	 *
	 * @return
	 */
	@Bean
	public PrivateKey privateKey() {
		// 此处最终需要替换到yaml文件内进行配置
		String privateKeyPath = System.getProperty("user.dir") + File.separator + "conf" + File.separator + "rsaPrivateKey.pem";
		try (InputStream inputStream = Files.newInputStream(Paths.get(privateKeyPath))) {
			return PemUtil.readPemPrivateKey(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 读取公钥证书
	 * 证书位置后续可以写到具体的配置文件内
	 *
	 * @return
	 */
	@Bean
	public RSAPublicKey publicKey() {
		String rsaPublicKeyPath = System.getProperty("user.dir") + File.separator + "conf" + File.separator + "rsaPublicKey.pem";
		try (InputStream inputStream = Files.newInputStream(Paths.get(rsaPublicKeyPath))) {
			return (RSAPublicKey) PemUtil.readPemPublicKey(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Token编码器
	 *
	 * @param privateKey
	 * @param publicKey
	 * @return
	 */
	@ConditionalOnMissingBean(TokenEncoder.class)
	@Bean
	public TokenEncoder tokenEncoder(PrivateKey privateKey, RSAPublicKey publicKey) {
		return new JwtTokenEncoder(privateKey, publicKey);
	}
	
}
