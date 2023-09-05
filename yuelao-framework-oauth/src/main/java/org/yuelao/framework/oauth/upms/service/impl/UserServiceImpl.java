package org.yuelao.framework.oauth.upms.service.impl;

import com.google.common.collect.Lists;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.yuelao.framework.oauth.upms.service.UserService;
import org.yuelao.framework.starter.security.user.UserInfo;


@Component
public class UserServiceImpl implements UserService {
	
	
	@Override
	public UserInfo loadUserByAccount(String username) throws UsernameNotFoundException {
		return getUserInfo();
	}
	
	@Override
	public UserInfo loadUserByPhone(String phone) throws UsernameNotFoundException {
		return getUserInfo();
	}
	
	@Override
	public UserInfo loadUserByEmail(String email) {
		return getUserInfo();
	}
	
	private UserInfo getUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1L);
		userInfo.setUserType("SUPER_ADMIN");
		userInfo.setAccount("zhangsan");
		userInfo.setMobile("17719540702");
		userInfo.setEmail("281344730@qq.com");
		//支持多种加密方式
		userInfo.setPassword("{MD5}{WuncQOEYAxwNHMgolNnqZEmRswrgDntwNVc4gEHsyLQ=}5872882b5d23f7524b1a9bed4c75b244");
		userInfo.setRealName("牛昌");
		userInfo.setNickName("淡淡丶奶油味");
		userInfo.setGender("男");
		userInfo.setEnable(true);
		userInfo.setRoles(Lists.newArrayList("SUPER_ADMIN"));
		userInfo.setAuthorities(Lists.newArrayList("SUPER_ADMIN"));
		userInfo.setDeptIds(Lists.newArrayList(1L, 2L, 3L, 4L, 5L, 6L));
		return userInfo;
	}
	
}
