package EffectiveJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericTests {
    @Test
    @DisplayName("아이템26. 로 타입은 사용하지 말라 (List 로 타입을 사용하는 경우)")
    void item26(){
        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, Integer.valueOf(42));

        Assertions.assertThrows(ClassCastException.class, () -> {
            String s = strings.get(0);
        });
    }

    @Test
    @DisplayName("아이템26. 로 타입은 사용하지 말라 (List<Object>를 사용하는 경우)")
    void item26_2(){
        List<String> strings = new ArrayList<>();
        //unsafeAdd2(strings, Integer.valueOf(42)); //컴파일 에러 발생!!
    }

    @Test
    @DisplayName("아이템26. 로 타입은 사용하지 말라 (List<?> 비한정적 와일드카드 타입을 사용하라)")
    void item26_3(){
        List<?> list = new ArrayList<>();
        //list.add(""); //컴파일 에러 발생!!
        list.add(null);

        //로 타입은 타입 안정성을 잃어버린다.
        List list2 = new ArrayList<>();
        list2.add("1");
        list2.add(1);
    }

    private static void unsafeAdd(List list, Object o){
        list.add(o);
    }

    private static void unsafeAdd2(List<Object> list, Object o){
        list.add(o);
    }
}
