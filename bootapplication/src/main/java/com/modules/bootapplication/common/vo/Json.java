package com.modules.bootapplication.common.vo;

import java.util.Map;

/**
 * 返回给前端的json数据
 * @author Administrator
 *
 */
public class Json {

	public boolean success;
	public String msg;
	public Map<String, Object> obj;
	public Object data;

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getObj() {
		return obj;
	}
	public void setObj(Map<String, Object> obj) {
		this.obj = obj;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
