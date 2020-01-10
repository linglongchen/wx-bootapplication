/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.modules.bootapplication.modules.sys.entity;

import com.modules.bootapplication.common.config.Global;
import com.modules.bootapplication.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 系统角色Entity
 * @author wcf
 * @version 2016-09-21
 */
public class Role extends DataEntity<Role> {

	private static final long serialVersionUID = 1L;
	private String name; 	// 角色名称
	private String enname;	// 英文名称
	private Integer roleType;// 角色类型; 1:超级管理员，2：系统管理员，3：普通管理员
	private String dataScope;// 数据范围

	private String oldName; 	// 原角色名称
	private String oldEnname;	// 原英文名称
	private String sysData; 		//是否是系统数据
	private String useable; 		//是否是可用

	private User user;		// 根据用户ID查询角色列表


	// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
	public static final String DATA_SCOPE_ALL = "1";
	public static final String DATA_SCOPE_COMPANY_AND_CHILD = "2";
	public static final String DATA_SCOPE_COMPANY = "3";
	public static final String DATA_SCOPE_OFFICE_AND_CHILD = "4";
	public static final String DATA_SCOPE_OFFICE = "5";
	public static final String DATA_SCOPE_SELF = "8";
	public static final String DATA_SCOPE_CUSTOM = "9";

	//角色类型（1：超级管理员；2：系统管理员，3：普通管理员）
	public static final Integer ROLE_TYPE_SUPER = 1;
	public static final Integer ROLE_TYPE_SYS = 2;
	public static final Integer ROLE_TYPE_NORMAL = 3;

	public Role() {
		super();
		this.dataScope = DATA_SCOPE_SELF;
		this.useable = Global.YES;
		this.roleType = ROLE_TYPE_NORMAL;
	}

	public Role(Integer id){
		super(id);
	}

	public Role(User user) {
		this();
		this.user = user;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public String getSysData() {
		return sysData;
	}

	public void setSysData(String sysData) {
		this.sysData = sysData;
	}

	@Length(min=1, max=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=1, max=100)
	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	@Length(min=1, max=2)
	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getOldEnname() {
		return oldEnname;
	}

	public void setOldEnname(String oldEnname) {
		this.oldEnname = oldEnname;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
