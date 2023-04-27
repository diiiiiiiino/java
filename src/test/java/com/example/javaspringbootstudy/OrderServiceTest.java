package com.example.javaspringbootstudy;

import com.example.javaspringbootstudy.config.AppConfig;
import com.example.javaspringbootstudy.entity.Member;
import com.example.javaspringbootstudy.entity.Order;
import com.example.javaspringbootstudy.enumertation.Grade;
import com.example.javaspringbootstudy.service.MemberService;
import com.example.javaspringbootstudy.service.MemberServiceImpl;
import com.example.javaspringbootstudy.service.OrderService;
import com.example.javaspringbootstudy.service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
