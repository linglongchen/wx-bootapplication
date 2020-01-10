package com.modules.bootapplication.common.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GridParam {
	/**
	 * 起始行数
	 */
	@NotNull
	@Min(0)
	private Integer offset;
	/**
	 * 每页显示的数量
	 */
	@NotNull
	@Min(-1)
	@Max(50)
    private Integer limit;

	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
