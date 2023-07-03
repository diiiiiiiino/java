package aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = AopServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod(){
        log.info("helloMethod={}", helloMethod);
    }

    @Test
    void exactMatch() {
        pointcut.setExpression("execution(public String com.example.javaspringbootstudy.aop.AopServiceImpl.hello(String))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void allMatch(){
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatch(){
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar1(){
        pointcut.setExpression("execution(* hel*(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar2(){
        pointcut.setExpression("execution(* *el*(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchFalse() {
        pointcut.setExpression("execution(* nono(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isFalse();
    }

    @Test
    void packageExactMatch1(){
        pointcut.setExpression("execution(* com.example.javaspringbootstudy.aop.AopServiceImpl.hello(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch2(){
        pointcut.setExpression("execution(* com.example.javaspringbootstudy.aop.*.*(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatchFalse(){
        pointcut.setExpression("execution(* com.example.*.*(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isFalse();
    }

    @Test
    void packageMatchSubPackage1(){
        pointcut.setExpression("execution(* com.example.javaspringbootstudy..*.*(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchSubPackage2(){
        pointcut.setExpression("execution(* com.example..*.*(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void typeExactMatch(){
        pointcut.setExpression("execution(* com.example.javaspringbootstudy.aop.AopServiceImpl.*(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchSuperType(){
        pointcut.setExpression("execution(* com.example.javaspringbootstudy.aop.AopService.*(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(* com.example.javaspringbootstudy.aop.AopServiceImpl.*(..))");

        Method internalMethod = AopServiceImpl.class.getMethod("internal", String.class);

        assertThat(pointcut.matches(internalMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
        pointcut.setExpression("execution(* com.example.javaspringbootstudy.aop.AopService.*(..))");

        Method internalMethod = AopServiceImpl.class.getMethod("internal", String.class);

        assertThat(pointcut.matches(internalMethod, AopServiceImpl.class)).isFalse();
    }

    @Test
    void argsMatch() {
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void argsMatchNoArgs() {
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isFalse();
    }

    @Test
    void argsMatchStar() {
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void argsMatchAll() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }

    @Test
    void argsMatchComplex() {
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(helloMethod, AopServiceImpl.class)).isTrue();
    }
}
