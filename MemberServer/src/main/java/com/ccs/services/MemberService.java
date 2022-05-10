package com.ccs.services;

import com.ccs.models.entity.Member;
import com.ccs.models.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

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
    public Long updateMember(Member member) {
        return memberRepository.save(member).getId();

    }

    @Transactional
    public Long deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
        return memberId;
    }

    @Transactional
    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

}
