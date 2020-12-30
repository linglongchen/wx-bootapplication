package ${package_name}.vo;
import lombok.Data;
import java.util.Date;
import com.modules.system.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
* @author ${author}
*
* @date ${date}
*/
@Data
@ApiModel("${table_name}VO")
public class ${table_name}VO extends BaseVO{

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
