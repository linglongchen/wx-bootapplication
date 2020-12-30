package com.modules.system.api;

import com.modules.common.oauth.Result;
import com.modules.common.oauth.ResultStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/test")
@Api(tags = "测试api")
public class TestController {
    /**
     * 测试
     * @return
     */
    @GetMapping("/getInfo")
    @ApiOperation("查询菜单列表")
    public Result getInfo() {
        return new Result(ResultStatusCode.OK);
    }



}
