package org.yuelao.framework.oauth.authentication;

/**
 * 用户手机号码端登录
 */
public enum UserPhoneGrantType implements OauthGrantType {
	/**
	 * 手机号码
	 */
	MOBILE("mobile"),
	/**
	 * 短信验证码
	 */
	SMS_CODE("smsCode"),
	/**
	 * 手机认证方式
	 */
	GRANT_TYPE("mobile");
	
	
	private String code;
	
	UserPhoneGrantType(String code) {
		this.code = code;
	}
	
	
	@Override
	public String getCode() {
		return this.code;
	}
	
}
