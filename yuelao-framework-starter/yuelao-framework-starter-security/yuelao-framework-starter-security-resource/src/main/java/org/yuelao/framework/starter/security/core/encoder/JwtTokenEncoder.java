package org.yuelao.framework.starter.security.core.encoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.yuelao.framework.starter.security.core.exception.TokenExpiresException;
import org.yuelao.framework.starter.security.core.exception.TokenParseException;
import org.yuelao.framework.starter.security.core.exception.TokenSignException;
import org.yuelao.framework.starter.security.core.token.AbstractBasicAuthenticationToken;
import org.yuelao.framework.starter.security.resource.token.BearerTokenAuthenticationToken;
import org.yuelao.framework.starter.security.user.UserDetail;

import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class JwtTokenEncoder implements TokenEncoder {
	
	
	/**
	 * JWT 编码
	 */
	@Getter
	private JWSSigner jwsSigner;
	/**
	 * JWT 解码
	 */
	@Getter
	private RSASSAVerifier verifier;
	
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	
	public JwtTokenEncoder(PrivateKey privateKey, RSAPublicKey publicKey) {
		this.jwsSigner = new RSASSASigner(privateKey);
		this.verifier = new RSASSAVerifier(publicKey);
	}
	
	/**
	 * 对 Authentication 进行编码
	 *
	 * @param
	 * @return
	 */
	@Override
	public String encode(AbstractBasicAuthenticationToken authentication) {
		Date currentTime = getNow().getTime();
		JWTClaimsSet build = new JWTClaimsSet.Builder()
				.issueTime(getNow().getTime())
				.notBeforeTime(currentTime)
				.expirationTime(authentication.getExpires())
				.claim("details", authentication.getDetails())
				.claim("tokenType", authentication.getTokenType())
				.claim("ip", authentication.getIp())
				.build();
		JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
				.type(JOSEObjectType.JWT)
				.build();
		SignedJWT signedJWT = new SignedJWT(header, build);
		
		try {
			signedJWT.sign(jwsSigner);
			return signedJWT.serialize();
		} catch (JOSEException e) {
			throw new TokenSignException(e);
		}
	}
	
	
	/**
	 * 对Token进行解码
	 *
	 * @param token
	 * @return
	 */
	@Override
	public AbstractBasicAuthenticationToken decode(String token) {
		try {
			SignedJWT parse = SignedJWT.parse(token);
			if (!parse.verify(verifier)) {
				throw new TokenSignException();
			}
			Date expirationTime = parse.getJWTClaimsSet().getExpirationTime();
			
			
			
			if (getNow().getTime().getTime() > expirationTime.getTime()) {
				throw new TokenExpiresException();
			}
			Object authentication = parse.getJWTClaimsSet().getClaim("details");
			String tokenType = (String) parse.getJWTClaimsSet().getClaim("tokenType");
			String ip = (String) parse.getJWTClaimsSet().getClaim("ip");
			UserDetail userDetail = objectMapper.convertValue(authentication, UserDetail.class);
			
			BearerTokenAuthenticationToken authenticationToken = new BearerTokenAuthenticationToken();
			authenticationToken.setAuthorities(userDetail.getAuthorities());
			authenticationToken.setPrincipal(userDetail.getAccount());
			authenticationToken.setDetails(userDetail);
			authenticationToken.setTokenType(tokenType);
			authenticationToken.setAuthenticated(true);
			authenticationToken.setIp(ip);
			return authenticationToken;
		} catch (ParseException e) {
			throw new TokenParseException(e);
		} catch (JOSEException e) {
			throw new TokenSignException(e);
		}
	}
	
	
	private Calendar getNow() {
		return Calendar.getInstance();
	}
	
}
