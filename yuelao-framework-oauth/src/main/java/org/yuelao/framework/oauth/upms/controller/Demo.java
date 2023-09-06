package org.yuelao.framework.oauth.upms.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuelao.common.core.web.ResultModel;

@RestController
@RequestMapping
public class Demo {
	
	@PreAuthorize("@permission.check()")
	@GetMapping("/test")
	public ResultModel<String> test() {
		return ResultModel.success("SUCCESS");
	}
	
	
	@GetMapping("/test2")
	public ResultModel<String> test2() {
		return ResultModel.success("SUCCESS");
	}
}
