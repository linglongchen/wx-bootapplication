package com.modules.common.utils;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自定义引入增强Mapper操作
 *
 * BaseMapper接口：使mapper包含完整的CRUD方法
 * ConditionMapper接口：使mapper支持Condition类型参数
 * MySqlMapper接口：使mapper支持MySQL特有的批量插入和返回自增字段
 * IdsMapper接口：使mapper支持批量ID操作
 * 用法可参考博客:https://blog.csdn.net/fangwenzheng88/article/details/78713091
 *
 * @author: zxd
 * @Date: 2019/12/5 15:28
 */
public interface MyMapper<T> extends BaseMapper<T>, MySqlMapper<T>, ConditionMapper<T>, IdsMapper<T> {
}
