package com.ccs.services;

import com.ccs.models.entity.Member;
import com.ccs.models.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    public final MemberRepository memberRepository;

    @Transactional
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long saveMember(Member member) {
        return memberRepository.save(member).getId();
    }

    @Transactional
    public void updateMember(Member member) {
        memberRepository.save(member);

    }
    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }


}