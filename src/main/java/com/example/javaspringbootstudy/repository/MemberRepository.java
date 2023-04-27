package com.example.javaspringbootstudy.repository;

import com.example.javaspringbootstudy.entity.Member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
