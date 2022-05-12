package com.ccs.clients;

import com.ccs.models.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

@Component
public class MemberRestTemplateClient {

    //토큰 전파를 위해 기존 restTemplate -> Oauth2RestTemplate로 변경
    @Autowired
    OAuth2RestTemplate restTemplate;

    public Member getMember(Long memberId) {

        ResponseEntity<Member> restExchange =
                restTemplate.exchange(
                        "http://zuulserver:5555/memberserver/users/{memberId}",
                        HttpMethod.GET,
                        null, Member.class, memberId);

        return restExchange.getBody();
    }
}
