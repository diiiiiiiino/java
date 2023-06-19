package EffectiveJava.chap02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;

import static EffectiveJava.chap02.NyPizza.Size.SMALL;
import static EffectiveJava.chap02.Pizza.Topping.*;
import static EffectiveJava.chap02.Rank.*;

public class ObjectCreateDestroyTests {

    @DisplayName("아이템1. 생성자 대신 정적 팩토리 메서드를 고려하라")
    @Test
    void constructorInsteadofStaticFactoryMethod() throws IOException {
        Date d = Date.from(Instant.EPOCH);
        Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
        BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
        StackWalker luke = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        Object newArray = Array.newInstance(Integer.class, 3);
        FileStore fs = Files.getFileStore(Path.of("/root"));
        BufferedReader br = Files.newBufferedReader(Path.of("/root"));

        Hashtable<String, Rank> map = new Hashtable<>();
        List<Rank> integers = Collections.list(map.elements());
    }

    @DisplayName("아이템2. 생성자에 매개변수가 많다면 빌더를 고려하라")
    @Test
    void constructorInsteadofBuilder() {
        NutritionFacts nutritionFacts = new NutritionFacts.Builder(2,3)
                .calories(100)
                .sodium(35)
                .carbohydrate(27)
                .build();

        Assertions.assertEquals(2, nutritionFacts.getServingSize());
        Assertions.assertEquals(3, nutritionFacts.getServings());
        Assertions.assertEquals(100, nutritionFacts.getCalories());
        Assertions.assertEquals(35, nutritionFacts.getSodium());
        Assertions.assertEquals(27, nutritionFacts.getCarbohydrate());
    }

    @DisplayName("아이템2. 생성자에 매개변수가 많다면 빌더를 고려하라")
    @Test
    void constructorInsteadofBuilder2() {
        NyPizza pizza = new NyPizza.Builder(SMALL).addTopping(SAUSAGE).addTopping(ONION).build();
        Calzone calzone = new Calzone.Builder().addTopping(HAM).sauceInside().build();
    }
}
