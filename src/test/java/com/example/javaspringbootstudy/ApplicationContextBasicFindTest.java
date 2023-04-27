package com.example.javaspringbootstudy;

import com.example.javaspringbootstudy.config.AppConfig;
import com.example.javaspringbootstudy.service.MemberService;
import com.example.javaspringbootstudy.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @DisplayName("빈 이름으로 조회")
    @Test
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("이름 없이 타입만으로 조회")
    @Test
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("빈 이름과 구체 타입으로 조회")
    @Test
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("빈 이름으로 조회X")
    @Test
    void findBeanByNameX() {
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxx", MemberService.class));
    }
}
