package com.example.javaspringbootstudy.aop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class ArgsTest {
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = AopServiceImpl.class.getMethod("hello", String.class);
    }

    private AspectJExpressionPointcut pointcut(String expression){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @Test
    void withinExact() {
        assertThat(pointcut("args(String)").matches(helloMethod, AopServiceImpl.class)).isTrue();
        assertThat(pointcut("args(Object)").matches(helloMethod, AopServiceImpl.class)).isTrue();
        assertThat(pointcut("args()").matches(helloMethod, AopServiceImpl.class)).isFalse();
        assertThat(pointcut("args(..)").matches(helloMethod, AopServiceImpl.class)).isTrue();
        assertThat(pointcut("args(*)").matches(helloMethod, AopServiceImpl.class)).isTrue();
        assertThat(pointcut("args(String, ..)").matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void argsVsExecution() {
        assertThat(pointcut("args(String)").matches(helloMethod, AopServiceImpl.class)).isTrue();
        assertThat(pointcut("args(java.io.Serializable)").matches(helloMethod, AopServiceImpl.class)).isTrue();
        assertThat(pointcut("args(Object)").matches(helloMethod, AopServiceImpl.class)).isTrue();

        assertThat(pointcut("execution(* *(String))").matches(helloMethod, AopServiceImpl.class)).isTrue();
        assertThat(pointcut("execution(* *(java.io.Serializable))").matches(helloMethod, AopServiceImpl.class)).isFalse();
        assertThat(pointcut("execution(* *(Object))").matches(helloMethod, AopServiceImpl.class)).isFalse();
    }
}
