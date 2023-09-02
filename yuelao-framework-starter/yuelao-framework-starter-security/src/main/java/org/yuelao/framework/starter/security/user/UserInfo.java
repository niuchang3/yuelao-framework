package org.yuelao.framework.starter.security.user;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
	
	private String password;
	
	private String username;
	
	private List<String> authorities;
	
	private boolean accountNonExpired;
	
	private boolean accountNonLocked;
	
	private boolean credentialsNonExpired;
	
	private boolean enabled;
	
	public UserInfo() {
	}
	
	public UserInfo(String password, String username, List<String> authorities) {
		this(password, username, authorities, true, true, true, true);
	}
	
	public UserInfo(String password, String username, List<String> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
		this.password = password;
		this.username = username;
		this.authorities = authorities;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}
}
