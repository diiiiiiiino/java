package aop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class WithinTest {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = AopServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void withinExact() {
        pointcut.setExpression("within(com.example.javaspringbootstudy.aop.AopServiceImpl)");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void withinStar() {
        pointcut.setExpression("within(com.example.javaspringbootstudy.aop.*Service*)");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void withinSubPackage() {
        pointcut.setExpression("within(com.example.javaspringbootstudy..*)");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("타겟의 타입에만 직접 적용, 인터페이스를 선정하면 안된다.")
    void withinSuperTypeFalse() {
        pointcut.setExpression("within(com.example.javaspringbootstudy.aop.AopService)");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("execution은 타입 기반, 인터페이스를 선정가능")
    void executionSuperTypeFalse() {
        pointcut.setExpression("execution(* com.example.javaspringbootstudy.aop.AopService.*(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }
}
