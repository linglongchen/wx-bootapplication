package com.modules.common.oauth;


/**
 * @param
 * @author wcf
 * @description 错误代码
 * @date 2018/1/5
 * @return
 */
public enum ResultStatusCode {
    OK(0, "OK"),
    SIGN_ERROR(120, "签名错误"),
    TIME_OUT(130, "访问超时"),
    BAD_REQUEST(400, "参数解析失败"),
    INVALID_TOKEN(401, "无效的授权码"),
    INVALID_CLIENTID(402, "无效的密钥"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    SYSTEM_ERR(500, "服务器运行异常"),
    NOT_EXIST_USER_OR_ERROR_PWD(10000, "该用户不存在或密码错误"),
    STOCK_OUT(10001, "商品超出库存"),
    NOT_EXIST_BUSINESS(10002, "该商家不存在"),
    BIND_PHONE(10010, "请绑定手机号"),
    INVALID_CAPTCHA(30005, "无效的验证码"),
    USER_FROZEN(40000, "该用户已被冻结"),
    LOGINED_IN(40001, "该用户已登录"),
    GOODS_IS_RECEIVE(50000,"该礼物已被领取"),
    HAVE_JOIN(50001,"该用户已拼团"),
    MONEY_LOW(50002,"拼团价格必须超过1元"),
    MONEY_HIGH(50003,"拼团价格已超过剩余拼团价格"),
    MONEY_NOT_TRUE(50004,"拼团价格需与剩余价格相同"),
    GROUP_HAVE_SUCESS(50005,"拼团已成功"),
    PHONE_SUCCESS(50004,"解密成功"),
    GROUP_HAVE_FAIL(50006,"拼团已失败");


    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private ResultStatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
