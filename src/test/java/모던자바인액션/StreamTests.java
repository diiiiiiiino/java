package 모던자바인액션;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import 모던자바인액션.model.Dish;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class StreamTests {
    List<Dish> menu;
    TestMock testMock;

    StreamTests() {
        menu = getDishes();
        testMock = mock(TestMock.class);
    }

    @DisplayName("1-1. 스트림 중간연산 (치종연산 호출 X)")
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

    @DisplayName("3-1. 스트림 슬라이싱 (takeWile 데이터가 정렬되어 있을때 사용하는게 효과적)")
    @Test
    void takeWileTest(){
        List<Dish> dishes = menu.stream()
                .peek(dish -> System.out.println("distinct before : " + dish))
                .distinct() // 중복된 요소 중 첫번째 요소가 파이프라인을 수행하고 나머지는 수행되지 않는다
                .peek(dish -> System.out.println("sorted before : " + dish))
                .sorted(Comparator.comparingInt(Dish::getCalories)) //sorted()가 있을 경우 먼저 요소들이 정렬되고 나서 이후 파이프라인을 실행한다
                .peek(dish -> System.out.println("takeWhile before : " + dish))
                .takeWhile(dish -> {
                    System.out.println("takeWhile : " + dish);
                    return dish.getCalories() < 500;
                }) // takeWhile은 Predicate가 거짓이 되는 지점에서 작업을 중단하고 요소를 반환한다 ex) pizza는 500보다 큰 것 중에 제일 작은 요소기 때문에 takeWhile이 수행 된다.
                .peek(dish -> System.out.println("takeWhile after : " + dish))
                .collect(Collectors.toList());

        Assertions.assertEquals(5, dishes.size());
    }

    @DisplayName("3-2. 스트림 슬라이싱 (dropWile 데이터가 정렬되어 있을때 사용하는게 효과적)")
    @Test
    void dropWileTest(){
        List<Dish> dishes = menu.stream()
                .peek(dish -> System.out.println("distinct before : " + dish))
                .distinct() // 중복된 요소 중 첫번째 요소가 파이프라인을 수행하고 나머지는 수행되지 않는다
                .peek(dish -> System.out.println("sorted before : " + dish))
                .sorted(Comparator.comparingInt(Dish::getCalories)) //sorted()가 있을 경우 먼저 요소들이 정렬되고 나서 이후 파이프라인을 실행한다
                .peek(dish -> System.out.println("dropWhile before : " + dish))
                .dropWhile(dish -> {
                    System.out.println("dropWhile : " + dish);
                    return dish.getCalories() < 500;
                }) // dropWhile은 Predicate가 거짓이 되는 지점에서 작업을 중단하고 남은 모든 요소를 반환한다.
                .peek(dish -> System.out.println("dropWhile after : " + dish))
                .collect(Collectors.toList());

        Assertions.assertEquals(4, dishes.size());
    }

    @DisplayName("4. 스트림 축소 (limit)")
    @Test
    void limitTest(){
        int limit = 3;
        List<Dish> dishes = menu.stream()
                .peek(dish -> System.out.println("distinct before : " + dish))
                .distinct()
                .peek(dish -> System.out.println("limit before : " + dish))
                .limit(limit) //limit은 제한된 수 만큼의 요소 까지만 순회한다.
                .peek(dish -> System.out.println("limit after : " + dish))
                .collect(Collectors.toList());

        Assertions.assertEquals(limit, dishes.size());
    }

    @DisplayName("5-1. 요소 건너뛰기 (filter -> skip)")
    @Test
    void skipTest(){
        List<Dish> dishes = menu.stream()
                .peek(dish -> System.out.println("filter before : " + dish))
                .filter(dish -> Dish.Type.MEAT.equals(dish.getType()))
                .peek(dish -> System.out.println("skip before : " + dish))
                .skip(2)
                .peek(dish -> System.out.println("skip after : " + dish))
                .collect(Collectors.toList());

        Assertions.assertEquals(3, dishes.size());
    }

    @DisplayName("5-2. 요소 건너뛰기 (skip -> filter)")
    @Test
    void skipTest2(){
        List<Dish> dishes = menu.stream()
                .peek(dish -> System.out.println("skip before : " + dish))
                .skip(2)
                .peek(dish -> System.out.println("filter before : " + dish))
                .filter(dish -> Dish.Type.MEAT == dish.getType())
                .peek(dish -> System.out.println("filter after : " + dish))
                .collect(Collectors.toList());

        Assertions.assertEquals(3, dishes.size());
    }

    @DisplayName("6-1. 매핑 (map)")
    @Test
    void mapTest(){
        List<Integer> wordLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        Assertions.assertEquals(11, wordLengths.size());
    }

    @DisplayName("6-2. 스트림 평면화")
    @Test
    void flatMapTest(){
        List<String> words = Arrays.asList("Hello", "World");
        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        Assertions.assertEquals(7, uniqueCharacters.size());
    }

    @DisplayName("6-3. 매핑을 이용한 제곱근 리스트 반환")
    @Test
    void mapRootTest(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        List<Integer> squares = numbers.stream()
                .map(num -> num * num)
                .collect(Collectors.toList());

        for(int i = 0; i < numbers.size(); i++){
            Assertions.assertEquals(numbers.get(i) * numbers.get(i), squares.get(i));
        }
    }

    @DisplayName("6-4. 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트 반환")
    @Test
    void mapQuiz(){
        List<Integer> numbers = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);

        List<int[]> pairs = numbers.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        Assertions.assertEquals(numbers.size() * numbers2.size(), pairs.size());
    }

    @DisplayName("6-5. 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트 반환 (합이 3으로 나누어 떨어지는 쌍) ")
    @Test
    void mapQuiz2(){
        List<Integer> numbers = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);

        List<int[]> pairs = numbers.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        Assertions.assertEquals(2, pairs.size());
    }

    List<Dish> getDishes() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 801, Dish.Type.MEAT),
                new Dish("pork", false, 802, Dish.Type.MEAT),
                new Dish("pork", false, 803, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 550, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 530, Dish.Type.OTHER),
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
