/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.modules.bootapplication.modules.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.modules.bootapplication.common.persistence.DataEntity;

import java.util.Date;

/**
 * 用户信息Entity
 * @author wcf
 * @version 2018-04-17
 */
public class UserInfo extends DataEntity<UserInfo> {

	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String headImg;		// 头像
	private String wechatId;		// 微信id
	private String province;		// 省
	private String city;		// 市
	private String area;		// 区
	private String address;		// 地址
	private String isNew;		// 是否为新用户
	private String phone;//手机号
	private String realName;//真实姓名
	private Integer isAddress;//是否进行个人认证
	private Integer count;
	private Integer count2;
	private Integer isBusiness;
	private Integer isMember;
	private Date memberDate;

	public Integer getIsMember() {
		return isMember;
	}

	public void setIsMember(Integer isMember) {
		this.isMember = isMember;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getMemberDate() {
		return memberDate;
	}

	public void setMemberDate(Date memberDate) {
		this.memberDate = memberDate;
	}

	public Integer getIsBusiness() {
		return isBusiness;
	}

	public void setIsBusiness(Integer isBusiness) {
		this.isBusiness = isBusiness;
	}

	public Integer getCount2() {
		return count2;
	}

	public void setCount2(Integer count2) {
		this.count2 = count2;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getIsAddress() {
		return isAddress;
	}

	public void setIsAddress(Integer isAddress) {
		this.isAddress = isAddress;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public UserInfo() {
		super();
	}

	public UserInfo(Integer id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
}
