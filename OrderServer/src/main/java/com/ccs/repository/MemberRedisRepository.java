package com.ccs.repository;

import com.ccs.models.entity.Member;

public interface MemberRedisRepository {

    void saveMember(Member member);

    void updateMember(Member member);

    void deleteMember(Long memberId);

    Member findMember(Long memberId);
}
