package com.modules.system.vo;

import lombok.Data;

/**
 * @Description:
 * @Date: 2018/1/4
 * @Author: wcf
 */
@Data
public class Account {

    private String phone;

    private String jsCode;

    private String nickName;

    private String avatarUrl;

    private String refreshToken;
}
