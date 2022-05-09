package com.ccs.controllers;

import com.ccs.config.ServiceConfig;
import com.ccs.models.entity.Member;
import com.ccs.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final ServiceConfig serviceConfig;
    private static final Logger logger = LoggerFactory.getLogger(MemberApiController.class);

    @GetMapping("/{memberId}")
    public Member getMember(@PathVariable("memberId") Long memberId) {
        return memberService.getMember(memberId);
    }

    @GetMapping("/memberlist")
    public List<Member> getMemberList() {
        return memberService.getMemberList();
    }

    @PutMapping("/{memberId}")
    public void updateMember(@PathVariable("memberId") Long memberId, @RequestBody Member member) {
        memberService.updateMember(member);
    }

    @PostMapping("/members")
    public Long saveMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
    }

    @GetMapping("/")
    public String getConfigValue() {
        return serviceConfig.getExampleProperty();
    }
}
