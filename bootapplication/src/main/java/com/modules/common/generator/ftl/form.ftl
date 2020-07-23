package ${package_name}.form;
import java.util.Date;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
* @author ${author}
*
* @date ${date}
*/
@Data
@ApiModel("${table_name}")
public class ${table_name}Form {

<#if model_columns?exists>
	<#list model_columns as model>
	<#if model.changeColumnName != 'isDeleted' && model.changeColumnName != 'createUserId' && model.changeColumnName != 'createTime' && model.changeColumnName != 'updateUserId' && model.changeColumnName != 'updateTime'>
		/**
		* ${model.columnComment!}
		*/
		@ApiModelProperty("${model.columnComment!}")
		private ${model.columnType} ${model.changeColumnName};
	</#if>
	</#list>
</#if>

}
