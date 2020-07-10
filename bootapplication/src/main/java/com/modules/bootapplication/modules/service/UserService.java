package com.modules.bootapplication.modules.service;


import com.github.pagehelper.PageInfo;
import com.modules.bootapplication.modules.entity.User;
import com.modules.bootapplication.modules.paramVo.UserQuery;

import java.util.List;

/**
* @author chen
*
* @date 2020-07-07 16:10:51
*/
public interface UserService {
    /**
    *获取一条数据
    * @author chen
    * @date 2020-07-07 16:10:51
    * @param id
    * @return com.tencent.modules.sys.entity.User
    */
    User get(Long id);
    /**
    * 删除一条数据
    * @author chen
    * @date 2020-07-07 16:10:51
    * @param id
    * @return int
    */
    int delete(Long id);
    /**
    * 插入一条数据
    * @author chen
    * @date 2020-07-07 16:10:51
    * @param entity
    * @return int
    */
    long insert(User entity);
    /**
    *  批量插入
    * @author chen
    * @date 2020-07-07 16:10:51
    * @param list
    * @return int
    */
    int insertAll(List<User> list);
    /**
    * 更新一条数据
    * @author chen
    * @date 2020-07-07 16:10:51
    * @param id
    * @param entity
    * @return int
    */
    int update(Long id,User entity);
    /**
    * 获取分页数据
    * @author chen
    * @date 2020-07-07 16:10:51
    * @param query
    * @param page
    * @param size
    * @return com.tencent.modules.common.utils.PageInfo
    */
    PageInfo<User> page(UserQuery query, int page, int size);
    /**
    * 下一条数据
    * @author chen
    * @date 2020-07-07 16:10:51
    * @param id
    * @return com.tencent.modules.sys.entity.User
    */
    User next(Long id);
    /**
    * 上一条数据
    * @author chen
    * @date 2020-07-07 16:10:51
    * @param id
    * @return com.tencent.modules.sys.entity.User
    */
    User prev(Long id);
    /**
    *  获取最后一个编号
    * @author chen
    * @date 2020-07-07 16:10:51
    * @param
    * @return java.lang.Integer
    */
    Integer getLastNumber();

    /**
     * 查询该openid是否存在
     * @param userInfo
     * @return
     */
    public int getCountByOpenId(User userInfo);

    /**
     * 根据openid获取用户信息
     * @param userInfo
     * @return
     */
    public User getByOpenId(User userInfo);

}
