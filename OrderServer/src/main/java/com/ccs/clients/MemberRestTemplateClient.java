package com.ccs.clients;

import com.ccs.models.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MemberRestTemplateClient {

    //토큰 전파를 위해 기존 restTemplate -> Oauth2 호출 지원하는 Oauth2RestTemplate로 변경 -> JWT전파를 위해 RestTemplate 변경
    @Autowired
    RestTemplate restTemplate;

    public Member getMember(Long memberId) {

        ResponseEntity<Member> restExchange =
                restTemplate.exchange(
                        "http://zuulserver:5555/memberserver/users/{memberId}",
                        HttpMethod.GET,
                        null, Member.class, memberId);

        return restExchange.getBody();
    }
}
