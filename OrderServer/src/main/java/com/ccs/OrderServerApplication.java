package com.ccs;

import brave.sampler.Sampler;
import com.ccs.config.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@EnableHystrix
@EnableResourceServer
@EnableBinding(Sink.class)
public class OrderServerApplication {

    @Autowired
    private ServiceConfig serviceConfig;
    private static final Logger logger = LoggerFactory.getLogger(OrderServerApplication.class);

    // redisTemplate 객체 등록
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    //Redis DB커넥션 설정
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(serviceConfig.getRedisServer());
        redisStandaloneConfiguration.setPort(Integer.parseInt(serviceConfig.getRedisPort()));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    //handler로 변경경
//    @StreamListener(Sink.INPUT)
//    public void loggerSink(ItemChangeModel itemChangeModel) {
//        logger.debug("Received an event for item id {}", itmChangeModel.getItemId());
//    }

    // JWT토큰 전파를 위해 custom restTemplae 사용
    @Primary
    @LoadBalanced
    @Bean
    public RestTemplate getCustomRestTemplate() {
        return new RestTemplate();
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

    //모든 트랜잭션(100%)이 집킨 서버에 기록됨
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }
}
