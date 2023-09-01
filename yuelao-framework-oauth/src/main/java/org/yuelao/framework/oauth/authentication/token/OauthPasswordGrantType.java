package org.yuelao.framework.oauth.authentication.token;

/**
 * 用于用户名密码登录
 */
public enum OauthPasswordGrantType implements OauthGrantType {
	
	
	UUID("uuid"),
	/**
	 * 验证码
	 */
	CAPTCHA("captcha"),
	/**
	 * 用户名
	 */
	USERNAME("username"),
	
	/**
	 * 密码
	 */
	PASSWORD("password"),
	
	GRANT_TYPE("password");
	
	private final String code;
	
	OauthPasswordGrantType(String code) {
		this.code = code;
	}
	
	@Override
	public String getCode() {
		return this.code;
	}
}
