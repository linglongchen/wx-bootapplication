package com.modules.bootapplication.common.vo;

/**
 * @Description: 对应前端ztree插件的json模型
 * @author wcf
 * @date 2016年9月21日
 * @company chunqiuSoft
 */
public class ZTree {
	private Integer id;
	private Integer pId;
	private String name;
	private boolean open = true;//节点是否打开
	private boolean checked;//节点是否选中
	private boolean chkDisabled;//是否禁止勾选

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
}
