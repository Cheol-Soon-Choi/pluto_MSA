package com.ccs.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@Getter
@RefreshScope
public class ServiceConfig {

    @Value("${example.property}")
    private String exampleProperty;

    @Value("${signing.key}")
    private String jwtSigningKey;

    @Value("${redis.server}")
    private String redisServer;

    @Value("${redis.port}")
    private String redisPort;

}
