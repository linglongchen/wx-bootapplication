package com.modules.common.generator.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态码自定义
 *
 *  @author: zxd
 *  @Date: 2019/12/5 14:22
 */
public enum ResultCode {
    CODE_200(200, "访问成功"),
    CODE_401(401, "非法请求，参数错误"),
    CODE_402(402, "token失效"),
    CODE_403(403, "Method使用错误，请查看API"),
    CODE_404(404, "找不到访问地址"),
    CODE_406(406, "没有权限访问"),
    CODE_407(407, "用户登录已失效，请重新登录"),
    CODE_500(500, "系统内部错误"),
    CODE_501(501, "对象已存在，请检测唯一性"),
    CODE_502(502, "当前版本与服务器版本不一致,请更新应用"),
    CODE_503(503, "对象不存在，请检测请求参数"),
    CODE_504(504, "服务器连接错误，请稍后重试"),
    CODE_505(505, "key校验失败"),
    CODE_506(506, "RPC服务连接失败，请稍后重试"),
    CODE_507(507, "空指针异常"),
    CODE_508(508, "类型转换异常"),
    CODE_509(509, "IO异常"),
    CODE_510(510, "未知方法异常"),
    CODE_511(511, "数组越界异常");


    public Integer code;
    public String msg;

    ResultCode(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    protected static Map<Integer, String> codeMessageMap;

    static {
        codeMessageMap = new HashMap<>();
        for (ResultCode resultCode : ResultCode.values()) {
            codeMessageMap.put(resultCode.code, resultCode.msg);
        }
    }

    public static String findMessageByCode(Integer code) {
        if (codeMessageMap != null) {
            return codeMessageMap.get(code);
        }
        return "";
    }

}
