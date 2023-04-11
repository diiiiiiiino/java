package 모던자바인액션;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import 모던자바인액션.model.Dish;
import 모던자바인액션.model.enumeration.CaloricLevel;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class StreamDataCollectTests extends StreamBaseTest {

    /**
     * 1. 리듀싱
     */

    @DisplayName("6.2.1. 스트림값에서 최댓값과 최솟값 검색")
    @Test
    void reduce6_2_1(){
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);

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
}
