package org.yuelao.upms.converter;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.yuelao.upms.entity.OauthUserEntity;
import org.yuelao.framework.starter.security.user.UserDetail;

@Mapper
public interface OauthUserConverter {
	
	
	OauthUserConverter INSTANCE = Mappers.getMapper(OauthUserConverter.class);
	
	
	UserDetail converter(OauthUserEntity userPO);

}
