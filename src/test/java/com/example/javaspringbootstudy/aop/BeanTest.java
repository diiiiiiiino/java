package com.example.javaspringbootstudy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Import({BeanTest.BeanAspect.class, BeanTest.BeanConfig.class})
@SpringBootTest
public class BeanTest {

    @Autowired
    AopService aopService;

    @Test
    void success() {
        aopService.hello("b");
    }

    static class BeanConfig{

        @Bean
        AopService aopService() {
            return new AopServiceImpl();
        }
    }

    @Aspect
    static class BeanAspect {
        @Around("bean(aopService)")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[bean] {}", joinPoint.getSignature());

            return joinPoint.proceed();
        }
    }
}
