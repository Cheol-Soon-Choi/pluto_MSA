package com.ccs.services;

import com.ccs.events.source.SimpleSourceBean;
import com.ccs.models.constant.Role;
import com.ccs.models.entity.Member;
import com.ccs.models.entity.MemberRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    public final MemberRepository memberRepository;
    private final SimpleSourceBean simpleSourceBean;

    @Transactional
    @HystrixCommand(fallbackMethod = "FallbackMember",
            //쓰레드 풀 설정 - 벌크헤드
            threadPoolKey = "MemberThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            }
    )
    public Member getMember(Long memberId) {

        this.sleep();

        return memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
    }

    public void sleep() {
        int a = (int) (Math.random() * 10 + 1);
        try {
            if (a > 5) {
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Member FallbackMember(Long memberId) {
        return Member.builder()
                .id(memberId)
                .name("Member_Fallback")
                .password("1")
                .role(Role.ADMIN)
                .build();
    }

    @Transactional
    public Long saveMember(Member member) {
        Long memberId = memberRepository.save(member).getId();
        simpleSourceBean.publishMemberChange("SAVE", memberId);
        return memberId;
    }

    @Transactional
    public Long updateMember(Long memberId, Member member) {
        Member basicMember = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        basicMember.setName(member.getName());
        basicMember.setPassword(member.getPassword());
        basicMember.setRole(member.getRole());
        memberRepository.save(basicMember);

        simpleSourceBean.publishMemberChange("UPDATE", memberId);
        return memberId;
    }

    @Transactional
    public Long deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
        simpleSourceBean.publishMemberChange("DELETE", memberId);
        return memberId;
    }

    @Transactional
    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

}
