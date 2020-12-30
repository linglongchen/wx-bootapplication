package ${package_name}.service;
import ${package_name}.entity.${table_name};
import com.modules.bootapplication.common.generator.utils.PageInfo;
import com.modulessystem.query.${table_name}Query;
import java.util.List;
/**
* @author ${author}
*
* @date ${date}
*/
public interface ${table_name}Service {
    /**
    *获取一条数据
    * @author ${author}
    * @date ${date}
    * @param id
    * @return com.modulessystem.entity.${table_name}
    */
    ${table_name} get(Long id);
    /**
    * 删除一条数据
    * @author ${author}
    * @date ${date}
    * @param id
    * @return int
    */
    int delete(Long id);
    /**
    * 插入一条数据
    * @author ${author}
    * @date ${date}
    * @param entity
    * @return int
    */
    long insert(${table_name} entity);
    /**
    *  批量插入
    * @author ${author}
    * @date ${date}
    * @param list
    * @return int
    */
    int insertAll(List<${table_name}> list);
    /**
    * 更新一条数据
    * @author ${author}
    * @date ${date}
    * @param id
    * @param entity
    * @return int
    */
    int update(Long id,${table_name} entity);
    /**
    * 获取分页数据
    * @author ${author}
    * @date ${date}
    * @param query
    * @param page
    * @param size
    * @return com.modulessystem.common.generator.utils.PageInfo
    */
    PageInfo<${table_name}> page(${table_name}Query query, int page, int size);
    /**
    * 下一条数据
    * @author ${author}
    * @date ${date}
    * @param id
    * @return com.modulessystem.entity.${table_name}
    */
    ${table_name} next(Long id);
    /**
    * 上一条数据
    * @author ${author}
    * @date ${date}
    * @param id
    * @return com.modulessystem.entity.${table_name}
    */
    ${table_name} prev(Long id);
    /**
    *  获取最后一个编号
    * @author ${author}
    * @date ${date}
    * @param
    * @return java.lang.Integer
    */
    Integer getLastNumber();

}
