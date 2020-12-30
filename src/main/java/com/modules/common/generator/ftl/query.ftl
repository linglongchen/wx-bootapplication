package ${package_name}.query;
import lombok.Data;
import java.util.Date;
/**
* @author ${author}
*
* @date ${date}
*/
@Data
public class ${table_name}Query {

<#if model_columns?exists>
	<#list model_columns as model>
		<#if model.changeColumnName != 'id' && model.changeColumnName != 'isDeleted' && model.changeColumnName != 'createUserId' && model.changeColumnName != 'createTime' && model.changeColumnName != 'updateUserId' && model.changeColumnName != 'updateTime'>
	/**
	* ${model.columnComment!}
	*/
	private ${model.columnType} ${model.changeColumnName};
		</#if>
	</#list>
</#if>

}
