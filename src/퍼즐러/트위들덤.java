package 퍼즐러;

public class 트위들덤 {
    public static void main(String[] args) {
        /**
         * 복합 할당 표현식은 그들이 수행하는 연산의 결과를 그들의 좌측 변수의 타입으로 자동 캐스트 한다.
         * 만약 결과의 타입이 변수의 타입과 동일하다면 이 캐스트는 영향을 주지 않는다.
         * 하지만 결과의 타입이 변수의 타입보다 크다면 복합 할당 연산자는 암묵적인 축소 기본 타입 변환을 수행한다.
         */

        short x = 0;
        int i = 123456;

        x += i; //-7616
        //x = x + 1; // 컴파일 에러

        /**
         * 예상치 못한 결과를 피하고 싶다면 byte, short 또는 char 타입의 변수에 복합 할당 연산자를 사용하지 마라.
         * 복합할당 연산자는 암묵적으로 캐스트를 수행한다.
         * 만약 연산 결과의 타입이 변수의 타입보다 크다면, 생성된 캐스트는 위험한 축소 캐스트이다.
         * 그러한 캐스트는 암묵적으로 정밀도나 크기를 버릴 수 있다.
         */
    }
}
