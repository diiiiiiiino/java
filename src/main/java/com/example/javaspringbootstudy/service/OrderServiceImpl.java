package com.example.javaspringbootstudy.service;

import com.example.javaspringbootstudy.entity.Member;
import com.example.javaspringbootstudy.entity.Order;
import com.example.javaspringbootstudy.repository.MemberRepository;
import com.example.javaspringbootstudy.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
