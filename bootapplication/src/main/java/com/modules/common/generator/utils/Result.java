/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.modules.common.generator.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据結果封裝
 *
 * @Author zxd
 * @Date 2019/12/5 14:22
 * @Param
 * @Return
 * @Exception
 */
public class Result<T> extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * 返回值
     */
    private T data;

    /**
     * 存放字典
     */
    private Map<String, Object> dict;

    //默认返回是200
    public Result() {
        put("code", ResultCode.CODE_200.code);
        put("msg", "success");
        put("data", data);
    }

    public Result(Integer code, String msg) {
        put("code", code);
        put("msg", msg);
    }


    public static Result error() {
        return error(ResultCode.CODE_500.code, "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(ResultCode.CODE_500.code, msg);
    }

    public static Result error(int code, String msg) {
        Result Result = new Result();
        Result.put("code", code);
        Result.put("msg", msg);
        return Result;
    }

    public static Result error(int code) {
        Result Result = new Result();
        Result.put("code", code);
        Result.put("msg", ResultCode.findMessageByCode(code));
        return Result;
    }

    public static Result ok() {
        return new Result();
    }

    public Result data(T data) {
        this.put("data", data);
        return this;
    }

    public Result putVar(String key, Enums value) {
        if (dict == null) {
            dict = new HashMap<>(16);
        }
        dict.put(key, value != null ? value.getList() : null);
        this.put("dict", dict);
        return this;
    }


    public static Result ok(Map<String, Object> map) {
        Result Result = new Result();
        Result.putAll(map);
        return Result;
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
