package ${package_name}.dao;
import ${package_name}.entity.${table_name};
import com.modules.common.generator.utils.MyMapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;

/**
* @author ${author}
*
*@date ${date}
*/
public interface ${table_name}Dao extends MyMapper<${table_name}> {
    /**
    * 下一页
    * @author ${author}
    * @date ${date}
    * @param id
    * @return com.modulessystem.entity.${table_name}
    */
    ${table_name} next(Long id);
    /**
    * 上一页
    * @author ${author}
    * @date ${date}
    * @param id
    * @return com.modulessystem.entity.${table_name}
    */
    ${table_name} prev(Long id);
    /**
    * 覆盖原来的接口方法设置默认自增
    * @author ${author}
    * @date  ${date}
    * @param record
    * @return int
    */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") // id自动增长
    int insert(${table_name} record);
    /**
    * 获取最后一个编号
    * @author caizx
    * @date 2020/2/28 20:45
    * @param
    * @return java.lang.String
    */
    @Select("SELECT (number+0) numberStr FROM ${table_name_small} WHERE is_deleted=0 ORDER BY numberStr DESC LIMIT 1")
    @ResultType(Integer.class)
    Integer getLastNumber();

}
