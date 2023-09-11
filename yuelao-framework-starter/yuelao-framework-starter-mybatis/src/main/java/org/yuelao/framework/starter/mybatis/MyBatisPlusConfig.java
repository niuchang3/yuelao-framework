package org.yuelao.framework.starter.mybatis;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.yuelao.framework.starter.mybatis.core.fill.handler.DataFillHandler;
import org.yuelao.framework.starter.mybatis.core.fill.handler.extend.DefaultDataFillHandler;

@Configuration
@ComponentScan("org.yuelao.framework.starter.mybatis.**")
public class MyBatisPlusConfig {
	
	/**
	 * 分页插件
	 *
	 * @return
	 */
	@ConditionalOnMissingBean(MybatisPlusInterceptor.class)
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
		return interceptor;
	}
	
	/**
	 * 默认的数据填充程序
	 *
	 * @return
	 */
	@ConditionalOnMissingBean(DataFillHandler.class)
	@Bean
	public DataFillHandler defaultDataFillHandler() {
		return new DefaultDataFillHandler();
	}
	
}
