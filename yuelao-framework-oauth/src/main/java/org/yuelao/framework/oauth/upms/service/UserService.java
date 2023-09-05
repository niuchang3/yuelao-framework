package org.yuelao.framework.oauth.upms.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.yuelao.framework.starter.security.user.UserInfo;

public interface UserService {
	
	/**
	 * 根据账号加载用户
	 *
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	UserInfo loadUserByAccount(String username) throws UsernameNotFoundException;
	
	/**
	 * 根据电话号码加载用户
	 *
	 * @param phone
	 * @return
	 * @throws UsernameNotFoundException
	 */
	UserInfo loadUserByPhone(String phone) throws UsernameNotFoundException;
	
	/**
	 * 根据邮箱加载用户
	 *
	 * @param email
	 * @return
	 */
	UserInfo loadUserByEmail(String email);
}
