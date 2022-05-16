package com.ccs;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@EnableHystrix
@EnableResourceServer
public class MemberServerApplication {

    @Primary
    @LoadBalanced
    @Bean
    public RestTemplate getCustomRestTemplate() {
        return new RestTemplate();
    }

    //모든 트랜잭션(100%)이 집킨 서버에 기록됨
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    public static void main(String[] args) {
        SpringApplication.run(MemberServerApplication.class, args);
    }
}
