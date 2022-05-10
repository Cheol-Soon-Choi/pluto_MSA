package com.ccs.controllers;

import com.ccs.config.ServiceConfig;
import com.ccs.models.entity.Member;
import com.ccs.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final ServiceConfig serviceConfig;
    private static final Logger logger = LoggerFactory.getLogger(MemberApiController.class);

    //멤버 조회
    @GetMapping("/users/{memberId}")
    public Member getMember(@PathVariable("memberId") Long memberId) {
        return memberService.getMember(memberId);
    }

    //멤버 생성
    @PostMapping("/users")
    public Long saveMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    //멤버 수정
    @PutMapping("/users/{memberId}")
    public Long updateMember(@PathVariable("memberId") Long memberId, @RequestBody Member member) {
        return memberService.updateMember(member);
    }

    //멤버 삭제
    @DeleteMapping("/users/{memberId}")
    public Long deleteMember(@PathVariable("memberId") Long memberId) {
        return memberService.deleteMember(memberId);
    }

    //멤버 리스트
    @GetMapping("/users")
    public List<Member> getMemberList() {
        return memberService.getMemberList();
    }

    @GetMapping("/")
    public String getConfigValue() {
        return serviceConfig.getExampleProperty();
    }
}
