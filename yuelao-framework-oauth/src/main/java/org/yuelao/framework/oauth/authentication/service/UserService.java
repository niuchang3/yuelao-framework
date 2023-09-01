package org.yuelao.framework.oauth.authentication.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService {
	
	/**
	 * 根据账号加载用户详情
	 *
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	/**
	 * 根据手机号码加载用户详情
	 *
	 * @param phone
	 * @return
	 * @throws UsernameNotFoundException
	 */
	UserDetails loadUserByPhone(String phone) throws UsernameNotFoundException;
}
