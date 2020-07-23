package ${package_name}.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.modules.common.generator.utils.PageInfo;
import ${package_name}.dao.${table_name}Dao;
import ${package_name}.entity.${table_name};
import ${package_name}.query.${table_name}Query;
import ${package_name}.service.${table_name}Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author ${author}
*
* @date ${date}
*/
@Service
public class ${table_name}ServiceImpl implements ${table_name}Service {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private ${table_name}Dao ${table_name?uncap_first}Dao;

	@Override
	public ${table_name} get(Long id) {
	    Wrapper<${table_name}> wrapper = new QueryWrapper<${table_name}>().eq("id", id).eq("is_deleted",0);
		${table_name} ${table_name?uncap_first} =  ${table_name?uncap_first}Dao.selectOne(wrapper);
		return ${table_name?uncap_first};
	}

	@Override
	public int delete(Long id) {
		${table_name} entity = new ${table_name}();
	    entity.setIsDeleted(1);
		entity.setId(id);
		return ${table_name?uncap_first}Dao.updateById(entity);
	}

	@Override
	public long insert(${table_name} entity) {
	    entity.setIsDeleted(0);
		int i = ${table_name?uncap_first}Dao.insert(entity);
        if(i > 0){
			return entity.getId();
			}
		return 0;
	}

	@Override
	public int insertAll(List<${table_name}> list) {
		list.forEach(obj ->{
		obj.setCreateTime(new Date());
		obj.setIsDeleted(0);
		});
		return ${table_name?uncap_first}Dao.insertList(list);
		}


	@Override
	public int update(Long id, ${table_name} entity) {
		Wrapper<${table_name}> wrapper = new QueryWrapper<${table_name}>().eq("id", id);
		return ${table_name?uncap_first}Dao.update(entity,wrapper);
	}

	@Override
	public Integer getLastNumber() {
	return ${table_name?uncap_first}Dao.getLastNumber();
	}

	@Override
	public PageInfo<${table_name}> page(${table_name}Query query, int page, int size) {
		IPage<${table_name}> iPageData = new Page<${table_name}>();
		iPageData.setCurrent(page);
		iPageData.setSize(size);
		Wrapper<${table_name}> wrapper = new QueryWrapper<${table_name}>().eq("is_deleted",0).orderByDesc("create_time");
		IPage<${table_name}> iPage = ${table_name?uncap_first}Dao.selectPage(iPageData,wrapper);
		return new PageInfo<${table_name}>(iPage);
	}

	@Override
	public ${table_name} next(Long id) {
		return ${table_name?uncap_first}Dao.next(id);
	}

	@Override
	public ${table_name} prev(Long id) {
		return ${table_name?uncap_first}Dao.prev(id);
	}
}
