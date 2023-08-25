package org.yuelao.common.core.constants;

import lombok.Getter;

public enum HttpStatusCode implements HttpStatusCodeConverter {
	/**
	 * 基础状态
	 */
	SUCCESS(200, "接口请求成功。"),
	ERROR(500, "服务器异常。"),
	
	/**
	 * 权限异常
	 */
	PERMISSION_EXCEPTION(4000, ""),
	AUTHENTICATION_FAILED(4001, "认证失败"),
	
	/**
	 * 操作异常
	 */
	OPERATION_ERROR(5001, "数据校验失败。"),
	;
	
	
	/**
	 * 错误码
	 */
	@Getter
	private Integer code;
	/**
	 * 错误码描述
	 */
	@Getter
	private String description;
	
	HttpStatusCode(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	@Override
	public String getName() {
		return this.name();
	}
}
