package ${package_name}.controller;
import com.modules.bootapplication.common.generator.utils.PageInfo;
import com.modules.bootapplication.common.generator.utils.Result;
import ${package_name}.entity.${table_name};
import ${package_name}.form.${table_name}Form;
import ${package_name}.vo.${table_name}VO;
import ${package_name}.query.${table_name}Query;
import ${package_name}.service.${table_name}Service;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("/${table_name?uncap_first}")
@Api(value = "${table_name}Controller", tags = {"${table_name}Controller"}, description = "${table_name}Controller")
public class ${table_name}Controller {
	@Resource
	private ${table_name}Service ${table_name?uncap_first}Service;


	@GetMapping("/get/{id}")
	@ApiImplicitParams({@ApiImplicitParam(name = "id",value = "id",defaultValue = "0")})
	@ApiOperation(value = "根据id获取数据")
	@ApiResponses({
	@ApiResponse(code = 200, message = "返回数据", response = ${table_name}VO.class)
	})
	public Result get(@ApiParam(name = "id", value = "id", required = true) @PathVariable Long id) {
	 ${table_name} entity = ${table_name?uncap_first}Service.get(id);
		if(entity == null){
			return Result.error("对象信息不存在！");
		}else{
			${table_name}VO entityVO = new ${table_name}VO();
			BeanUtils.copyProperties(entity,entityVO);
			return Result.ok().data(entityVO);
		}

	}


	@DeleteMapping("/delete/{id}")
	@ApiImplicitParams({@ApiImplicitParam(name = "id",value = "id",defaultValue = "0")})
	@ApiOperation(value = "删除")
	@ApiResponses({
	@ApiResponse(code = 200, message = "返回数据", response =  Integer.class)
	})
	public Result delete(@ApiParam(name = "id", value = "id", required = true) @PathVariable Long id) {
		int i = ${table_name?uncap_first}Service.delete(id);
		return Result.ok().data(i);
	}

	@PostMapping("/add")
	@ApiOperation(value = "添加")
	@ApiResponses({
	@ApiResponse(code = 200, message = "返回数据", response = Integer.class)
	})
	public Result add(@ApiParam(name="用户对象",value="传入json格式",required=true)@RequestBody ${table_name}Form form) {
		${table_name} entity = new ${table_name}();
		BeanUtils.copyProperties(form,entity);
		long i = ${table_name?uncap_first}Service.insert(entity);
		return Result.ok().data(i);
	}

	@PutMapping("/update")
	@ApiOperation(value = "更新")
	@ApiResponses({
	@ApiResponse(code = 200, message = "返回数据", response = Integer.class)
	})
	public Result update(@ApiParam(name="用户对象",value="传入json格式",required=true)@RequestBody ${table_name}Form form) {
		${table_name} entity = new ${table_name}();
		BeanUtils.copyProperties(form,entity);
		int i = ${table_name?uncap_first}Service.update(entity.getId(),entity);
		return Result.ok().data(i);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取所有数据分页列表")
	@ApiResponses({
	@ApiResponse(code = 200, message = "返回数据", response = ${table_name}VO.class, responseContainer = "List")
	})
	public Result page(@ApiParam(name="查询对象",value="传入json格式",required=true) ${table_name}Query query,Integer page,Integer size) {
		page = page == null ? 1 : page;
		size = size == null ? 10 : size;
		PageInfo<${table_name}> pageData = ${table_name?uncap_first}Service.page(query,page,size);
		List<${table_name}> list = pageData.getList();
			if(list == null){
			list = new ArrayList<>();
			}
		List<${table_name}VO> voList = new ArrayList<${table_name}VO>();
				for (${table_name} entity : list) {
					${table_name}VO entityVO = new ${table_name}VO();
					BeanUtils.copyProperties(entity,entityVO);
					voList.add(entityVO);
				}
		PageInfo<${table_name}VO> pageInfo = new PageInfo<${table_name}VO>(voList,pageData.getTotalCount(),pageData.getPageSize(),pageData.getCurrPage());
		return Result.ok().data(pageInfo);
	}

}
