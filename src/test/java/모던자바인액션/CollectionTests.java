package 모던자바인액션;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import 모던자바인액션.model.Trader;
import 모던자바인액션.model.Transaction;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionTests {

    @DisplayName("8.1 컬렉션 팩토리")
    @Test
    void test8_1() {
        final List<String> friends = Arrays.asList("a", "b", "c");
        friends.set(0, "C");

        Assertions.assertThrows(UnsupportedOperationException.class, () -> friends.add("d"));
    }

    @DisplayName("8.1.1 리스트 팩토리")
    @Test
    void test8_1_1() {
        final List<String> friends = List.of("a", "b", "c");

        Assertions.assertThrows(UnsupportedOperationException.class, () -> friends.add("d"));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> friends.set(0, "d"));
    }

    @DisplayName("8.1.2 집합 팩토리")
    @Test
    void test8_1_2() {
        final Set<String> friends = Set.of("a", "b", "c");

        Assertions.assertThrows(IllegalArgumentException.class, () -> Set.of("a", "b", "c", "a"));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> friends.add("a"));
    }

    @DisplayName("8.1.3 맵 팩토리")
    @Test
    void test8_1_3(){
        final Map<String, Integer> ageOfFriends = Map.of("a", 30, "b", 25, "c", 26);
        final Map<String, Integer> ageOfFriends2 = Map.ofEntries(
                Map.entry("a", 30),
                Map.entry("b", 10),
                Map.entry("c", 20));

        Assertions.assertThrows(UnsupportedOperationException.class, () -> ageOfFriends.put("a", 10));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> ageOfFriends.remove("a"));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> ageOfFriends2.put("a", 10));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> ageOfFriends2.remove("a"));
    }

    @DisplayName("8.2.1 removeIf 메서드")
    @Test
    void test8_2_1() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(new Trader("a", "city"), 2022, 1000));
        transactions.add(new Transaction(new Trader("b", "city"), 2022, 1400));
        transactions.add(new Transaction(new Trader("c", "city"), 2022, 700));

        Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            // 아래 두 코드는 동일하다
            /* for(Transaction transaction : transactions){
                if(transaction.getValue() >= 1000){
                    transactions.remove(transaction);
                }
            }*/

            for(Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext();){
                Transaction transaction = iterator.next();
                if(transaction.getValue() >= 1000){
                    transactions.remove(transaction);
                }
            }
        });

        ArrayList<Transaction> transactions2 = new ArrayList<>();
        transactions2.add(new Transaction(new Trader("a", "city"), 2022, 1000));
        transactions2.add(new Transaction(new Trader("b", "city"), 2022, 1400));
        transactions2.add(new Transaction(new Trader("c", "city"), 2022, 700));

        //아래와 같이 변환이 가능하다
        transactions2.removeIf(transaction -> transaction.getValue() >= 1000);

        Assertions.assertEquals(1, transactions2.size());
    }

    @DisplayName("8.2.2 replaceAll 메서드")
    @Test
    void test8_2_2() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("cat");
        strings.add("apple");
        strings.add("computer");

        strings.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));

        System.out.println(strings);
    }

    @DisplayName("8.3.1 forEach 메서드")
    @Test
    void test8_3_1() {
        final Map<String, Integer> ageOfFriends = Map.of("a", 30, "b", 25, "c", 26);

        ageOfFriends.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    @DisplayName("8.3.2 정렬 메서드 comparingByKey comparingByValue")
    @Test
    void test8_3_2() {
        Map<String, String> favouriteMovies = getImmutableFavouriteMovies();

        favouriteMovies.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);
    }

    @DisplayName("8.3.3 getOrDefault 메서드")
    @Test
    void test8_3_3() {
        Map<String, String> favouriteMovies = getImmutableFavouriteMovies();

        Assertions.assertEquals("Star Wars", favouriteMovies.getOrDefault("Raphael", "NONE"));
        Assertions.assertEquals("NONE", favouriteMovies.getOrDefault("NOS", "NONE"));
    }

    @DisplayName("8.3.4 계산 패턴")
    @Test
    void test8_3_4() {
        Map<String, ArrayList<String>> movies = getFavouriteMovies2();

        movies.computeIfAbsent("Raphael", key -> new ArrayList<>()); //값이 null이거나
        Assertions.assertEquals(3, movies.size());

        movies.computeIfAbsent("Raphael2", key -> new ArrayList<>()); //키가 없으면 실행한다.
        Assertions.assertEquals(4, movies.size());

        Map<String, ArrayList<String>> movies2 = getFavouriteMovies2();

        movies2.computeIfPresent("Raphael", (s, strings) -> new ArrayList<>()); // 값이 null이면 실행 안함
        Assertions.assertNull(movies2.get("Raphael"));

        movies2.computeIfPresent("Raphael2", (s, strings) -> new ArrayList<>());
        Assertions.assertNull(movies2.get("Raphael2")); // 키가 없으면 실행 안함

        movies2.computeIfPresent("Olivia", (s, strings) -> null); // 값을 만드는 함수가 널을 반환하면 현재 매핑을 맵에서 제거한다. 권장되지 않음 remove 사용 권장
        Assertions.assertNull(movies2.get("Olivia"));

        movies2.computeIfPresent("Cristina", (s, strings) -> {
            strings.add("hihi");
            return strings;
        });

        Assertions.assertEquals(1, movies2.get("Cristina").size());

        Map<String, ArrayList<String>> movies3 = getFavouriteMovies2();
        movies3.compute("Raphael", (s, strings) -> new ArrayList<>());
        Assertions.assertNotNull(movies3.get("Raphael"));

        movies3.compute("Raphael2", (s, strings) -> new ArrayList<>());
        Assertions.assertNotNull(movies3.get("Raphael2"));
    }

    @DisplayName("8.3.5 삭제 패턴")
    @Test
    void test8_3_5() {
        Map<String, String> movies = getFavouriteMovies();
        movies.remove("Olivia");

        Assertions.assertEquals(2, movies.size());

        Map<String, String> movies2 = getFavouriteMovies();

        movies2.remove("Olivia", "James Bond");

        Assertions.assertEquals(2, movies2.size());
        Assertions.assertNull(movies2.get("Olivia"));

        Map<String, String> movies3 = getFavouriteMovies();

        movies3.remove("Olivia", "NONE");

        Assertions.assertEquals(3, movies3.size());
        Assertions.assertNotNull(movies3.get("Olivia"));
    }

    @DisplayName("8.3.6 교체 패턴")
    @Test
    void test8_3_6() {
        Map<String, String> movies = getFavouriteMovies();
        movies.replace("Raphael", "Star wars2");
        movies.replaceAll((s, s2) -> s2.toUpperCase());

        Assertions.assertEquals("STAR WARS2", movies.get("Raphael"));
    }

    @DisplayName("8.3.7 합침")
    @Test
    void test8_3_7() {
        Map<String, String> family = Map.ofEntries(
                Map.entry("Teo", "Star Wars"),
                Map.entry("Cristina", "James Bond"));

        Map<String, String> friends = Map.ofEntries(
                Map.entry("Raphael", "Star Wars"),
                Map.entry("Cristina", "Matrix"));

        Map<String, String> everyone = new HashMap<>(family);
        friends.forEach((k, v) -> everyone.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));

        Assertions.assertEquals(3, everyone.size());
        Assertions.assertEquals("James Bond & Matrix", everyone.get("Cristina"));

        /**지정된 키와 연관된 값이 없을때 키를 널이 아닌 값과 연결한다.**/
        Map<String, Long> moviesToCount = new HashMap<>();
        moviesToCount.merge("Matrix", 1L, (oldValue, newValue) -> newValue + 1L);
        Assertions.assertEquals(1, moviesToCount.get("Matrix"));

        moviesToCount.merge("Matrix", 1L, (oldValue, newValue) -> newValue + 1L);
        Assertions.assertEquals(2, moviesToCount.get("Matrix"));

        /**지정된 키와 연관된 값이 널이면 키를 널이 아닌 값과 연결한다.**/
        Map<String, Long> moviesToCount2 = new HashMap<>();
        moviesToCount2.put("Matrix", null);

        moviesToCount2.merge("Matrix", 1L, (oldValue, newValue) -> newValue + 1L);
        Assertions.assertEquals(1, moviesToCount2.get("Matrix"));

        moviesToCount2.merge("Matrix", 3L, (oldValue, newValue) -> newValue + 1L);
        Assertions.assertEquals(4, moviesToCount2.get("Matrix"));

        /** Matrix의 값이 없거나 널이면 아래의 BiFunction은 수행되지 않는다 or 값이 있으면 BiFunction이 수행된다 **/

        /**연결된 값을 주어진 매핑 함수의 결과가 널이면 항목을 제거한다**/
        moviesToCount2.merge("Matrix", 1L, (oldValue, newValue) -> null);
        Assertions.assertNull(moviesToCount2.get("Matrix"));

        Map<String, Long> moviesToCount3 = new HashMap<>();
        moviesToCount3.put("Matrix", 1L);

        moviesToCount3.merge("Matrix", 1L, (oldValue, newValue) -> newValue + 1L);
        Assertions.assertEquals(2, moviesToCount3.get("Matrix"));

        moviesToCount3.merge("Matrix", 3L, (oldValue, newValue) -> newValue + 1L);
        Assertions.assertEquals(4, moviesToCount3.get("Matrix"));
    }

    @DisplayName("8.4.1 ConcurrentHashMap 리듀스와 검색")
    @Test
    void test8_4_1(){
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        map.put("a", 1L);
        map.put("b", 10L);
        map.put("c", 100L);

        long parallelismThreshold = 1;
        Optional<Long> maxValue = Optional.ofNullable(map.reduceValues(parallelismThreshold, Long::max));

        Assertions.assertEquals(100, maxValue.get());
    }

    @DisplayName("8.4.2 ConcurrentHashMap 계수")
    @Test
    void test8_4_2() {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        map.put("a", 1L);
        map.put("b", 10L);
        map.put("c", 100L);

        Assertions.assertEquals(3, map.mappingCount());
    }

    @DisplayName("8.4.3 ConcurrentHashMap 집합뷰")
    @Test
    void test8_4_3() {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        map.put("a", 1L);
        map.put("b", 10L);
        map.put("c", 100L);

        ConcurrentHashMap.KeySetView<String, Long> keySetView = map.keySet();
        map.put("d", 200L);

        Assertions.assertEquals(4, keySetView.size());
    }

    public static Map<String, String> getImmutableFavouriteMovies(){
        return Map.ofEntries(
                Map.entry("Raphael", "Star Wars"),
                Map.entry("Cristina", "Matrix"),
                Map.entry("Olivia", "James Bond"));
    }

    public static Map<String, String> getFavouriteMovies(){
        HashMap<String, String> map = new HashMap<>();
        map.put("Raphael", "Star Wars");
        map.put("Cristina", "Matrix");
        map.put("Olivia", "James Bond");

        return map;
    }

    public static Map<String, ArrayList<String>> getFavouriteMovies2(){
        Map<String, ArrayList<String>> map = new HashMap<>();
        map.put("Raphael", null);
        map.put("Cristina", new ArrayList<>());
        map.put("Olivia", new ArrayList<>());

        return map;
    }
}
