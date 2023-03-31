package 모던자바인액션;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import 모던자바인액션.model.Dish;
import 모던자바인액션.model.Trader;
import 모던자바인액션.model.Transaction;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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

    @DisplayName("7-1. 검색과 매칭 (anyMatch 쇼트서킷) ")
    @Test
    void anyMatchTest(){
        List<Dish> atLeastTrueMenu = Arrays.asList(
                new Dish("pork1", false, 801, Dish.Type.MEAT),
                new Dish("pork2", true, 802, Dish.Type.MEAT),
                new Dish("pork3", false, 803, Dish.Type.MEAT));

        List<Dish> allFalseMenu = Arrays.asList(
                new Dish("pork1", false, 801, Dish.Type.MEAT),
                new Dish("pork2", false, 802, Dish.Type.MEAT),
                new Dish("pork3", false, 803, Dish.Type.MEAT));

        Assertions.assertEquals(true, atLeastTrueMenu.stream()
                .peek(dish -> System.out.println("true dish : " + dish))
                .anyMatch(Dish::isVegetarian));
        Assertions.assertEquals(false, allFalseMenu.stream()
                .peek(dish -> System.out.println("false dish : " + dish))
                .anyMatch(Dish::isVegetarian));
    }

    @DisplayName("7-2. 검색과 매칭 (allMatch 쇼트서킷) ")
    @Test
    void allMatchTest(){
        List<Dish> allTrueMenu = Arrays.asList(
                new Dish("pork", false, 801, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("season fruit", true, 120, Dish.Type.OTHER));

        List<Dish> atLeastFalseMenu = Arrays.asList(
                new Dish("pork", false, 1200, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("season fruit", true, 120, Dish.Type.OTHER));

        Assertions.assertEquals(true, allTrueMenu.stream().allMatch(dish ->  dish.getCalories() < 1000));
        Assertions.assertEquals(false, atLeastFalseMenu.stream()
                .peek(dish -> System.out.println("dish : " + dish))
                .allMatch(dish -> dish.getCalories() < 1000));
    }

    @DisplayName("7-3. 검색과 매칭 (noneMatch 쇼트서킷) ")
    @Test
    void noneMatchTest(){
        List<Dish> atLeastFalseMenu = Arrays.asList(
                new Dish("pork", false, 1200, Dish.Type.MEAT),
                new Dish("chicken", false, 1400, Dish.Type.MEAT),
                new Dish("season fruit", true, 1120, Dish.Type.OTHER));

        List<Dish> allFalseMenu = Arrays.asList(
                new Dish("pork", false, 801, Dish.Type.MEAT),
                new Dish("chicken", false, 1400, Dish.Type.MEAT),
                new Dish("season fruit", true, 120, Dish.Type.OTHER));

        Assertions.assertEquals(true, atLeastFalseMenu.stream().noneMatch(dish ->  dish.getCalories() < 1000));
        Assertions.assertEquals(false, allFalseMenu.stream()
                .peek(dish -> System.out.println("false dish : " + dish))
                .noneMatch(dish ->  dish.getCalories() < 1000));
    }

    @DisplayName("8-1. 요소 검색 (findAny 쇼트서킷) ")
    @Test
    void findAnyTest(){
        List<Dish> dishes = Arrays.asList(
                new Dish("pork", false, 1200, Dish.Type.MEAT),
                new Dish("chicken", false, 1400, Dish.Type.MEAT),
                new Dish("season fruit", true, 1120, Dish.Type.OTHER));

        Optional<Dish> dish = dishes.stream()
                .peek(d -> System.out.println("dish : " + d))
                .filter(d -> d.getName() == "chicken")
                .findAny();

        Assertions.assertEquals(true, dish.isPresent());
    }

    @DisplayName("8-2. 요소 검색 (findFirst 쇼트서킷) ")
    @Test
    void findFirstTest(){
        List<Dish> dishes = Arrays.asList(
                new Dish("pork", false, 1200, Dish.Type.MEAT),
                new Dish("chicken", false, 1400, Dish.Type.MEAT),
                new Dish("season fruit", true, 1120, Dish.Type.OTHER));

        Optional<Dish> dish = dishes.stream()
                .peek(d -> System.out.println("dish : " + d))
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .findFirst();

        Assertions.assertEquals(true, dish.isPresent());
    }

    @DisplayName("9-1 리듀싱 (reduce)")
    @Test
    void reduceTest() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        int sum = numbers.stream().reduce(0, Integer::sum);

        Stream<Integer> stream = Stream.empty();
        Optional<Integer> sum2 = stream.reduce(Integer::sum);

        Assertions.assertEquals(15, sum);
        Assertions.assertEquals(true, sum2.isEmpty());
    }

    @DisplayName("9-2 리듀싱 (max/min)")
    @Test
    void reduceMaxAndMinTest() {
        List<Integer> numbers = Arrays.asList(1,2,5,4,5);
        Optional<Integer> max = numbers.stream()
                .peek(System.out::println)
                .reduce(Integer::max);

        System.out.println("====================================");

        Integer max2 = numbers.stream()
                .peek(System.out::println)
                .reduce(7, Integer::max);

        System.out.println("====================================");

        Optional<Integer> min = numbers.stream()
                .peek(System.out::println)
                .reduce(Integer::min);

        Assertions.assertEquals(5, max.get());
        Assertions.assertEquals(7, max2);
        Assertions.assertEquals(1, min.get());
    }

    @DisplayName("9-3 map과 reduce를 이용한 스트림의 요리 개수 계산")
    @Test
    void mapReduceTest() {
        int count = menu.stream()
                .map(dish -> 1)
                .reduce(0, Integer::sum);
        Assertions.assertEquals(menu.size(), count);
    }

    @DisplayName("10-1 기본형 특화 스트림 (mapToInt, mapToDouble, mapToLong)")
    @Test
    void intStreamTest() {
        List<Dish> dishes = Arrays.asList(
                new Dish("pork", false, 100, Dish.Type.MEAT),
                new Dish("chicken", false, 200, Dish.Type.MEAT),
                new Dish("season fruit", true, 300, Dish.Type.OTHER));

        int calories = dishes.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        List<Dish> emptyDishes = Arrays.asList();
        int emptyCalories = emptyDishes.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        Assertions.assertEquals(600, calories);
        Assertions.assertEquals(0, emptyCalories);
    }

    @DisplayName("10-2 객체 스트림으로 복원하기")
    @Test
    void backupStream() {
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();
    }

    @DisplayName("10-3 OptionalInt")
    @Test
    void optionalIntTest() {
        List<Dish> dishes = Arrays.asList(
                new Dish("pork", false, 100, Dish.Type.MEAT),
                new Dish("chicken", false, 200, Dish.Type.MEAT),
                new Dish("season fruit", true, 300, Dish.Type.OTHER));

        OptionalInt maxCalories = dishes.stream()
                .peek(System.out::println)
                .mapToInt(Dish::getCalories)
                .max();

        int max = maxCalories.orElse(1);

        List<Dish> emptyDishes = Arrays.asList();
        OptionalInt emptyMaxCalories = emptyDishes.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int emptyMax = emptyMaxCalories.orElse(1);

        Assertions.assertEquals(300, max);
        Assertions.assertEquals(1, emptyMax);
    }

    @DisplayName("11-1 숫자 범위 (range, rangeClose)")
    @Test
    void rangeTest() {
        IntStream closedEvenNumbers = IntStream.rangeClosed(1, 100) //시작값과 종료값이 포함
                .filter(n -> n % 2 == 0);

        IntStream evenNumbers = IntStream.range(1, 100) //시작값과 종료값이 포함
                .filter(n -> n % 2 == 0);

        Assertions.assertEquals(50, closedEvenNumbers.count());
        Assertions.assertEquals(49, evenNumbers.count());
    }

    @DisplayName("12-1 피타고라스 수")
    @Test
    void pythagorasTest() {
        Stream<double[]> pythagorasTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0)
                );

        pythagorasTriples.limit(5)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }

    @DisplayName("13-1 값으로 스트림 만들기")
    @Test
    void createStreamTest() {
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase)
                .forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();
    }

    @DisplayName("13-2 null이 될 수 있는 객체로 스트림 만들기")
    @Test
    void createNullableStreamTest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");

        Stream<String> homeValueStream = Stream.ofNullable(map.get("c"));
        Stream<String> values = Stream.of("a", "b", "c")
                .flatMap(key -> Stream.ofNullable(map.get(key)));

        Assertions.assertEquals(0, homeValueStream.count());
        Assertions.assertEquals(2, values.count());
    }

    @DisplayName("13-3 배열로 스트림 만들기")
    @Test
    void createArrayStreamTest() {
        int[] numbers = {1,2,3,4,5};
        int sum = Arrays.stream(numbers).sum();

        Assertions.assertEquals(15, sum);
    }

    @DisplayName("13-4 파일로 스트림 만들기")
    @Test
    void createFileStreamTest() {
        long uniqueWords = 0;

        try(Stream<String> lines = Files.lines(Paths.get("src/test/resources/data.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(6, uniqueWords);
    }

    @DisplayName("13-5 함수로 무한 스트림 만들기 (iterate)")
    @Test
    void iterateTest() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    @DisplayName("13-6 피보나치 수열 만들기")
    @Test
    void fibonacciTest () {
        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1] } )
                .limit(20)
                .forEach(arr -> System.out.println("(" + arr[0] + "," + arr[1] + ")"));

        System.out.println("===================================================");

        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1] } )
                .limit(20)
                .map(arr -> arr[0])
                .forEach(n -> System.out.println("(" + n + ")"));
    }

    @DisplayName("13-7 무한 스트림 자르기")
    @Test
    void iterateTest2() {
        IntStream.iterate(0, n -> n < 100, n -> n + 4)
                .forEach(System.out::println);

        System.out.println("======================================");

        IntStream.iterate(0, n -> n + 4)
                .takeWhile(n -> n < 100)
                .forEach(System.out::println);
    }

    @DisplayName("13-8 무한 스트림 생성 (generate)")
    @Test
    void generateTest() {
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    @DisplayName("14-1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오")
    @Test
    void quiz1() {
        List<Transaction> transactions = getTransaction();
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
    }

    @DisplayName("14-2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오")
    @Test
    void quiz2() {
        List<String> transactions = getTransaction().stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    @DisplayName("14-3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오")
    @Test
    void quiz3() {
        List<Trader> transactions = getTransaction().stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

    }

    @DisplayName("14-4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오")
    @Test
    void quiz4() {
        String name = getTransaction().stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
    }

    @DisplayName("14-5. 밀라노에 거래자가 있는가?")
    @Test
    void quiz5() {
        boolean has = getTransaction().stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
    }

    @DisplayName("14-6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오")
    @Test
    void quiz6() {
        getTransaction().stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    @DisplayName("14-7. 전체 트랜잭션 중 최댓값은 얼마인가?")
    @Test
    void quiz7() {
        OptionalInt max = getTransaction().stream()
                .mapToInt(Transaction::getValue)
                .max();
        System.out.println(max.orElse(0));
    }

    @DisplayName("14-8. 전체 트랜잭션 중 최솟값은 얼마인가?")
    @Test
    void quiz8() {
        OptionalInt min = getTransaction().stream()
                .mapToInt(Transaction::getValue)
                .min();
        System.out.println(min.orElse(0));
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

    List<Transaction> getTransaction() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        return transactions;
    }

    class TestMock{
        void print(String str){
            System.out.println(str);
        }
    }
}
