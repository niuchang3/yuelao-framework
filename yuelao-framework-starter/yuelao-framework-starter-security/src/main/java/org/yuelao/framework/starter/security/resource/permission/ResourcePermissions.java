package org.yuelao.framework.starter.security.resource.permission;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.yuelao.framework.starter.security.user.UserInfo;

import java.util.Collection;

@Component("permission")
public class ResourcePermissions {
	
	
	public boolean check() {
		SecurityContext context = SecurityContextHolder.getContext();
		UserInfo details = (UserInfo) context.getAuthentication().getDetails();
		// 如果是超级管理员，所有权限放行
		if (details.isSuperAdmin()) {
			return true;
		}
		//用户当前所拥有的权限集合
		Collection<? extends GrantedAuthority> userAuthorities = context.getAuthentication().getAuthorities();
		//获取当前请求的URL,对菜单权限进行匹配
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return true;
	}
}
