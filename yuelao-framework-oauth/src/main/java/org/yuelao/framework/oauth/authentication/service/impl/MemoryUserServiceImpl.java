package org.yuelao.framework.oauth.authentication.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.yuelao.framework.oauth.authentication.service.UserService;



public class MemoryUserServiceImpl implements UserService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
	
	@Override
	public UserDetails loadUserByPhone(String phone) throws UsernameNotFoundException {
		return null;
	}
}
