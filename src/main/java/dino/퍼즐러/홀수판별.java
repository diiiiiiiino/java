package dino.퍼즐러;

public class 홀수판별 {
    public static boolean isOdd(int i){
        return i % 2 == 1;
    }

    public static boolean isOdd2(int i){
        return i % 2 != 0;
    }

    /**
     * isOdd2 보다 빠름, 일반적으로 나누기와 나머지 연산은 다른 산술 및 논리 연산자에 비해 느리다.
     * 성급하게 아래와 같은 코드로 최적화는 좋지 않음
     * @param i
     * @return
     */
    public static boolean isOdd3(int i){
        return (i & 1) != 0; 
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 2;

        //나머지 연산자(%) 정의
        boolean expression = (a / b) * b + (a % b) == a; // 나머지 연산의 결과는 0이 아닌 수를 반환할 때 항상 연산의 좌측 피연산자와 부호가 같다는 것을 의미

        System.out.println(isOdd(-3));
        System.out.println(isOdd2(-3));
        System.out.println(expression);
    }
}
