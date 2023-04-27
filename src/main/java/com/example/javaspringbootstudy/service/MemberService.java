package com.example.javaspringbootstudy.service;

import com.example.javaspringbootstudy.entity.Member;

public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
}
