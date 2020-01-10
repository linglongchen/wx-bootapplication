/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.modules.bootapplication.modules.user.service;

import com.modules.bootapplication.common.service.CrudService;
import com.modules.bootapplication.modules.user.dao.UserInfoDao;
import com.modules.bootapplication.modules.user.entity.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息Service
 * @author wcf
 * @version 2018-04-17
 */
@Service
@Transactional(readOnly = true)
public class UserInfoService extends CrudService<UserInfoDao, UserInfo> {
	@Resource
	private UserInfoDao dao;

	/**
	 * 查询该openid是否存在
	 * @param userInfo
	 * @return
	 */
	public int getCountByOpenId(UserInfo userInfo){
		return dao.getCountByOpenId(userInfo);
	}

	/**
	 * 根据openid获取用户信息
	 * @param userInfo
	 * @return
	 */
	public UserInfo getByOpenId(UserInfo userInfo){
		return dao.getByOpenId(userInfo);
	}

	@Transactional(readOnly = false)
	public int register(UserInfo userInfo){
		userInfo.preInsert();
		dao.insert(userInfo);
		return userInfo.getId();
	}

	/**
	 * 获取用户抽奖次数
	 * @param userId
	 * @return
	 */
	public UserInfo getCountById(Integer userId){
		return dao.getCountById(userId);
	}

	/**
	 * 将用户领取状态设为1
	 */
	@Transactional(readOnly = false)
	public void setUserCount(){
		List<Integer> idList = dao.getAllId();
		for (int i=0;i<idList.size();i++){
			dao.setUserCount(idList.get(i));
		}
	}


	public Integer getBusinessByUserId(String wechatId){
		return dao.getBusinessByUserId(wechatId);
	}

	public Integer getIsBusiness(Integer userId){
		return dao.getIsBusiness(userId);
	}
}
