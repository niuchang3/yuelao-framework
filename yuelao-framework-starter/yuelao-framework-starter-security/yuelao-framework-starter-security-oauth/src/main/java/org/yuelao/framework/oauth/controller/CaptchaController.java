package org.yuelao.framework.oauth.controller;


import com.google.common.collect.Maps;
import com.wf.captcha.SpecCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuelao.common.core.spring.SpringContextHelper;
import org.yuelao.common.core.web.ResultModel;
import org.yuelao.framework.oauth.authentication.GrantType;
import org.yuelao.framework.oauth.utils.RedisUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	
	@GetMapping
	public ResultModel<Map<String, String>> captcha() {
		SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
		String verCode = specCaptcha.text().toLowerCase();
		String uuid = UUID.randomUUID().toString();
		Map<String, String> res = Maps.newHashMap();
		res.put("uuid", uuid);
		res.put("image", specCaptcha.toBase64());
		
		Environment environment = SpringContextHelper.getApplicationContext().getEnvironment();
		String[] activeProfiles = environment.getActiveProfiles();
		if (!ObjectUtils.isEmpty(activeProfiles) && !StringUtils.equals("prod", activeProfiles[0])) {
			res.put("code", verCode);
		}
		String key = RedisUtils.genKey(GrantType.PasswordParams.captcha.name(), uuid);
		redisTemplate.opsForValue().set(key, verCode,60, TimeUnit.SECONDS);
		return ResultModel.success(res);
	}
	
	
	
}
