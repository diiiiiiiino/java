package main.java.퍼즐러;

public class 십육진수의즐거움 {
    public static void main(String[] args) {
        /**
         * 16진수 또는 8진수 리터럴은 그들의 상위 비트가 1이라면 음수이다.
         * 10진수 음수는 마이너스 부호의 존재로 인해 명확하게 구별된다.
         */

        /**
         * 혼합타입 계산
         * 0xcafebabe -> -889275714 (십진수)
         * int 타입을 long 타입으로 변환 시 int는 부호를 지닌 성수 타입이기 떄문에
         * 변환 시 부호 확장은 수행한다. 따라서 int 타입 음의 값을 수치적으로 동일한 long 타입 값으로 확장한다.
         * (int) 0xcafebabe -> (long) 0xffffffffcafebabeL
         */
        System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));

        /**
         * 아래와 같이 우측 피연산자를 long 타입의 16진법 리터럴을 사용하면
         * 해가 되는 부호 확장을 피하게 하고, 1cafebabe라는 기대한 결과를 출력한다.
         */
        System.out.println(Long.toHexString(0x100000000L + 0xcafebabeL));
    }
}
