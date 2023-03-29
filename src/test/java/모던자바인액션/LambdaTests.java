package 모던자바인액션;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import 모던자바인액션.model.*;
import 모던자바인액션.인터페이스.TripleFunction;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import static 모던자바인액션.model.Color.GREEN;
import static 모던자바인액션.model.Color.RED;

public class LambdaTests {
    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();

    @DisplayName("1-1. Comparator 조합")
    @Test
    void comparatorTest(){
        List<Apple> apples = Arrays.stream(new Apple[]{ new Apple(5, Color.RED), new Apple(7, Color.BLUE), new Apple(5, GREEN), new Apple(1, Color.BLUE)})
                .collect(Collectors.toList());

        Comparator<Apple> c = Comparator.comparing(Fruit::getWeight);

        //apples.sort(c);
        //apples.sort(c.reversed());
        apples.sort(c.reversed().thenComparing(Apple::getColor));

        for(Apple apple : apples){
            System.out.println(apple.getWeight() + ", color : " + apple.getColor());
        }

        Assertions.assertEquals(5, apples.get(2).getWeight());
        Assertions.assertEquals(GREEN, apples.get(2).getColor());
        Assertions.assertEquals(5, apples.get(1).getWeight());
        Assertions.assertEquals(RED, apples.get(1).getColor());
    }

    @DisplayName("1-2. Predicate 조합")
    @Test
    void predicateTest(){
        Predicate<Apple> redApple = apple -> Color.RED.equals(apple.getColor());
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);
        Predicate<Apple> redAndHeavyAppleOrGreen = redAndHeavyApple.or(apple -> GREEN.equals(apple.getColor()));

        Assertions.assertEquals(true, notRedApple.test(new Apple(5, Color.BLUE)));
        Assertions.assertEquals(true, redAndHeavyApple.test(new Apple(555, Color.RED)));
        Assertions.assertEquals(false, redAndHeavyAppleOrGreen.test(new Apple(10, Color.RED)));
    }

    @DisplayName("1-3. Function 조합")
    @Test
    void functionTest(){
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> i = f.compose(g);

        int andThenResult = h.apply(1);
        int composeResult = i.apply(1);

        Assertions.assertEquals(4, andThenResult);
        Assertions.assertEquals(3, composeResult);
    }

    @DisplayName("2. 생성자 참조 예제")
    @Test
    void constructorRefTest(){
        Supplier<Apple> c1 = () -> new Apple();
        c1 = Apple::new;
        Apple a1 = c1.get();

        Function<Integer, Apple> c2 = (weight) -> new Apple(weight);
        c2 = Apple::new;
        Apple a2 = c2.apply(55);

        Function<Color, Apple> c3 = (color) -> new Apple(color);
        c3 = Apple::new;
        Apple a3 = c3.apply(Color.RED);

        BiFunction<Integer, Color, Apple> c4 = (weight, color) -> new Apple(weight, color);
        c4 = Apple::new;
        Apple a4 = c4.apply(170, Color.RED);

        TripleFunction<Integer, Color, Integer, Apple> c5 = (weight, color, price) -> new Apple(weight, color, price);
        c5 = Apple::new;
        Apple a5 = c5.apply(170, Color.BLUE, 100);
    }

    @DisplayName("3. 생성자 참조 활용")
    @Test
    void constructorRefTest2(){
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);

        Fruit apple = giveMeFruit("apple", 170);
        Fruit orange = giveMeFruit("orange", 99);

        Assertions.assertInstanceOf(Apple.class, apple);
        Assertions.assertInstanceOf(Orange.class, orange);
    }

    @DisplayName("4. 메서드 참조 예제")
    @Test
    void methodRefTest(){
        Consumer<Integer> consumer = integer -> MethodRef.staticMethod(integer);
        consumer = MethodRef::staticMethod;

        consumer.accept(1);

        MethodRef test = new MethodRef();

        BiConsumer<MethodRef, Integer> biConsumer = (test1, integer) -> test1.instanceMethod(integer);
        biConsumer = MethodRef::instanceMethod;
        biConsumer.accept(test, 5);

        Consumer<Integer> consumer2 = integer -> test.instanceMethod(integer);
        consumer2 = test::instanceMethod;
        consumer2.accept(5);
    }

    static Fruit giveMeFruit(String fruit, Integer weight){
        return map.get(fruit.toLowerCase())
                .apply(weight);
    }
}
