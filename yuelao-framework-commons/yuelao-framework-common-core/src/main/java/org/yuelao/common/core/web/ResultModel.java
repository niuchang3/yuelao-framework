package org.yuelao.common.core.web;


import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;
import org.yuelao.common.core.constants.HttpStatusCode;
import org.yuelao.common.core.constants.HttpStatusCodeConverter;
import org.yuelao.common.core.spring.SpringContextHelper;

import java.io.Serializable;

/**
 *test
 * @param <T>
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultModel<T> implements Serializable {
	
	
	/**
	 * 操作码
	 */
	private Integer code;
	/**
	 * 提供给开发人员看的信息
	 */
	private String devMessage;
	/**
	 * 需要提示用户的信息
	 */
	private String message;
	/**
	 * 请求返回数据
	 */
	private T data;
	/**
	 * 请求URL
	 */
	private String url;
	
	
	/**
	 * 操作成功，返回数据结果集
	 *
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> ResultModel<T> success(T data) {
		//URL 需要在上下文中获取
		return new ResultModel(HttpStatusCode.SUCCESS.getCode(), "", HttpStatusCode.SUCCESS.getDescription(), data, "");
	}
	
	/**
	 * 操作失败
	 * 如果不等于生产环境时，会携带出异常栈信息
	 *
	 * @param <T>
	 * @return
	 */
	public static <T> ResultModel<T> failed(HttpStatusCodeConverter requestCode, Throwable throwable, String message, String url) {
		return new ResultModel(requestCode.getCode(), getDevMessage(throwable), message, null, url);
	}
	
	/**
	 * 开发环境会获取栈信息，方便开发人员调试异常
	 *
	 * @param throwable
	 * @return
	 */
	private static String getDevMessage(Throwable throwable) {
		if (ObjectUtils.isEmpty(throwable)) {
			return null;
		}
		Environment environment = SpringContextHelper.getApplicationContext().getEnvironment();
		String[] activeProfiles = environment.getActiveProfiles();
		if (ObjectUtils.isEmpty(activeProfiles)) {
			return null;
		}
		if (!StringUtils.equals("prod", activeProfiles[0])) {
			return ExceptionUtil.stacktraceToString(throwable);
		}
		return null;
	}
}
