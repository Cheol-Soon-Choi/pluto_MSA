package com.ccs.clients;

import com.ccs.models.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MemberRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    public Member getMember(Long memberId) {

        ResponseEntity<Member> restExchange =
                restTemplate.exchange(
                        "http://memberserver/users/{memberId}",
                        HttpMethod.GET,
                        null, Member.class, memberId);

        return restExchange.getBody();
    }
}
