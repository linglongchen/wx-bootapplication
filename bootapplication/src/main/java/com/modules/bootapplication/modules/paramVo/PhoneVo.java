package com.modules.bootapplication.modules.paramVo;

import lombok.Data;

@Data
public class PhoneVo {
    String encryptedData;
    String iv;
    String sessionKey;
}
