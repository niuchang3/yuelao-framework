package org.yuelao.framework.oauth.authentication.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.yuelao.common.core.constants.HttpStatusCode;
import org.yuelao.common.core.web.ResultModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 未登录游客模式下的异常处理
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpStatusCode.ERROR.getCode());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		try {
			ResultModel<Object> failed = ResultModel.failed(HttpStatusCode.AUTHENTICATION_FAILED, authException, authException.getMessage(), "");
			writer.write(objectMapper.writeValueAsString(failed));
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			writer.close();
		}
	}
}
