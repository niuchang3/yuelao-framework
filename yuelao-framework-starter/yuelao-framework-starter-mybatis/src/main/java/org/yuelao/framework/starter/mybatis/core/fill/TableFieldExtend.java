package org.yuelao.framework.starter.mybatis.core.fill;

import java.lang.annotation.*;
import java.util.Date;


/**
 * 使用SpEl表达式进行数据填充
 * 从静态方法中获取数据：
 * 示例：T(xxx.xxx.xxx.Demo).xxx()
 *
 * 直接创建数据对象
 * 示例：new xxx.xxx.xxx.Demo()
 *
 * 从Spring上下文中获取数据
 * 示例： @xxxService.xxx()
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableFieldExtend {
	
	/**
	 * 直接使用某个值进行填充
	 *
	 * @return
	 */
	String value() default "";
	
	/**
	 * 使用SpEl表达式进行数据填充
	 */
	String expression() default "";
	
}
