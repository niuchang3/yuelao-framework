package org.yuelao.framework.oauth.authentication.token;

public interface TokenGenerator<T extends OAuth2Token> {
	
	
	void generation();
}
