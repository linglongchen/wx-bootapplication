package com.modules.bootapplication.modules.paramVo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

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
