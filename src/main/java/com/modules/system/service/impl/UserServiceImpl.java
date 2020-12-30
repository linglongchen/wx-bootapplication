package com.modules.system.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.modules.common.generator.utils.PageInfo;
import com.modules.system.dao.UserDao;
import com.modules.system.entity.User;
import com.modules.system.service.UserService;
import com.modules.system.query.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author chenTom
*
* @date 2020-07-23 16:18:12
*/
@Service
public class UserServiceImpl implements UserService {


    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private UserDao userDao;

	@Override
	public User get(Long id) {
	    Wrapper<User> wrapper = new QueryWrapper<User>().eq("id", id).eq("is_deleted",0);
		User user =  userDao.selectOne(wrapper);
		return user;
	}

	@Override
	public int delete(Long id) {
		User entity = new User();
	    entity.setIsDeleted(1);
		entity.setId(id);
		return userDao.updateById(entity);
	}

	@Override
	public long insert(User entity) {
	    entity.setIsDeleted(0);
		int i = userDao.insert(entity);
        if(i > 0){
			return entity.getId();
			}
		return 0;
	}

	@Override
	public int insertAll(List<User> list) {
		list.forEach(obj ->{
		obj.setCreateTime(new Date());
		obj.setIsDeleted(0);
		});
		return userDao.insertList(list);
		}


	@Override
	public int update(Long id, User entity) {
		Wrapper<User> wrapper = new QueryWrapper<User>().eq("id", id);
		return userDao.update(entity,wrapper);
	}

	@Override
	public Integer getLastNumber() {
	return userDao.getLastNumber();
	}

	@Override
	public PageInfo<User> page(UserQuery query, int page, int size) {
		IPage<User> iPageData = new Page<User>();
		iPageData.setCurrent(page);
		iPageData.setSize(size);
		Wrapper<User> wrapper = new QueryWrapper<User>().eq("is_deleted",0).orderByDesc("create_time");
		IPage<User> iPage = userDao.selectPage(iPageData,wrapper);
		return new PageInfo<User>(iPage);
	}

	@Override
	public User next(Long id) {
		return userDao.next(id);
	}

	@Override
	public User prev(Long id) {
		return userDao.prev(id);
	}

	/**
	 * 查询该openid是否存在
	 * @param user
	 * @return
	 */
	@Override
	public User getCountByOpenId(String openId){
		return userDao.getCountByOpenId(openId);
	}

	/**
	 * 根据openid获取用户信息
	 * @param userInfo
	 * @return
	 */
	@Override
	public User getByOpenId(User userInfo){
		return userDao.getByOpenId(userInfo);
	}

}
