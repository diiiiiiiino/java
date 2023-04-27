package com.example.javaspringbootstudy;

import com.example.javaspringbootstudy.config.AppConfig;
import com.example.javaspringbootstudy.entity.Member;
import com.example.javaspringbootstudy.entity.Order;
import com.example.javaspringbootstudy.enumertation.Grade;
import com.example.javaspringbootstudy.service.MemberService;
import com.example.javaspringbootstudy.service.MemberServiceImpl;
import com.example.javaspringbootstudy.service.OrderService;
import com.example.javaspringbootstudy.service.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}
