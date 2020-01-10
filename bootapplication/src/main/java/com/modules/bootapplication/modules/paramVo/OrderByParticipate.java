package com.modules.bootapplication.modules.paramVo;

/**
 * 我参与的订单(抽奖和拼团)
 */
public class OrderByParticipate {
    private Integer id;//订单id
    private Integer type;//0:为抽奖;1:为订单
    private Integer userId;//用户id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
