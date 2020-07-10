package com.modules.bootapplication.modules.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.modules.bootapplication.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
* @author chen
*
* @date 2020-07-07 16:10:51
*/
@Data
@TableName("user")
public class User extends BaseEntity {

    /**
    *
    */
    @Id
    @GeneratedValue(generator = "JDBC")
	private Long id;
    /**
    *
    */
	private String headImg;
    /**
    *
    */
	private String wechatId;
    /**
    *
    */
	private String phone;
    /**
    *
    */
	private String nickName;
    /**
    *
    */
	private String realName;
    /**
    *
    */
	private String province;
    /**
    *
    */
	private String city;
    /**
    *
    */
	private String area;
    /**
    *
    */
	private String address;


}

