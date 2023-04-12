package 모던자바인액션;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import 모던자바인액션.model.Dish;
import 모던자바인액션.model.PrimeNumbersCollectors;
import 모던자바인액션.model.enumeration.CaloricLevel;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class StreamDataCollectTests extends StreamBaseTest {

    /**
     * 1. 리듀싱
     */

    @DisplayName("6.2.1. 스트림값에서 최댓값과 최솟값 검색")
    @Test
    void reduce6_2_1(){
        Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);

        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));

        Assertions.assertTrue(mostCalorieDish.isPresent());
        Assertions.assertEquals(803, mostCalorieDish.get().getCalories());
    }

    @DisplayName("6.2.2 요약 연산")
    @Test
    void summaryTest6_2_2() {
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));

        Assertions.assertEquals(totalCalories, menuStatistics.getSum());
        Assertions.assertEquals(avgCalories, menuStatistics.getAverage());
    }

    @DisplayName("6.2.3 문자열 연결")
    @Test
    void stringJoining6_2_3() {
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu);

        //menu.stream().collect(joining()); //todo : 왜 안되는지 확인 필요

        String shortMenuComma = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenuComma);
    }

    /**
     * 2. 범용 리듀싱 요약 연산
     */
    @DisplayName("6.2.4 범용 리듀싱 요약 연산")
    @Test
    void globalReducingSummary() {
        int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));

        Optional<Dish> mostCalorieDish = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

        Assertions.assertTrue(mostCalorieDish.isPresent());
    }

    /**
     * 3. 그룹화
     */
    @DisplayName("6.3 그룹화")
    @Test
    void grouping6_3() {
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));

        Assertions.assertEquals(4, dishesByCaloricLevel.get(CaloricLevel.DIET).size());
        Assertions.assertEquals(4, dishesByCaloricLevel.get(CaloricLevel.NORMAL).size());
        Assertions.assertEquals(3, dishesByCaloricLevel.get(CaloricLevel.FAT).size());
    }

    @DisplayName("6.3.1 그룹화된 요소 조작")
    @Test
    void grouping6_3_1() {
        Map<Dish.Type, List<Dish>> caloricDishedByType = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType));

        Map<Dish.Type, List<Dish>> caloricDishedByType2 = menu.stream()
                .collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));

        Map<Dish.Type, List<String>> dishNamesByType = menu.stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        System.out.println(dishNamesByType);

        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));

        Map<Dish.Type, Set<String>> dishNamesByType2 = menu.stream().collect(groupingBy(Dish::getType, flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
        System.out.println(dishNamesByType2);

        Assertions.assertEquals(false, caloricDishedByType.containsKey(Dish.Type.FISH));
        Assertions.assertEquals(true, caloricDishedByType2.containsKey(Dish.Type.FISH));
    }

    @DisplayName("6.3.2 다수준 그룹화")
    @Test
    void test6_3_2() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishedByTypeCaloricLevel = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if(dish.getCalories() <= 400)
                                return CaloricLevel.DIET;
                            else if(dish.getCalories() <= 700)
                                return CaloricLevel.NORMAL;
                            else
                                return CaloricLevel.FAT;
                        })));
        System.out.println(dishedByTypeCaloricLevel);
    }

    @DisplayName("6.3.3 서브그룹으로 데이터 수집")
    @Test
    void test6_3_3(){
        Map<Dish.Type, Long> typesCount = menu.stream()
                .collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
            .collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        Assertions.assertEquals(3, typesCount.size());
        Assertions.assertEquals(3, mostCaloricByType.size());
    }

    @DisplayName("6.3.3.1 컬렉터 결과를 다른 형식에 적용하기")
    @Test
    void test6_3_3_1() {
        Map<Dish.Type, Dish> mostCaloricByType = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(mostCaloricByType);
    }

    @DisplayName("6.3.3.2 groupingBy와 함께 사용하는 다른 컬렉터 예제")
    @Test
    void test6_3_3_2() {
        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream()
                .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelByType = menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> {
                    if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }, toSet())));

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelByType2 = menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> {
                    if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }, toCollection(HashSet::new))));

        System.out.println(totalCaloriesByType);
        System.out.println(caloricLevelByType);
        System.out.println(caloricLevelByType2);
    }

    /**
     * 분할
     */
    @DisplayName("6.4 분할")
    @Test
    void test6_4() {
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));


        System.out.println(partitionedMenu);
        System.out.println(partitionedMenu.get(true));
    }

    @DisplayName("6.4.1 분할의 장점")
    @Test
    void test6_4_1() {
        // 분할 함수가 반환하는 참, 거짓 두 가지 요소의 스트림 리스트를 모두 유지한다는 것이 장점이다
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println(vegetarianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(mostCaloricPartitionedByVegetarian);
    }

    @DisplayName("6.4.2 숫자를 소수와 비소수로 분할하기")
    @Test
    void test6_4_2() {
        int n = 9;
        Map<Boolean, List<Integer>> partitionPrime = IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrimeSqrt(candidate)));
        System.out.println(partitionPrime);
    }

    @DisplayName("6.6.2 컬렉터 성능 비교")
    @Test
    void test6_6_2() {
        long fastest = Long.MAX_VALUE;
        for(int i = 0; i < 10; i++){
            long start = System.nanoTime();
            partitionPrimes(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration < fastest) fastest = duration;

            //85225
        }

        System.out.println("Fastest execution done in " + fastest + " msecs" );
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollectors());
    }

    public boolean isPrime(int candidate){
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);
    }

    public boolean isPrimeSqrt(int candidate){
        int candidateRoot = (int) Math.sqrt((double)candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }
}
