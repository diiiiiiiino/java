package com.example.javaspringbootstudy.service;

import com.example.javaspringbootstudy.entity.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
