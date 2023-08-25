package org.yuelao.framework.oauth.authentication;

public interface OauthGrantType {
	
	String GRANT_TYPE_NAME = "grant_type";
	
	/**
	 * 获取code码
	 *
	 * @return
	 */
	String getCode();
}
