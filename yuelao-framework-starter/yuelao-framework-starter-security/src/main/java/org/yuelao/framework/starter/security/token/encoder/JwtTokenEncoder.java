package org.yuelao.framework.starter.security.token.encoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.yuelao.framework.starter.security.exception.JwtTokenException;
import org.yuelao.framework.starter.security.properties.TokenSettingsProperties;
import org.yuelao.framework.starter.security.token.BearerTokenAuthenticationToken;
import org.yuelao.framework.starter.security.token.OAuth2Token;
import org.yuelao.framework.starter.security.token.TokenEncoder;
import org.yuelao.framework.starter.security.user.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class JwtTokenEncoder implements TokenEncoder<OAuth2Token> {
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
	
	
	@Getter
	private TokenSettingsProperties properties;
	
	
	private String bearerTokenHeaderName = HttpHeaders.AUTHORIZATION;
	
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	private static final Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$",
			Pattern.CASE_INSENSITIVE);
	
	
	public JwtTokenEncoder(TokenSettingsProperties properties, @Autowired(required = false) PrivateKey privateKey, RSAPublicKey publicKey) {
		this.properties = properties;
		this.jwsSigner = new RSASSASigner(privateKey);
		this.verifier = new RSASSAVerifier(publicKey);
	}
	
	/**
	 * 对 Authentication 进行编码
	 *
	 * @param authentication
	 * @return
	 */
	@Override
	public OAuth2Token encode(Authentication authentication) {
		Calendar accessExpire = getNow();
		accessExpire.add(Calendar.MINUTE, properties.getExpire());
		Calendar refreshExpire = getNow();
		refreshExpire.add(Calendar.MINUTE, properties.getRefreshExpire());
		
		String accessToken = encode(properties.getIssuer(), accessExpire.getTime(), authentication);
		String refreshToken = encode(properties.getIssuer(), refreshExpire.getTime(), authentication);
		return new OAuth2Token("Bearer", accessToken, accessExpire.getTime().getTime(), refreshToken, refreshExpire.getTime().getTime());
		
	}
	
	/**
	 * 对 Authentication 进行编码
	 *
	 * @param issuer
	 * @param expireTime
	 * @param authentication
	 * @return
	 */
	private String encode(String issuer, Date expireTime, Authentication authentication) {
		Calendar now = getNow();
		Date signTime = now.getTime();
		JWTClaimsSet build = new JWTClaimsSet.Builder()
				.issuer(issuer)
				.issueTime(signTime)
				.notBeforeTime(signTime)
				.expirationTime(expireTime)
				.claim("authentication", authentication.getDetails())
				.build();
		JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
				.type(JOSEObjectType.JWT)
				.build();
		
		SignedJWT signedJWT = new SignedJWT(header, build);
		
		try {
			signedJWT.sign(jwsSigner);
			return signedJWT.serialize();
		} catch (JOSEException e) {
			throw new JwtTokenException("token 签名失败。", e);
		}
	}
	
	/**
	 * 对Token进行解码
	 *
	 * @param token
	 * @return
	 */
	@Override
	public Authentication decode(String token) {
		try {
			SignedJWT parse = SignedJWT.parse(token);
			if (!parse.verify(verifier)) {
				throw new JwtTokenException("token 验签失败。");
			}
			Object authentication = parse.getJWTClaimsSet().getClaim("authentication");
			UserInfo userInfo = objectMapper.convertValue(authentication, UserInfo.class);
			if (CollectionUtils.isEmpty(userInfo.getAuthorities())) {
				userInfo.setAuthorities(Lists.newArrayList());
			}
			List<SimpleGrantedAuthority> collect = userInfo.getAuthorities().stream().map(str -> new SimpleGrantedAuthority(str)).collect(Collectors.toList());
			BearerTokenAuthenticationToken authenticationToken = new BearerTokenAuthenticationToken(collect, userInfo.getUsername(), null);
			authenticationToken.setAuthenticated(true);
			return authenticationToken;
		} catch (ParseException e) {
			//TODO 此处应该抛出非法签名异常
			throw new JwtTokenException("token 异常", e);
		} catch (JOSEException e) {
			//TODO 此处应该抛出验签异常信息
			throw new JwtTokenException("token 验签失败。", e);
		}
	}
	
	
	private Calendar getNow() {
		return Calendar.getInstance();
	}
	
	
	private String extractFromAuthorizationHeader(HttpServletRequest request) {
		String authorization = request.getHeader(this.bearerTokenHeaderName);
		if (!StringUtils.startsWithIgnoreCase(authorization, "Bearer")) {
			return null;
		}
		Matcher matcher = authorizationPattern.matcher(authorization);
		if (!matcher.matches()) {
			throw new JwtTokenException("令牌格式不正确");
		}
		return matcher.group("token");
	}
}
