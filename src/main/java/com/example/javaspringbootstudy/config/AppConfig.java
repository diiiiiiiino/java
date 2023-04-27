package com.example.javaspringbootstudy.config;

import com.example.javaspringbootstudy.repository.MemberRepository;
import com.example.javaspringbootstudy.repository.MemoryMemberRepository;
import com.example.javaspringbootstudy.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
