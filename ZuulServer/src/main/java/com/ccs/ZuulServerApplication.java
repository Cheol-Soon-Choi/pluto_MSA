package com.ccs;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableZuulProxy
@RefreshScope
@EnableHystrix
public class ZuulServerApplication {

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
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}
