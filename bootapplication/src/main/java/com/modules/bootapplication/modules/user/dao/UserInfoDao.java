/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.modules.bootapplication.modules.user.dao;


import com.modules.bootapplication.common.persistence.CrudDao;
import com.modules.bootapplication.modules.user.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户信息DAO接口
 * @author wcf
 * @version 2018-04-17
 */
@Mapper
public interface UserInfoDao extends CrudDao<UserInfo> {

    /**
     * 查询该openid是否存在
     * @return
     */
    int getCountByOpenId(UserInfo userInfo);

    Integer getBusinessByUserId(String wechatId);

    /**
     * 根据openid获取用户信息
     * @param userInfo
     * @return
     */
    UserInfo getByOpenId(UserInfo userInfo);

    UserInfo getCountById(Integer userId);

    void setUserCount(Integer id);
    List<Integer> getAllId();


    Integer getIsBusiness(Integer userId);
}
