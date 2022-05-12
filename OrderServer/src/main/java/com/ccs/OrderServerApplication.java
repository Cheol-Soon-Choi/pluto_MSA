package com.ccs;

import com.ccs.utils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@EnableHystrix
@EnableResourceServer
public class OrderServerApplication {

    // JWT토큰 전파를 위해 custom restTemplae 사용
    @Primary
    @Bean
    public RestTemplate getCustomRestTemplate() {
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }

    //Oauth2 호출을 지원하는 restTemplate(Oauth 토큰 전파)
//    @Bean
//    public OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory factory) {
//        return factory.getUserInfoRestTemplate();
//    }

    //리본 지원 restTemplate
//    @LoadBalanced
//    @Bean
//    public RestTemplate getRestTemplate() {
//        RestTemplate template = new RestTemplate();
//        List interceptors = template.getInterceptors();
//        if (interceptors == null) {
//            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
//        } else {
//            interceptors.add(new UserContextInterceptor());
//            template.setInterceptors(interceptors);
//        }
//
//        return template;
//    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }
}
