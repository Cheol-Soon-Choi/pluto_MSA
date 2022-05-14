package com.ccs.clients;

import com.ccs.models.entity.Member;
import com.ccs.repository.MemberRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    MemberRedisRepository memberRedisRepository;

    private static final Logger logger = LoggerFactory.getLogger(MemberRestTemplateClient.class);

    //캐시 확인
    private Member checkRedisCache(Long memberId) {
        try {
            return memberRedisRepository.findMember(memberId);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("##### Error encountered while trying to retrieve memberId: {} check Redis Cache.  Exception {}", memberId, ex);
            return null;
        }
    }

    //캐시 저장
    private void cacheMemberObject(Member member) {
        try {
            memberRedisRepository.saveMember(member);
        } catch (Exception ex) {
            logger.error("##### Unable to cache itemId {} in Redis. Exception {}", member.getId(), ex);
        }
    }

    public Member getMember(Long memberId) {

        Member member = checkRedisCache(memberId);

        //캐시 o -> 캐시 전달
        if (member != null) {
            logger.debug("##### I have successfully retrieved an memberId {} from the redis cache: {}", memberId, member);
            return member;
        }

        //캐시 x -> 아이템 서버 호출
        logger.debug("##### Unable to locate member from the redis cache: {}.", memberId);
        ResponseEntity<Member> restExchange =
                restTemplate.exchange(
                        "http://zuulserver:5555/memberserver/users/{memberId}",
                        HttpMethod.GET,
                        null, Member.class, memberId);
        member = restExchange.getBody();

        //호출 후 캐시서버에 저장
        if (member != null) {
            cacheMemberObject(member);
        }
        return member;
    }
}
