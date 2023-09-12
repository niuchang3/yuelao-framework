package org.yuelao.framework.starter.security.core.authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TenantGrantedAuthority implements GrantedAuthority {
	
	
	private String authority;
	
	private String tenantId;
	
}
