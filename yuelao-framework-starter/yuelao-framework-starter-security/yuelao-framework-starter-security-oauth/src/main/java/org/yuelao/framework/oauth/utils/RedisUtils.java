package org.yuelao.framework.oauth.utils;

import org.apache.commons.lang3.StringUtils;

public class RedisUtils {
	public final static String VAR_SPLITOR = ":";
	
	public static String genKey(String... keyMembers) {
		return StringUtils.join(keyMembers, VAR_SPLITOR).toUpperCase();
	}
}
