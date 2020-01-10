package com.modules.bootapplication.modules.utils;

/**
 * @Author mrhuang
 * @Date 2018/3/2 0002 11:05
 */
public enum OrderStatus {
    //0：待付款，1：已付款，2：已提交，3：退款中，4：退款成功
    WAITPAY(0),PAID(1),SUBMIT(2),REFUNDING(3),REFUNDED(4);
    public int value;

    OrderStatus(int value) {
        this.value = value;
    }
}
