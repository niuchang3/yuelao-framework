package org.yuelao.framework.starter.security.user;

import lombok.Data;
import org.yuelao.framework.starter.security.constant.BasicUserType;

import java.util.List;

@Data
public class UserInfo {
	
	/**
	 * 用户ID
	 */
	private Long id;
	/**
	 * 账户类型
	 */
	private String userType;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 性别
	 */
	private String gender;
	
	/**
	 * 账号是否启用
	 */
	private Boolean enable;
	
	/**
	 * 角色CODE信息
	 */
	private List<String> roles;
	/**
	 * 接口权限
	 */
	private List<String> authorities;
	/**
	 * 部门ID,用于做数据权限
	 */
	private List<Long> deptIds;
	
	/**
	 * 判断是否为超级管理员
	 *
	 * @return
	 */
	public Boolean isSuperAdmin() {
		return BasicUserType.SUPER_ADMIN.name().equals(getUserType());
	}
	
	/**
	 * 判断是否为租户管理员
	 *
	 * @return
	 */
	public Boolean isAgencyAdmin() {
		return BasicUserType.AGENCY_ADMIN.name().equals(getUserType());
	}
	
	/**
	 * 判断是否为普通用户
	 *
	 * @return
	 */
	public Boolean isRegularUser() {
		return BasicUserType.REGULAR_USERS.name().equals(getUserType());
	}
	
}
