package 모던자바인액션;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import 모던자바인액션.model.Trader;
import 모던자바인액션.model.Transaction;

import java.util.*;

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
        Map<String, String> favouriteMovies = getFavouriteMovies();

        favouriteMovies.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);
    }

    @DisplayName("8.3.3 getOrDefault 메서드")
    @Test
    void test8_3_3() {
        Map<String, String> favouriteMovies = getFavouriteMovies();

        Assertions.assertEquals("Star Wars", favouriteMovies.getOrDefault("Raphael", "NONE"));
        Assertions.assertEquals("NONE", favouriteMovies.getOrDefault("NOS", "NONE"));
    }

    public static Map<String, String> getFavouriteMovies(){
        return Map.ofEntries(
                Map.entry("Raphael", "Star Wars"),
                Map.entry("Cristina", "Matrix"),
                Map.entry("Olivia", "James Bond"));
    }
}
