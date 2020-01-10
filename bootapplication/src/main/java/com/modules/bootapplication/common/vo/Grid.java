package com.modules.bootapplication.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.modules.bootapplication.common.config.Global;
import com.modules.bootapplication.common.utils.StringUtils;

import java.util.List;

public class Grid {
    private Long total = 0L; //总记录数
    private List rows;//画面行数据集合

    private int totalPage;//总页数

    public Grid(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public Grid(GridParam param) {
        if (param != null) {
            this.offset = param.getOffset();
            this.limit = param.getLimit();
        }
    }

    /**
     * 起始行数
     */
    @JsonIgnore
    private int offset;
    /**
     * 每页显示的数量
     */
    @JsonIgnore
    private int limit = Integer.valueOf(Global.getConfig("page.pageSize"));
    /**
     * 排序字段
     */
    @JsonIgnore
    private String sort;
    /**
     * 排序规则（asc,desc）
     */
    @JsonIgnore
    private String order = "asc";

    @JsonIgnore
    private String orderBy;


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
        if (StringUtils.isNotEmpty(sort)) {
            this.orderBy = sort + " " + order;
        }
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


    public int getTotalPage() {
        try {
            Float f = total.floatValue() / limit;
            return (int) Math.ceil(f);
        } catch (Exception e) {
            return 0;
        }
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

}
