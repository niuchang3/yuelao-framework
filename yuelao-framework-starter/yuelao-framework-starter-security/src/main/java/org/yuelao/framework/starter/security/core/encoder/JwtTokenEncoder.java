package org.yuelao.framework.starter.security.core.encoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.Getter;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.yuelao.framework.starter.security.error.JwtSignException;
import org.yuelao.framework.starter.security.resource.token.BearerTokenAuthenticationToken;
import org.yuelao.framework.starter.security.user.UserInfo;

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
	public String encode(String tokenType, Date expires, Authentication authentication) {
		Date currentTime = getNow().getTime();
		JWTClaimsSet build = new JWTClaimsSet.Builder()
				.issueTime(getNow().getTime())
				.notBeforeTime(currentTime)
				.expirationTime(expires)
				.claim("details", authentication.getDetails())
				.claim("tokenType", tokenType)
				.build();
		JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
				.type(JOSEObjectType.JWT)
				.build();
		SignedJWT signedJWT = new SignedJWT(header, build);
		
		try {
			signedJWT.sign(jwsSigner);
			return signedJWT.serialize();
		} catch (JOSEException e) {
			throw new JwtSignException("Jwt Token sign error", e);
		}
	}
	
	
	/**
	 * 对Token进行解码
	 *
	 * @param token
	 * @return
	 */
	@Override
	public Authentication decode(String token) throws ParseException {
		try {
			SignedJWT parse = SignedJWT.parse(token);
			if (!parse.verify(verifier)) {
				throw new JwtSignException("JWT Token签名错误。");
			}
			Date expirationTime = parse.getJWTClaimsSet().getExpirationTime();
			
			String tokenType = (String) parse.getJWTClaimsSet().getClaim("tokenType");
			
			if (getNow().getTime().getTime() > expirationTime.getTime()) {
				throw new CredentialsExpiredException(tokenType + "已过期");
			}
			Object authentication = parse.getJWTClaimsSet().getClaim("details");
			UserInfo userInfo = objectMapper.convertValue(authentication, UserInfo.class);
			
			List<GrantedAuthority> authorities = userInfo.getAuthorities().stream().map(str -> new SimpleGrantedAuthority(str)).collect(Collectors.toList());
			BearerTokenAuthenticationToken authenticationToken = new BearerTokenAuthenticationToken();
			authenticationToken.setAuthorities(authorities);
			authenticationToken.setPrincipal(userInfo.getAccount());
			authenticationToken.setDetails(userInfo);
			authenticationToken.setTokenType(tokenType);
			authenticationToken.setAuthenticated(true);
			return authenticationToken;
		} catch (ParseException e) {
			throw e;
		} catch (JOSEException e) {
			throw new JwtSignException("JWT Token签名错误。");
		}
	}
	
	
	private Calendar getNow() {
		return Calendar.getInstance();
	}
	
}
