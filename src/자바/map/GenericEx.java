package 자바.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenericEx {
    public static void main(String[] args) {
        /**
         * 제네릭의 명시적인 타입 -> 와일드 카드 : 자동 형변환
         */
        List<Integer> integers = new ArrayList<>();
        List<?> wildList = integers;

        /**
         * 와일드 카드 ->제네릭의 명시적인 타입 : 명시적 형변환
         */
        integers = (List<Integer>)wildList;

        /**
         * 명시적 타입 <--> 명시적 타입 : 불가
         */
        //integerList = (List<Integer>)objects;
        //objects = (List<Object>)integerList;

        /**
         * List<Object> -> List<?> -> List<Integer> 형변환 가능 (경고 발생)
         */
        List<Object> objects = new ArrayList<>();
        objects.add("1");
        objects.add("2");

        List<?> aa = objects;
        List<Integer> bb = (List<Integer>)aa;

        Integer a = bb.get(0);

    }
}
