package org.yuelao.framework.oauth.authentication.token.extend;

import cn.hutool.crypto.PemUtil;
import com.google.common.collect.Lists;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class JwtTokenGenerator {
	
	
	public static void main(String[] args) {
		
		Calendar signTime = Calendar.getInstance();
		Date signTimeTime = signTime.getTime();
		signTime.add(Calendar.MINUTE, 10);
		Date expireTime = signTime.getTime();
		
		
		Base64StringKeyGenerator generator = new Base64StringKeyGenerator();
		JWTClaimsSet build = new JWTClaimsSet.Builder()
				.jwtID(generator.generateKey())
				.issuer("http://www.test.com")
				.subject(generator.generateKey())
				.expirationTime(expireTime)
				.notBeforeTime(signTimeTime)
				.claim("scope", Lists.newArrayList("read", "write"))
				.claim("userInfo", "userInfo")
				.build();
		
		
		System.out.println("--------jwt 明文-------------");
		System.out.println(build.toString());
		System.out.println();
		System.out.println();
		System.out.println();
		
		String privateKeyPath = System.getProperty("user.dir") + File.separator + "conf" + File.separator + "rsaPrivateKey.pem";
		try (InputStream inputStream = Files.newInputStream(Paths.get(privateKeyPath))) {
			PrivateKey privateKey = PemUtil.readPemPrivateKey(inputStream);
			JWSSigner signer = new RSASSASigner(privateKey);
			JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).type(JOSEObjectType.JWT).build();
			SignedJWT signedJWT = new SignedJWT(header, build);
			signedJWT.sign(signer);
			String serialize = signedJWT.serialize();
			
			
			
			System.out.println("--------jwt 密文-------------");
			System.out.println(serialize);
			System.out.println();
			System.out.println();
			System.out.println();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (JOSEException e) {
			throw new RuntimeException(e);
		}
		
		
		String rsaPublicKeyPath = System.getProperty("user.dir") + File.separator + "conf" + File.separator + "rsaPublicKey.pem";
		try (InputStream inputStream = Files.newInputStream(Paths.get(rsaPublicKeyPath))){
			SignedJWT parse = SignedJWT.parse("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJwc3ZvcWNzUitvOUNVZmZVYW5WLzZXRTNTRDVLTEI3UjA4dXgzRVV2K0NnPSIsInVzZXJJbmZvIjoidXNlckluZm8iLCJuYmYiOjE2OTMyMTIxMjgsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJpc3MiOiJodHRwOi8vd3d3LnRlc3QuY29tIiwiZXhwIjoxNjkzMjEyNzI4LCJqdGkiOiJjZzdvVDNZWlBxRUVhLytkbWYza2hLeXRQZFBWbFdPV0RaRjNmTzM3ZW0wPSJ9.DDLz0QjbxM7LcfXjg1Z1xuFpAWWeXgarqFkZiJ9V8IGMVY5_Hd3KVUsPrFdSA5lvwVcfxrZP9FVrI2uDWf_Mw0ixaiHJxyrN9MNbmRsdmNwdmEvC96hg1-m-olPkHp0eGmeEvvGATatqpKtZzZZ8fvuyFT7IGoeIlKhkfGeuEW7jn_TQ25WH8hZh4dMxswbKT6F4eNj1j3DD66CWD1hUT0fxyxT5iH8JRPlkHYgmwHs2KBcJBie0r0Fv6DXzsqB2fRf5ux4oj87988oUYtpLJrmMC0tz_vPT8dusH_V_3h9RUxwgN-0rhBTmfUTddS12T5EQkgv8EAofnmxE3mCWPg");
			RSAPublicKey publicKey = (RSAPublicKey) PemUtil.readPemPublicKey(inputStream);
			RSASSAVerifier rsassaVerifier = new RSASSAVerifier(publicKey);
			if(parse.verify(rsassaVerifier)){
				System.out.println("----------jwt 解密---------------");
				System.out.println(parse.toString());
			}
			
		} catch (ParseException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (JOSEException e) {
			throw new RuntimeException(e);
		}
	}
}
