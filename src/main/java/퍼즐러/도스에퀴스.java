package main.java.퍼즐러;

public class 도스에퀴스 {
    public static void main(String[] args) {
        /**
         * 혼합된 타입 연산은 혼란을 일으킬 수 있다.
         *
         * 조건부 연산자의 결과 타입 결정 규칙
         * 1. 두번째, 3번째 피연산자들이 같은 타입이라면 그것이 조건식의 타입이다.
         * 혼한됩 타입 연산으로부터 벗어남으로써 전체적인 혼란을 피할 수 있다.
         *
         * 2. byte, short 또는 char 타입을 T타입이라고 했을 때 만약 피연산자 중 하나가 T타입이고
         * 다른 하나는 T타입으로 표현 가능한 int 타입 상수라면, 조건식의 타입은 T이다.
         *
         * 3. 그렇지 않다면, 이진 수치 확장이 피연산자 타입에 적용되고 조건식의 타입은 두 번째, 세번째 피연산자의 확장된 타입이다.
         */

        char x = 'A';
        int i = 0;
        System.out.println(true ? x : 0);
        System.out.println(false ? i : x);
    }
}
