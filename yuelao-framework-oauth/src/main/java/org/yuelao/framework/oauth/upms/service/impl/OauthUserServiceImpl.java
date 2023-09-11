package org.yuelao.framework.oauth.upms.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.yuelao.framework.oauth.upms.converter.OauthUserConverter;
import org.yuelao.framework.oauth.upms.entity.OauthUserEntity;
import org.yuelao.framework.oauth.upms.mapper.OauthUserMapper;
import org.yuelao.framework.oauth.upms.service.OauthUserService;
import org.yuelao.framework.starter.mybatis.core.wrapper.LambdaQueryWrapperPlus;
import org.yuelao.framework.starter.security.core.authority.TenantGrantedAuthority;
import org.yuelao.framework.starter.security.user.UserDetail;

/**
 * 用户Service
 * <p>
 * 目前暂未实现权限查询
 */
@Component
public class OauthUserServiceImpl implements OauthUserService {
	/**
	 * OauthUserMapper
	 */
	@Autowired
	private OauthUserMapper oauthUserMapper;
	
	/**
	 * 根据账号查询用户详情
	 *
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetail loadUserByAccount(String username) throws UsernameNotFoundException {
		OauthUserEntity oauthUserEntity = oauthUserMapper.selectOne(new LambdaQueryWrapperPlus<OauthUserEntity>().eq(OauthUserEntity::getAccount, username));
		if (ObjectUtils.isEmpty(oauthUserEntity)) {
			throw new UsernameNotFoundException("账号信息不存在。");
		}
		UserDetail userDetail = OauthUserConverter.INSTANCE.converter(oauthUserEntity);
		setAuthorities(userDetail);
		return userDetail;
	}
	
	/**
	 * 根据手机号码查询用户详情
	 *
	 * @param phone
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetail loadUserByPhone(String phone) throws UsernameNotFoundException {
		OauthUserEntity oauthUserEntity = oauthUserMapper.selectOne(new LambdaQueryWrapperPlus<OauthUserEntity>().eq(OauthUserEntity::getMobile, phone));
		if (ObjectUtils.isEmpty(oauthUserEntity)) {
			throw new UsernameNotFoundException("账号信息不存在。");
		}
		UserDetail userDetail = OauthUserConverter.INSTANCE.converter(oauthUserEntity);
		setAuthorities(userDetail);
		
		return userDetail;
	}
	
	/**
	 * 根据用户电子邮箱查询用户详情
	 *
	 * @param email
	 * @return
	 */
	@Override
	public UserDetail loadUserByEmail(String email) {
		OauthUserEntity oauthUserEntity = oauthUserMapper.selectOne(new LambdaQueryWrapperPlus<OauthUserEntity>().eq(OauthUserEntity::getEmail, email));
		if (ObjectUtils.isEmpty(oauthUserEntity)) {
			throw new UsernameNotFoundException("账号信息不存在。");
		}
		UserDetail userDetail = OauthUserConverter.INSTANCE.converter(oauthUserEntity);
		setAuthorities(userDetail);
		return userDetail;
	}
	
	/**
	 * 为用户赋予权限
	 *
	 * @param userDetail
	 */
	private void setAuthorities(UserDetail userDetail) {
		userDetail.setRoles(Lists.newArrayList("SUPER_ADMIN"));
		//
		userDetail.setAuthorities(Lists.newArrayList(new TenantGrantedAuthority("SUPER_ADMIN","1")));
	}
	
}
