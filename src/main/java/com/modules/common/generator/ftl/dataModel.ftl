package ${package_name}.excelparse;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

/**
* @author ${author}
*
* @date ${date}
*/
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class ${table_name}Data implements Serializable {

    <#if model_columns?exists>
        <#list model_columns as model>
            <#if model.changeColumnName != 'isDeleted' && model.changeColumnName != 'createUserId' && model.changeColumnName != 'createTime' && model.changeColumnName != 'updateUserId' && model.changeColumnName != 'updateTime'>
    /**
    * ${model.columnComment!}
    */
    @ExcelProperty("${model.columnComment!}")
	private ${model.columnType} ${model.changeColumnName};
	</#if>
       </#list>
    </#if>
}
