package org.yuelao.framework.starter.security.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.yuelao.framework.starter.security.user.UserDetail;

public interface OauthUserService {
	
	/**
	 * 根据账号加载用户
	 *
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	UserDetail loadUserByAccount(String username) throws UsernameNotFoundException;
	
	/**
	 * 根据电话号码加载用户
	 *
	 * @param phone
	 * @return
	 * @throws UsernameNotFoundException
	 */
	UserDetail loadUserByPhone(String phone) throws UsernameNotFoundException;
	
	/**
	 * 根据邮箱加载用户
	 *
	 * @param email
	 * @return
	 */
	UserDetail loadUserByEmail(String email);
}
