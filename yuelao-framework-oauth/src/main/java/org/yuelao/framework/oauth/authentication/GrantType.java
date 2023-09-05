package org.yuelao.framework.oauth.authentication;

public interface GrantType {
	
	String GRANT_TYPE_NAME = "grant_type";
	
	/**
	 * Basic授权类型，账号密码登录参数
	 */
	enum PasswordParams {
		
		uuid,
		captcha,
		account,
		password;
	}
	
	enum MobileParams {
		mobile,
		captcha;
	}
}
