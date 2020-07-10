package com.modules.bootapplication.common.oauth;

import lombok.Data;

/**
 * 移动端api接口返回的数据模型
 * @author chunqiu
 *
 */
@Data
public class Result {
	private int code;		//返回的代码，0表示成功，其他表示失败
    private String msg;		//成功或失败时返回的错误信息

    private Object data;	//成功时返回的数据信息

	public Result(ResultStatusCode resultStatusCode, Object data){
		this.code = resultStatusCode.getCode();
		this.msg = resultStatusCode.getMsg();
		this.data = data;
	}

    public Result(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg){
    	this(code, msg, null);
	}
	public Result(ResultStatusCode resultStatusCode){
    	this(resultStatusCode, null);
	}



}
