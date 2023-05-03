package com.example.javaspringbootstudy.service;

import com.example.javaspringbootstudy.entity.Member;
import com.example.javaspringbootstudy.enumertation.Grade;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
