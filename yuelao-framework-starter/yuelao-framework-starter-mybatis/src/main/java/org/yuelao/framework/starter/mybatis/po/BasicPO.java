package org.yuelao.framework.starter.mybatis.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.yuelao.framework.starter.mybatis.core.fill.TableFieldExtend;

import java.util.Date;

/**
 * 基础性PO对象
 */
@Data
public abstract class BasicPO {
	/**
	 * 主键ID
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	/**
	 * 创建时间
	 */
	@TableFieldExtend(expression = "new java.util.Date()")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableFieldExtend(expression = "new java.util.Date()")
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;
	
	/**
	 * 数据版本号
	 */
	@TableField
	@Version
	private Long version;
	
}
