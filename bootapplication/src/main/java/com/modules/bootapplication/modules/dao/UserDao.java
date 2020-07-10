package com.modules.bootapplication.modules.dao;

import com.modules.bootapplication.common.utils.MyMapper;
import com.modules.bootapplication.modules.entity.User;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
* @author chen
*
*@date 2020-07-07 16:10:51
*/
public interface UserDao extends MyMapper<User> {
    /**
    * 下一页
    * @author chen
    * @date 2020-07-07 16:10:51
    * @param id
    * @return com.tencent.modules.sys.entity.User
    */
    User next(Long id);
    /**
    * 上一页
    * @author chen
    * @date 2020-07-07 16:10:51
    * @param id
    * @return com.tencent.modules.sys.entity.User
    */
    User prev(Long id);
    /**
    * 覆盖原来的接口方法设置默认自增
    * @author chen
    * @date  2020-07-07 16:10:51
    * @param record
    * @return int
    */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") // id自动增长
    int insert(User record);
    /**
    * 获取最后一个编号
    * @author caizx
    * @date 2020/2/28 20:45
    * @param
    * @return java.lang.String
    */
    @Select("SELECT (number+0) numberStr FROM user WHERE is_deleted=0 ORDER BY numberStr DESC LIMIT 1")
    @ResultType(Integer.class)
    Integer getLastNumber();


    /**
     * 查询该openid是否存在
     * @return
     */
    @Select("select count(*) from user where wechat_id=#{wechatId}")
    @ResultType(Integer.class)
    int getCountByOpenId(User userInfo);


    @Select("select * from user where wechat_id=#{wechatId}")
    @ResultType(User.class)
    User getByOpenId(User userInfo);
}
