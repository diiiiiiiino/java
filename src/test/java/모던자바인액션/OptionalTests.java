package 모던자바인액션;

import lombok.Data;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import 객체지향.구성.Car;
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

public class OptionalTests {

    @DisplayName("11.3.1 Optional 객체 만들기")
    @Test
    void test11_3_1() {
        Optional<String> optionalCar = Optional.empty();

        Optional<String> optionalCar2 = Optional.of("A");

        Optional<String> optionalCar3 = Optional.ofNullable(null);

        Assertions.assertThrows(NullPointerException.class, () -> Optional.of(null));
    }

    @DisplayName("11.3.2 맵으로 Optional의 값을 추출하고 변환하기")
    @Test
    void test11_3_2() {
        Car car = new Car("Porche");
        Optional<Car> optionalCar = Optional.ofNullable(car);
        Optional<String> name = optionalCar.map(Car::getName);

        Assertions.assertEquals("Porche", name.get());
    }

    @DisplayName("11.3.3 flatMap으로 Optional 객체 연결")
    @Test
    void test11_3_3() {
        Optional<Person> person = Optional.ofNullable(new Person());

        String carName = person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");

        Assertions.assertEquals("Unknown", carName);
    }

    @DisplayName("11.3.4 Optional 스트림 조작")
    @Test
    void test11_3_4() {
        Stream<Optional<String>> stream = Stream.of(Optional.of("A"), Optional.of("B"), Optional.ofNullable(null));
        Set<String> result = stream.filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        System.out.println(result);
        Assertions.assertEquals(2, result.size());
    }

    @DisplayName("11.3.6 Optional 합치기")
    @Test
    void test11_3_6() {
        Optional<Person> person = Optional.of(new Person());
        Optional<Car> car = Optional.of(new Car("Hi"));

        Optional<Insurance> insurance = person.flatMap(p -> car.map(c -> findCheapestInsurance()));

        Assertions.assertTrue(insurance.isPresent());

        Optional<Person> person2 = Optional.ofNullable(null);
        Optional<Car> car2 = Optional.of(new Car("Hi"));

        Optional<Insurance> insurance2 = person2.flatMap(p -> car2.map(c -> findCheapestInsurance()));
        Assertions.assertTrue(insurance2.isEmpty());

        Optional<Person> person3 = Optional.ofNullable(new Person());
        Optional<Car> car3 = Optional.empty();

        Optional<Insurance> insurance3 = person3.flatMap(p -> car3.map(c -> findCheapestInsurance()));
        Assertions.assertTrue(insurance3.isEmpty());
    }

    @DisplayName("11.3.7 필터로 특정값 거르기")
    @Test
    void test11_3_7() {
        Optional<Insurance> optionalInsurance = Optional.empty();
        Optional<Insurance> result = optionalInsurance.filter(insurance -> "A".equals(insurance.getName()));

        Assertions.assertTrue(result.isEmpty());

        Optional<Insurance> optionalInsurance2 = Optional.of(new Insurance("A"));

        Optional<Insurance> result2 = optionalInsurance2.filter(insurance -> "A".equals(insurance.getName()));

        Assertions.assertTrue(result2.isPresent());
    }

    @DisplayName("11.4.1 잠재적으로 null이 될 수 있는 대상을 Optional로 감싸기")
    @Test
    void test11_4_1() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("A", null);

        Optional<String> value = Optional.ofNullable(hashMap.get("A"));

        Assertions.assertTrue(value.isEmpty());
    }

    @DisplayName("11.4.2 예외와 Optional 클래스")
    @Test
    void test11_4_2() {
        String s = "";
        Optional<Integer> result = stringToInt(s);

        Assertions.assertTrue(result.isEmpty());
    }

    static Optional<Integer> stringToInt(String s){
        try{
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e){
            return Optional.empty();
        }
    }

    Insurance findCheapestInsurance(){
        return new Insurance();
    }

    @Getter
    class OptionalPerson{
        /**
         * Optional은 직렬화 할 수 없다.
         * Optional 클래스 설계자가 용도를 선택형 반환값을 지원하는 것이라고 명확하게 못박았다
         */
        private Car car;
        public Optional<Car> getCarAsOptional(){
            return Optional.ofNullable(car);
        }
    }

    @Getter
    class Person{
        private Optional<Car> car = Optional.empty();
    }

    @Getter
    class Car{
        private Optional<Insurance> insurance;
        private String name;

        public Car(String name) {
            this.name = name;
        }
    }

    @Getter
    class Insurance {
        private String name;

        public Insurance() {}

        public Insurance(String name) {
            this.name = name;
        }
    }

}
