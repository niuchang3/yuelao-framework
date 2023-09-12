package org.yuelao.framework.starter.security.core.configurers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

public class DelegateHttpConfigurer extends AbstractHttpConfigurer<DelegateHttpConfigurer, HttpSecurity> {
	
	/**
	 * 需要配置的HTTPSecurity 配置
	 */
	private Map<Class<? extends AbstractConfiguration>, AbstractConfiguration> configuration = Maps.newLinkedHashMap();
	
	/**
	 * 获取配置信息
	 *
	 * @param type
	 * @param <T>
	 * @return
	 */
	public <T> T getConfigurers(Class<T> type) {
		return (T) this.configuration.get(type);
	}
	
	/**
	 * 添加配置信息
	 *
	 * @param type
	 * @param configurer
	 * @param <T>
	 * @return
	 */
	public <T extends AbstractConfiguration> DelegateHttpConfigurer addConfigurers(Class<T> type, T configurer) {
		configuration.put(type, configurer);
		return this;
	}
	
	
	@Override
	public void init(HttpSecurity builder) throws Exception {
		configuration.values().forEach(configurer -> {
			configurer.init(builder);
		});
	}
	
	@Override
	public void configure(HttpSecurity builder) throws Exception {
		configuration.values().forEach(configurer -> configurer.configure(builder));
	}
	
	public List<RequestMatcher> getRequestMatcher() {
		List<RequestMatcher> allRequestMatcher = Lists.newArrayList();
		this.configuration.values().forEach(config -> {
			if(!CollectionUtils.isEmpty(config.getRequestMatcher())){
				allRequestMatcher.addAll(config.getRequestMatcher());
			}
		});
		return allRequestMatcher;
	}
}
