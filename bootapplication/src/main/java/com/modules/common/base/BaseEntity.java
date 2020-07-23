/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.modules.common.base;

import lombok.Data;

import java.util.Date;

/**
 * @author v_vllchen
 */
@Data
public class BaseEntity {
	/**
	 * 创建人id
	 */
	private Long createUserId;
	/**
	 * 更新人id
	 */
	private Long updateUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否删除
	 */
	private Integer isDeleted;

}

