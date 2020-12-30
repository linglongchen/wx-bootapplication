package com.modules.common.generator;

public class ColumnClass {

	/** 字段名称 **/
	private String columnName;
	/** 字段类型 **/
	private String columnType;
	/** 数据库字段首字母小写且去掉下划线字符串 **/
	private String changeColumnName;
	/** 数据库字段注释 **/
	private String columnComment;
	/** 数据库对应类型 **/
	private String jdbcType;

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getChangeColumnName() {
		return changeColumnName;
	}

	public void setChangeColumnName(String changeColumnName) {
		this.changeColumnName = changeColumnName;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

}
