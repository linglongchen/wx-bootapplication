package com.modules.common.oauth;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author v_vllchen
 */
@Configuration
@Data
public class Audience {
	@Value("${audience.clientId}")
	private String clientId;

	@Value("${audience.base64Secret}")
    private String base64Secret;

	@Value("${audience.name}")
    private String name;

	@Value("${audience.expiresSecond}")
    private int expiresSecond;


}
