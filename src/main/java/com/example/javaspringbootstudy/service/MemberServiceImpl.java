package com.example.javaspringbootstudy.service;

import com.example.javaspringbootstudy.entity.Member;
import com.example.javaspringbootstudy.repository.MemberRepository;
import com.example.javaspringbootstudy.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceImpl implements  MemberService{
    private final MemberRepository memberRepository;

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
