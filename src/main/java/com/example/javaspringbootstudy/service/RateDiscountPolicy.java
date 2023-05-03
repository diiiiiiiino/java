package com.example.javaspringbootstudy.service;

import com.example.javaspringbootstudy.entity.Member;
import com.example.javaspringbootstudy.enumertation.Grade;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
