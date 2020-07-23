package ${package_name}.entity;
import lombok.Data;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import com.modules.common.base.BaseEntity;
/**
* @author ${author}
*
* @date ${date}
*/
@Data
@TableName("${table_name_small}")
public class ${table_name} extends  BaseEntity{

    <#if model_columns?exists>
        <#list model_columns as model>
     <#if model.changeColumnName != 'isDeleted' && model.changeColumnName != 'createUserId' && model.changeColumnName != 'createTime' && model.changeColumnName != 'updateUserId' && model.changeColumnName != 'updateTime'>
    /**
    * ${model.columnComment!}
    */
	private ${model.columnType} ${model.changeColumnName};
	</#if>
       </#list>
    </#if>


}

