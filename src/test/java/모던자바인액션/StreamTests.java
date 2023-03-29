package 모던자바인액션;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import 모던자바인액션.model.Dish;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class StreamTests {
    List<Dish> menu;
    TestMock testMock;

    StreamTests() {
        menu = getDishes();
        testMock = mock(TestMock.class);
    }

    @DisplayName("11. 스트림 중간연산 (치종연산 호출 X)")
    @Test
    void streamMiddleCalcTest1_1(){
        /**
         * 스트림의 중간연산은 요소를 하나씩 탐색하면서 순차적으로 중간연산을 실행한다.
         * 그리고 최종연산이 실행되지 않으면 중간연산을 수행하지 않는다.
         * ex) 아래 예제와 같이 첫번째 filter에서 true면 두번째 filter는 실행된다
         *     첫번쨰 filter가 false면 두번째 filter는 실행되지 않는다.
         *
         *     결론은 요소가 100개 있으면 요소 하나씩 A -> B -> C 순으로 연산이 수행된다.
         *     요소 전체가 A를 실행하고 나서 B를 실행하는 것이 아니다.
         */
        menu.stream()
                .filter(m -> {
                    testMock.print("getCalories : " + (m.getCalories() > 300));
                    System.out.println("getCalories : " + (m.getCalories() > 300));
                    return m.isVegetarian();
                })
                .filter(m -> {
                    System.out.println("getCalories : " + (m.getCalories() > 300));
                    return m.getCalories() > 300;
                })
                .map(m -> {
                    System.out.println("getName : " + m.getName());
                    return m.getName();
                });

        verify(testMock, times(0)).print(anyString());
    }

    @DisplayName("1-2. 스트림 중간연산 (최종연산 호출 O)")
    @Test
    void streamMiddleCalcTest1_2(){
        menu.stream()
                .filter(m -> {
                    testMock.print("getCalories : " + (m.getCalories() > 300));
                    System.out.println("getCalories : " + (m.getCalories() > 300));
                    return m.isVegetarian();
                })
                .filter(m -> {
                    System.out.println("getCalories : " + (m.getCalories() > 300));
                    return m.getCalories() > 300;
                })
                .map(m -> {
                    System.out.println("getName : " + m.getName());
                    return m.getName();
                }).count();

        verify(testMock, atLeastOnce()).print(anyString());
    }

    @DisplayName("2. 스트림 재사용")
    @Test
    void streamRecycleTest(){
        /**
         * 스트림은 최종연산이 수행되고 나서 스트림을 재사용 할 수 없다
         */
        Stream<Dish> stream1 = menu.stream()
                .filter(m -> {
                    System.out.println("isVegetarian : " + m.isVegetarian());
                    return m.isVegetarian();
                });

        Assertions.assertThrows(IllegalStateException.class, () -> {
            stream1.count();
            stream1.count();
        });
    }

    @DisplayName("3. distinct")
    @Test
    void distinctTest(){
        /**
         * distinct를 사용하면 고유 요소를 필터링 할 수 있다.
         * 제대로 수행하기 위해 객체의 equals()와 hashCode()를 둘 다 오버라이딩해야한다.
         */
        List<Dish> dishes = menu.stream()
                .distinct()
                .collect(Collectors.toList());

        Assertions.assertEquals(9, dishes.size());
    }

    List<Dish> getDishes() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        return menu;
    }

    class TestMock{
        void print(String str){
            System.out.println(str);
        }
    }
}
