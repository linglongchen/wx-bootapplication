package ${package_name}.excelparse;

import ${package_name}.entity.${table_name};
import ${package_name}.service.${table_name}Service;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;

/**
* @author ${author}
* @date ${date}
*/
public class ${table_name}DataListener extends AnalysisEventListener<${table_name}Data> {
	private static final Logger LOGGER = LoggerFactory.getLogger(${table_name}DataListener.class);

	private ${table_name}Service ${table_name?uncap_first}Service;
	public ${table_name}DataListener(${table_name}Service ${table_name?uncap_first}Service){
	this.${table_name?uncap_first}Service = ${table_name?uncap_first}Service;
	}
	/**
	* 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
	*/
	private static final int BATCH_COUNT = 3000;

	List<${table_name}Data> list = new ArrayList<>();

		@Override
		public void invoke(${table_name}Data data, AnalysisContext context) {
		LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
		list.add(data);
		if (list.size() >= BATCH_COUNT) {
		saveData();
		list.clear();
		}
		}

		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
		saveData();
		list.clear();
		LOGGER.info("所有数据解析完成！");
		}

		/**
		* 加上存储数据库
		*/
		private void saveData() {
		if(list.isEmpty()){
		return;
		}
		List<${table_name}> entityList = new ArrayList<>();
			for (${table_name}Data data: list) {
		    ${table_name} entity = new ${table_name}();
			BeanUtils.copyProperties(data,entity);
			entityList.add(entity);
			}
			LOGGER.info("{}条数据，开始存储数据库！", list.size());
		    ${table_name?uncap_first}Service.insertAll(entityList);
			entityList.clear();
			LOGGER.info("存储数据库成功！");
			}


	
}
