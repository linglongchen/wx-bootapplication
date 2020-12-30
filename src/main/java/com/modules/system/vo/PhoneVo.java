package com.modules.system.vo;

import lombok.Data;

/**
 * @author v_vllchen
 */
@Data
public class PhoneVo {
    String encryptedData;
    String iv;
    String sessionKey;
}
