package main.java.퍼즐러;

public class 애인바꾸기 {
    public static void main(String[] args) {
        /**
         * 자바 언어 명세는 "피연산자는 좌측에서 우측으로 연산된다"라고 말한다.
         * 하나의 표현식에서 한 번 이상 동일한 변수에 할당하지 말자
         * 같은 변수에 여러 번의 할당하는 표현식은 혼동을 야기하고, 원하는대로 동작하는 경우가 드물다.
         * 영리한 프로그램 기교를 피하라, 그들은 버그를 만들기 쉽고 유지보수가 어려우며 직관적인 코드보다도 훨씬 느리다.
         */

        int x = 1984;
        int y = 2001;
        x ^= y ^= x ^= y; //이런 코드를 사용하면 안된다.

        System.out.println("x : " + x + " y : " + y);

        int x2 = 1;
        int y2 = 2;

        //x ^= y ^= x ^= y 자바에서 실제 동작
        int tmp1 = x2;
        int tmp2 = y2;
        int tmp3 = x2 ^ y2;
        x2 = tmp3;
        y2 = tmp2 ^ tmp3;
        x2 = tmp1 ^ y2;

        System.out.println("x2 : " + x2 + " y2 : " + y2);
    }
}
