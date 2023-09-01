package org.yuelao.framework.oauth.authentication.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.yuelao.common.core.constants.HttpStatusCode;
import org.yuelao.common.core.web.ResultModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 已登录，但对资源无权限的异常处理
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpStatusCode.ERROR.getCode());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		try (PrintWriter writer = response.getWriter()) {
			ResultModel<Object> failed = ResultModel.failed(HttpStatusCode.AUTHENTICATION_FAILED, accessDeniedException, accessDeniedException.getMessage(), "");
			writer.write(objectMapper.writeValueAsString(failed));
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
