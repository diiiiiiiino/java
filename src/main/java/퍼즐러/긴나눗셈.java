package main.java.퍼즐러;

public class 긴나눗셈 {
    public static void main(String[] args) {
        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000; //L를 사용하지 않으면 오버플로우 발생
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

        final long MICROS_PER_DAY2 = 24L * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY2 = 24L * 60 * 60 * 1000;

        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY); // 5
        System.out.println(MICROS_PER_DAY2 / MILLIS_PER_DAY2); // 1000
    }

}
