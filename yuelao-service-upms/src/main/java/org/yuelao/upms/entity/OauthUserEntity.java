package org.yuelao.upms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.yuelao.framework.starter.mybatis.po.BasicPO;

@Data
@TableName("oauth_user")
public class OauthUserEntity extends BasicPO {
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
	
}
