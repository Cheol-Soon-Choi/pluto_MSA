package com.ccs.repository;

import com.ccs.models.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class MemberRedisRepositoryImpl implements MemberRedisRepository {

    private static final String HASH_NAME = "member";
    private RedisTemplate<String, Member> redisTemplate;
    private HashOperations hashOperations;

    public MemberRedisRepositoryImpl() {
        super();
    }

    @Autowired
    private MemberRedisRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveMember(Member member) {
        hashOperations.put(HASH_NAME, "member" + member.getId(), member);
    }

    @Override
    public void updateMember(Member member) {
        hashOperations.put(HASH_NAME, "member" + member.getId(), member);
    }

    @Override
    public void deleteMember(Long memberId) {
        hashOperations.delete(HASH_NAME, "member" + memberId);
    }

    @Override
    public Member findMember(Long memberId) {
        return (Member) hashOperations.get(HASH_NAME, "member" + memberId);
    }
}
